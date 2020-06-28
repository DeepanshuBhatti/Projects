import React from "react";
import { observer } from "mobx-react";
import { CityInfo } from "../model/CityInfo";
import { CityInfoProps } from "./../model/Props";

const CityDropDown: React.FC<CityInfoProps> = (props: CityInfoProps) => {
  return (
    <div>
      <select name="cities" id="cities">
        {props.cities.map((s: CityInfo, i: number) => {
          return <option value={s.id}>{s.name}</option>;
        })}
      </select>
    </div>
  );
};

export default observer(CityDropDown);
