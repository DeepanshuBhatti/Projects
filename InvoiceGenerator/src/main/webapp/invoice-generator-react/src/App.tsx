import React from "react";
import ItemsGrid from "./component/ItemsGrid";
import { useStores } from "./useStores";
import "./App.css";

function App() {
  const { rootStore } = useStores();
  const { itemsStore } = rootStore;

  return (
    <div className="App">
      <h1>Deepanshu is the Best</h1>
      <ItemsGrid store={itemsStore} />
    </div>
  );
}

export default App;
