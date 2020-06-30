import React, { useState } from "react";
import { observer } from "mobx-react";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

const ItemRow: React.FC = () => {
  const [gst, setGst] = useState(5);
  const [itemName, setItemName] = useState(null);
  const [itemDescription, setItemDescription] = useState(null);
  const [quantity, setQuantity] = useState(0);
  const [price, setPrice] = useState(0);
  const [subAmount, setSubAmount] = useState(0);
  const [gstAmount, setGstAmount] = useState(0);
  const [totalAmount, setTotalAmount] = useState(0);

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
          value="5.0"
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
        value={itemName}
        onChange={(e) => setItemName(e.currentTarget.value)}
        placeholder="Name"
      />
    );
  };

  const getItemDescriptionInput = () => {
    return (
      <input
        name="itemDescription"
        type="text"
        value={itemDescription}
        onChange={(e) => setItemDescription(e.currentTarget.value)}
        placeholder="Description"
      />
    );
  };

  const onQuantityChange = (e: any) => {
    setQuantity(parseInt(e.currentTarget.value));
    calculateAttributes();
  };

  const getQuantityInput = () => {
    return (
      <input
        name="quantity"
        type="number"
        value={quantity}
        onChange={onQuantityChange}
        placeholder="0"
      />
    );
  };

  const onPriceChange = (e: any) => {
    setPrice(parseInt(e.currentTarget.value));
    calculateAttributes();
  };

  const getPriceInput = () => {
    return (
      <input
        name="price"
        type="number"
        value={price}
        onChange={onPriceChange}
        placeholder="0"
      />
    );
  };

  const calculateAttributes = () => {
    console.log(quantity);
    console.log(price);
    console.log(gst);
    if (quantity && price && gst) {
      let x = quantity * price;
      setSubAmount(x);
      setGstAmount((subAmount * gst) / 100);
      setTotalAmount(subAmount + gstAmount);
    }
  };

  let subAmountDiv = getZeroDiv();
  let gstAmountDiv = getZeroDiv();
  let totalAmountDiv = getZeroDiv();

  subAmountDiv = <div>{subAmount.toFixed(2)}</div>;

  gstAmountDiv = <div>{gstAmount.toFixed(2)}</div>;

  totalAmountDiv = <div>{totalAmount.toFixed(2)}</div>;

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
