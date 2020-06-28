import React, { useState } from "react";
import { observer } from "mobx-react";
import { fetchCityInfo } from "../Api";
import CityInfoGrid from "./CityInfoGrid";

const CityComponent: React.FC = () => {
  const [state, setState] = useState({
    cities: [],
  });

  const onSearchClick = () => {
    fetchCityInfo().then((res) =>
      setState({
        cities: res,
      })
    );
  };

  const onClear = () => {
    setState({
      cities: [],
    });
  };

  const getTableForAllCities = () => {
    return <CityInfoGrid cities={state.cities} />;
  };

  return (
    <div>
      <button onClick={onSearchClick} className="button">
        <span>Display City</span>
      </button>
      <button onClick={onClear} className="button">
        <span>Clear</span>
      </button>
      {state.cities != null &&
        state.cities.length > 0 &&
        getTableForAllCities()}
      <hr />
    </div>
  );
};

export default observer(CityComponent);
