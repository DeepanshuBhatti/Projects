import RootStore from "./stores/RootStore";
import { createContext, useContext } from "react";

const storesContext = createContext({
  rootStore: new RootStore(),
});

export const useStores = () => useContext(storesContext);
