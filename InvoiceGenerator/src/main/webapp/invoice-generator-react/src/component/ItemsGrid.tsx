import React, { useState } from "react";
import { observer } from "mobx-react";
import ItemRow from "./ItemRow";

const ItemsGrid: React.FC = () => {
  const [rows, setRows] = useState([<ItemRow />]);

  const addRow = () => {
    setRows([...rows, <ItemRow />]);
  };

  const removeRow = () => {
    rows.splice(rows.length - 1, 1);
    setRows([...rows]);
  };

  let ROW_HEADER_DIV = ["Name", "Description", "Quantity", "Price"];

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
      {rows.map((s: any, i: number) => {
        return <div key={i}>{s}</div>;
      })}
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
