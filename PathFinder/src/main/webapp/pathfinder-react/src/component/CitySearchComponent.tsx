import React, { useState, useEffect } from "react";
import { observer } from "mobx-react";
import { fetchCityInfo, fetchShortestDistance } from "../Api";
import { CityInfo } from "../model/CityInfo";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

const CitySearchComponent: React.FC = () => {
  const [cities, setCities] = useState([]);
  const [distance, setDistance] = useState(null);
  const [source, setSource] = useState(null);
  const [destination, setDestination] = useState(null);

  useEffect(() => {
    fetchCityInfo().then((res) => {
      setCities(res);
    });
  }, []);

  const onSearchClick = () => {
    fetchShortestDistance(source, destination).then((res) => {
      setDistance(res);
    });
  };

  const onClear = () => {
    setDistance(null);
    setSource(null);
    setDestination(null);
  };

  const getCityNames = (cities: Array<CityInfo>) => {
    return cities.map(function (item: CityInfo) {
      return item.name;
    });
  };

  const getSourceCitiesDropDown = () => {
    let cityNames = getCityNames(cities);
    return (
      <Dropdown
        options={cityNames}
        onChange={(e) => setSource(e.value)}
        value={source}
        placeholder="Select an option"
      />
    );
  };

  const getDestinationCitiesDropDown = () => {
    let cityNames = getCityNames(cities);
    return (
      <Dropdown
        options={cityNames}
        onChange={(e) => setDestination(e.value)}
        value={destination}
        placeholder="Select an option"
      />
    );
  };

  return (
    <div>
      <table>
        <tbody>
          <tr>
            <td>
              {cities != null && cities.length > 0 && getSourceCitiesDropDown()}
            </td>
            <td>
              {cities != null &&
                cities.length > 0 &&
                getDestinationCitiesDropDown()}
            </td>
          </tr>
          <tr>
            <td>
              <button onClick={onSearchClick} className="button">
                <span>Search Distance</span>
              </button>
            </td>
            <td>
              <button onClick={onClear} className="button">
                <span>Clear</span>
              </button>
            </td>
          </tr>
          <tr>
            <td colSpan={2}>
              {distance != null && (
                <div>
                  Distance between {source} and {destination} is {distance} KM
                </div>
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
