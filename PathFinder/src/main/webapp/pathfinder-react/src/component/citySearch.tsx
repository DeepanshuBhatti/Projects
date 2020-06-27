import React, { Component } from "react";
import { CityInfo } from "../model/CityInfo";
import { fetchCityInfo, fetchShortestDistance } from "./../Api";

export class CitySearch extends Component<CityInfo> {
  cityRef: React.RefObject<HTMLInputElement>;
  state: any = {
    cities: [],
  };
  constructor(props: CityInfo) {
    super(props);
    this.cityRef = React.createRef();
  }

  onSearchClick = () => {
    fetchCityInfo().then((res) =>
      this.setState({
        cities: res,
      })
    );

    fetchShortestDistance("delhi", "indore").then((res) => console.log(res));
  };

  onClear = () => {
    this.setState({
      cities: [],
    });
  };

  getCitiesData() {
    return this.state.cities;
  }

  getTableForAllCities() {
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
            {this.getCitiesData().map((s: CityInfo, i: number) => {
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
  }

  render() {
    return (
      <div>
        <button onClick={this.onSearchClick} className="button">
          <span>Display City</span>
        </button>
        <button onClick={this.onClear} className="button">
          <span>Clear</span>
        </button>
        {this.state.cities != null &&
          this.state.cities.length > 0 &&
          this.getTableForAllCities()}
      </div>
    );
  }
}

export default CitySearch;
