import ItemsStore from "./ItemsStore";

export class RootStore {
  itemsStore: ItemsStore;

  constructor() {
    this.itemsStore = new ItemsStore(this);
  }
}

export default RootStore;
