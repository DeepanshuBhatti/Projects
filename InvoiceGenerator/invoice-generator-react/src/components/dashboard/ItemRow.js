// @flow
import React, { Component } from "react";
import { setItem } from "../../actions";
import { connect } from "react-redux";
import { ItemRowProps } from "./../../models/Props";
import { ItemRowState } from "./../../models/States";
import {
  ITEM_ROW_RESPONSIVE_STYLE,
  ITEM_ROW_STYLE,
} from "./../../constants/Styles";

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

  getItemRowStyle() {
    return this.props.width >= 700
      ? ITEM_ROW_STYLE.itemRow
      : ITEM_ROW_RESPONSIVE_STYLE.itemRow;
  }

  render() {
    const { obj: data } = this.state;
    let subAmount = this.getZeroDiv();
    let gstAmount = this.getZeroDiv();
    let totalAmount = this.getZeroDiv();

    if (
      data.quantity &&
      data.price &&
      data.gst &&
      parseFloat(data.quantity) *
        parseFloat(data.price) *
        (1 + data.gst / 100) >
        0
    ) {
      let subAmountValue = data.quantity * data.price;
      let gstAmountValue = (data.quantity * data.price * data.gst) / 100;
      let totalAmountValue = subAmountValue + gstAmountValue;

      subAmount = (
        <div style={this.getInputStyle()}>{subAmountValue.toFixed(2)}</div>
      );

      gstAmount = (
        <div style={this.getInputStyle()}>{gstAmountValue.toFixed(2)}</div>
      );

      totalAmount = (
        <div style={this.getInputStyle()}>{totalAmountValue.toFixed(2)}</div>
      );
    }

    return (
      <div style={this.getItemRowStyle()} className="item-row">
        <input
          style={this.getInputStyle()}
          name="name"
          type="text"
          value={data.name}
          onChange={this.handleChange}
          placeholder="Item Name"
        />
        <input
          style={this.getInputStyle()}
          name="description"
          type="text"
          value={data.description}
          onChange={this.handleChange}
          placeholder="Description"
        />
        <input
          style={this.getInputStyle()}
          name="quantity"
          type="text"
          value={data.quantity}
          onChange={this.handleChange}
          placeholder="Quantity"
        />
        <input
          style={this.getInputStyle()}
          name="price"
          type="text"
          value={data.price}
          onChange={this.handleChange}
          placeholder="Price"
        />
        {subAmount}
        <input
          style={this.getInputStyle()}
          name="gst"
          type="text"
          value={data.gst}
          onChange={this.handleChange}
          placeholder="GST"
        />
        {gstAmount}
        {totalAmount}
      </div>
    );
  }
}

function mapStateToProps(state, ownProps) {
  return {
    items: state.items,
    width: state.width,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    setItem: (id, value) => dispatch(setItem(id, value)),
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(ItemRow);
