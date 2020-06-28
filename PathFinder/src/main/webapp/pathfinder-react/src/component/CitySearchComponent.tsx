import React, { useState, useEffect } from "react";
import { observer } from "mobx-react";
import { fetchCityInfo, fetchShortestDistance } from "../Api";
import CityDropDown from "./CityDropDown";
import { CityInfo } from "../model/CityInfo";

const CitySearchComponent: React.FC = () => {
  const [state, setState] = useState({
    cities: [],
    source: "",
    destination: "",
    distance: null,
  });

  const onSearchClick = () => {
    fetchShortestDistance("delhi", "indore").then((res) => {
      console.log(res);
      setState({
        cities: state.cities,
        source: state.source,
        destination: state.destination,
        distance: res,
      });
    });
  };

  useEffect(() => {
    fetchCityInfo().then((res) =>
      setState({
        cities: res,
        source: "",
        destination: "",
        distance: null,
      })
    );
  }, []);

  const onClear = () => {
    setState({
      cities: [],
      source: "",
      destination: "",
      distance: null,
    });
  };

  const getSourceCitiesDropDown = () => {
    let sourceCities = state.cities;
    return getCityDropDowns(sourceCities);
  };

  const getDestinationCitiesDropDown = () => {
    let destinationCities = state.cities;
    if (state.source !== "") {
      destinationCities = destinationCities.filter(
        (item) => item.name !== state.source
      );
    }
    return getCityDropDowns(destinationCities);
  };

  const getCityDropDowns = (cities: Array<CityInfo>) => {
    return <CityDropDown cities={cities} />;
  };

  return (
    <div>
      <table>
        <tbody>
          <tr>
            <td>
              {state.cities != null &&
                state.cities.length > 0 &&
                getSourceCitiesDropDown()}
            </td>
            <td>
              {state.cities != null &&
                state.cities.length > 0 &&
                getDestinationCitiesDropDown()}
            </td>
          </tr>
          <tr>
            <td>
              <button onClick={onSearchClick} className="button">
                <span>Search Distance</span>
              </button>
            </td>
          </tr>
          <tr>
            <td>
              {state.distance != null && (
                <div>Distance is {state.distance} KM</div>
              )}
            </td>
          </tr>
        </tbody>
      </table>
      <hr />
    </div>
  );
};

export default observer(CitySearchComponent);
