import React from "react";
import "./App.css";
import CitySearch from "./component/citySearch";

function App() {
  return (
    <div className="App">
      <CitySearch cityId={1} cityName="Mumbai" />
    </div>
  );
}

export default App;
