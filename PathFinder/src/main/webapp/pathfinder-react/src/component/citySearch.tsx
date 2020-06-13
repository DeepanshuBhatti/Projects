import React, { Component } from "react";
import { CityInfo } from "../model/CityInfo";

export class CitySearch extends Component<CityInfo> {
  render() {
    const currentCity: CityInfo = this.props;
    return (
      <div>
        <p>
          City {currentCity.cityId} is {currentCity.cityName}
        </p>
      </div>
    );
  }
}

export default CitySearch;
