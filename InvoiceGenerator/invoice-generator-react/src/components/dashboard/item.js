// @flow
import React, { Component } from "react";
import {
  SortableContainer,
  SortableElement,
  SortableHandle,
  arrayMove,
} from "react-sortable-hoc";
import ItemRow from "./ItemRow";
import { addItem, setItemsOrder, removeItem, setWidth } from "../../actions";
import { connect } from "react-redux";
import { ITEM_STYLE } from "./../../constants/Styles";
import { ROW_HEADER_DIV } from "./../../constants";

const DragHandle = SortableHandle(() => (
  <span>
    <i
      className="fa fa-bars"
      aria-hidden="true"
      style={ITEM_STYLE.barStyle}
    ></i>
  </span>
));

const SortableItem = SortableElement(({ value, onRemove }: Object) => {
  return (
    <div style={ITEM_STYLE.listWrapper}>
      <li style={ITEM_STYLE.listStyle}>
        <ItemRow itemId={value} />
        <DragHandle />
      </li>
      <a onClick={() => onRemove(value)}>
        <i
          style={ITEM_STYLE.deleteButtonStyle}
          className="fa fa-times"
          aria-hidden="true"
        ></i>
      </a>
    </div>
  );
});

const SortableList = SortableContainer(({ order, items, onRemove }: Object) => {
  return (
    <ul>
      {order.map((value, index) => (
        <SortableItem
          key={value}
          index={index}
          value={value}
          onRemove={onRemove}
        />
      ))}
    </ul>
  );
});

class Item extends Component {
  onSortEnd = ({ oldIndex, newIndex }: Object) => {
    const { order } = this.props;
    const newOrder = arrayMove(order, oldIndex, newIndex);
    this.props.setItemsOrder(newOrder);
  };

  addInput = () => {
    const order = this.props.order;
    let maxOrderValue = order.length === 0 ? 0 : parseInt(Math.max(...order));
    this.props.addItem(parseInt(maxOrderValue + 1), null);
  };

  componentDidMount() {
    window.addEventListener("resize", () => {
      this.props.setWidth(window.innerWidth);
    });
  }

  render() {
    let { order, items } = this.props;

    return (
      <div className="item">
        <div className="item__head">
          <table id="customers">
            <tr>{ROW_HEADER_DIV}</tr>
          </table>
        </div>
        <SortableList
          order={order}
          items={items}
          onSortEnd={this.onSortEnd}
          useDragHandle={true}
          onRemove={this.props.removeItem}
        />
        <a onClick={this.addInput} className="solid-btn solid-btn--new">
          <i className="fa fa-plus-circle" aria-hidden="true"></i>
          Add Row
        </a>
      </div>
    );
  }
}

function mapStateToProps(state, ownProps) {
  return {
    items: state.items,
    order: state.order,
    width: state.width,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    setItemsOrder: (item) => dispatch(setItemsOrder(item)),
    addItem: (id, value) => dispatch(addItem(id, value)),
    removeItem: (id) => dispatch(removeItem(id)),
    setWidth: (width) => dispatch(setWidth(width)),
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(Item);
