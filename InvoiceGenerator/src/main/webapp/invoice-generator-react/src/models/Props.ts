import ItemsStore from "../stores/ItemsStore";
import { ItemInfo } from "./ItemInfo";

export interface ItemsGridProps {
  store: ItemsStore;
}

export interface ItemRowProps {
  data: ItemInfo;
}
