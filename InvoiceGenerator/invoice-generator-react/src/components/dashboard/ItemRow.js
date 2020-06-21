// @flow
import React, { Component } from "react";
import { setItem, setGst } from "../../actions";
import { connect } from "react-redux";
import { ItemRowProps } from "./../../models/Props";
import { ItemRowState } from "./../../models/States";
import Select from "react-select";
import {
  ITEM_ROW_RESPONSIVE_STYLE,
  ITEM_ROW_STYLE,
} from "./../../constants/Styles";
import "./../../index.css";

const GST_DATA = require("./../../resources/gstData.json");

class ItemRow extends Component {
  state: ItemRowState;

  constructor(props: ItemRowProps) {
    super(props);
    this.state = {
      obj: {
        name: "",
        description: "",
        price: undefined,
        quantity: undefined,
        gst: 5.0,
      },
    };
  }

  componentDidMount() {
    const { items } = this.props;
    if (items[this.props.itemId] != null) {
      this.setState({
        obj: items[this.props.itemId],
      });
    }
  }

  handleChange = (e: Event) => {
    if (e.target instanceof HTMLInputElement) {
      const { obj } = this.state;
      const { name, value } = e.target;
      obj[name] = value;
      this.setState({
        obj,
      });
      const itemId = this.props.itemId;
      this.props.setItem(itemId, obj);
    }
  };

  getZeroDiv() {
    return <div style={this.getInputStyle()}>0</div>;
  }

  getInputStyle() {
    return this.props.width >= 700
      ? ITEM_ROW_STYLE.inputStyle
      : ITEM_ROW_RESPONSIVE_STYLE.inputStyle;
  }

  getGstDropDownStyle() {
    return this.props.width >= 700
      ? ITEM_ROW_STYLE.gstDropDownStyle
      : ITEM_ROW_RESPONSIVE_STYLE.gstDropDownStyle;
  }

  getItemRowStyle() {
    return this.props.width >= 700
      ? ITEM_ROW_STYLE.itemRow
      : ITEM_ROW_RESPONSIVE_STYLE.itemRow;
  }

  updateGstState(val: number) {
    let obj = this.state.obj;
    obj.gst = val;
    this.setState({
      obj: obj,
    });
    const itemId = this.props.itemId;
    this.props.setItem(itemId, obj);
  }

  gstChange = (val: { value?: string, label?: string }) => {
    if (val) {
      this.updateGstState(val.value);
      this.props.setGst(val);
    } else {
      this.updateGstState(5.0);
      this.props.setGst({ value: 5.0, label: "5" });
    }
  };

  getGstDropDown() {
    return (
      <div>
        <Select
          name="gst"
          value={this.state.obj.gst}
          options={GST_DATA}
          onChange={this.gstChange}
          style={this.getGstDropDownStyle()}
        />
      </div>
    );
  }

  getInputBox(name, value, description) {
    return (
      <input
        style={this.getInputStyle()}
        name={name}
        type="text"
        value={value}
        onChange={this.handleChange}
        placeholder={description}
      />
    );
  }

  render() {
    const { obj: data } = this.state;

    let subAmountDiv = this.getZeroDiv();
    let gstAmountDiv = this.getZeroDiv();
    let totalAmountDiv = this.getZeroDiv();

    if (data.quantity && data.price && data.gst) {
      data.subAmount = data.quantity * data.price;
      data.gstAmount = (data.subAmount * data.gst) / 100;
      data.totalAmount = data.subAmount + data.gstAmount;
      subAmountDiv = (
        <div style={this.getInputStyle()}>{data.subAmount.toFixed(2)}</div>
      );

      gstAmountDiv = (
        <div style={this.getInputStyle()}>{data.gstAmount.toFixed(2)}</div>
      );

      totalAmountDiv = (
        <div style={this.getInputStyle()}>{data.totalAmount.toFixed(2)}</div>
      );
    }

    return (
      <table id="customers">
        <tr>
          <td>{this.getInputBox("name", data.name, "Item Name")}</td>
          <td>
            {this.getInputBox("description", data.description, "Description")}
          </td>
          <td>{this.getInputBox("quantity", data.quantity, "Quantity")}</td>
          <td>{this.getInputBox("price", data.price, "Price")}</td>
          <td>{subAmountDiv}</td>
          <td>{this.getGstDropDown()}</td>
          <td>{gstAmountDiv}</td>
          <td>{totalAmountDiv}</td>
        </tr>
      </table>
    );
  }
}

function mapStateToProps(state, ownProps) {
  return {
    items: state.items,
    width: state.width,
    gst: state.gst,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    setItem: (id, value) => dispatch(setItem(id, value)),
    setGst: (gst) => dispatch(setGst(gst)),
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(ItemRow);
