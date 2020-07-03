import React, { useState } from "react";
import { observer } from "mobx-react";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

const ItemRow: React.FC = () => {
  const [gst, setGst] = useState(5);
  const [data, setData] = useState({
    name: "",
    description: "",
    quantity: 0,
    price: 0,
    subAmount: 0,
    gstAmount: 0,
    totalAmount: 0,
  });

  const getZeroDiv = () => {
    return <div>0</div>;
  };

  let GST_DATA = ["5", "12", "18", "28"];

  const getGstDropDown = () => {
    return (
      <div>
        <Dropdown
          options={GST_DATA}
          onChange={(e) => setGst(parseInt(e.value))}
          value="5"
          placeholder="Select an option"
        />
      </div>
    );
  };

  const getItemNameInput = () => {
    return (
      <input
        name="itemName"
        type="text"
        value={data.name}
        onChange={onNameChange}
        placeholder="Name"
      />
    );
  };

  const onNameChange = (e: any) => {
    let name = String(e.currentTarget.value);
    setData({
      ...data,
      name: name,
    });
  };

  const getItemDescriptionInput = () => {
    return (
      <input
        name="itemDescription"
        type="text"
        value={data.description}
        onChange={onDescriptionChange}
        placeholder="Description"
      />
    );
  };

  const onDescriptionChange = (e: any) => {
    let description = String(e.currentTarget.value);
    setData({
      ...data,
      description: description,
    });
  };

  const onQuantityChange = (e: any) => {
    let quantity = parseInt(e.currentTarget.value);
    let subAmount = getSubAmount(quantity, data.price);
    let gstAmount = getGstAmount(subAmount, gst);
    let totalAmount = getTotalAmount(subAmount, gstAmount);
    setData({
      ...data,
      quantity: quantity,
      subAmount: subAmount,
      gstAmount: gstAmount,
      totalAmount: totalAmount,
    });
  };

  const getQuantityInput = () => {
    return (
      <input
        name="quantity"
        type="number"
        value={data.quantity}
        onChange={onQuantityChange}
        placeholder="0"
      />
    );
  };

  const onPriceChange = (e: any) => {
    let price = parseInt(e.currentTarget.value);
    let subAmount = getSubAmount(data.quantity, price);
    let gstAmount = getGstAmount(subAmount, gst);
    let totalAmount = getTotalAmount(subAmount, gstAmount);
    setData({
      ...data,
      price: price,
      subAmount: subAmount,
      gstAmount: gstAmount,
      totalAmount: totalAmount,
    });
  };

  const getPriceInput = () => {
    return (
      <input
        name="price"
        type="number"
        value={data.price}
        onChange={onPriceChange}
        placeholder="0"
      />
    );
  };

  const getSubAmount = (quantity: number, price: number) => {
    return quantity * price;
  };

  const getGstAmount = (subAmount: number, gst: number) => {
    return (subAmount * gst) / 100;
  };

  const getTotalAmount = (subAmount: number, gstAmount: number) => {
    return subAmount + gstAmount;
  };

  let subAmountDiv = getZeroDiv();
  let gstAmountDiv = getZeroDiv();
  let totalAmountDiv = getZeroDiv();

  subAmountDiv = <div>{data.subAmount.toFixed(2)}</div>;

  gstAmountDiv = <div>{data.gstAmount.toFixed(2)}</div>;

  totalAmountDiv = <div>{data.totalAmount.toFixed(2)}</div>;

  return (
    <table>
      <tbody>
        <tr>
          <td>{getItemNameInput()}</td>
          <td>{getItemDescriptionInput()}</td>
          <td>{getQuantityInput()}</td>
          <td>{getPriceInput()}</td>
          <td>{subAmountDiv}</td>
          <td>{getGstDropDown()}</td>
          <td>{gstAmountDiv}</td>
          <td>{totalAmountDiv}</td>
        </tr>
      </tbody>
    </table>
  );
};

export default observer(ItemRow);
