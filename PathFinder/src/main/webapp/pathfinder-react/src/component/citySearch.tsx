import React, { Component } from "react";
import { CityInfo } from "../model/CityInfo";
import jsonData from "./../data/data.json";

export class CitySearch extends Component<CityInfo> {
  cityRef: React.RefObject<HTMLInputElement>;
  state: any = {
    cities: [],
    distance: [],
  };
  constructor(props: CityInfo) {
    super(props);
    this.cityRef = React.createRef();
  }

  onSearchClick = () => {
    this.setState({
      cities: jsonData.tables.cities,
      distance: jsonData.tables.distance,
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
    const currentCity: CityInfo = this.props;
    return (
      <div>
        <button onClick={this.onSearchClick} className="button">
          <span>Display City</span>
        </button>
        {this.getTableForAllCities()}
      </div>
    );
  }
}

export default CitySearch;
