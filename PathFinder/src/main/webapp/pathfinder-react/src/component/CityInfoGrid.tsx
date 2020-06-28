import React from "react";
import { observer } from "mobx-react";
import { CityInfo } from "../model/CityInfo";
import { CityInfoProps } from "./../model/Props";

const CityInfoGrid: React.FC<CityInfoProps> = (props: CityInfoProps) => {
  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>City Id</th>
            <th>City Name</th>
            <th>City Abbrev</th>
          </tr>
        </thead>
        <tbody>
          {props.cities.map((s: CityInfo, i: number) => {
            return (
              <tr key={i}>
                <td>{s.id}</td>
                <td>{s.name}</td>
                <td>{s.abbrev}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default observer(CityInfoGrid);
