import React from "react";
import "./App.css";
import CitySearch from "./component/CitySearch";

function App() {
  return (
    <div className="App">
      <CitySearch id={1} name="Delhi" />
    </div>
  );
}

export default App;
