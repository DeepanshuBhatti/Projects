import React, { useState } from "react";
import { observer } from "mobx-react";
import ItemRow from "./ItemRow";
import { ItemsGridProps } from "../models/Props";

const ItemsGrid: React.FC<ItemsGridProps> = (props: ItemsGridProps) => {
  let id = props.store.getSize() + 1;
  const [rows, setRows] = useState([<ItemRow store={props.store} id={id} />]);

  const addRow = () => {
    id = props.store.getSize() + 1;
    setRows([...rows, <ItemRow store={props.store} id={id} />]);
  };

  const removeRow = () => {
    rows.splice(rows.length - 1, 1);
    setRows([...rows]);
  };

  let ROW_HEADER_DIV = [
    "Name",
    "Description",
    "Quantity",
    "Price",
    "Amount",
    "GST %",
    "GST Amount",
    "Total",
  ];

  return (
    <div>
      <table>
        <thead>
          <tr>
            {ROW_HEADER_DIV.map((s: string, i: number) => {
              return <th key={i}>{s}</th>;
            })}
          </tr>
        </thead>
      </table>
      <div className="ex3">
        {rows.map((s: any, i: number) => {
          return <div key={i}>{s}</div>;
        })}
      </div>
      <button onClick={addRow} className="button">
        <span>Add Row</span>
      </button>
      <button onClick={removeRow} className="button">
        <span>Remove Row</span>
      </button>
    </div>
  );
};

export default observer(ItemsGrid);
