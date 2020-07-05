import RootStore from "./RootStore";
import { observable, action } from "mobx";
import { ItemInfo } from "./../models/ItemInfo";

export class ItemsStore {
  rootStore: RootStore;

  constructor(rootStore: RootStore) {
    this.rootStore = rootStore;
  }

  getDefaultItemInfo(id: number) {
    let x: ItemInfo = {
      id: id,
      name: "",
      description: "",
      quantity: 0,
      price: 0,
      subAmount: 0,
      gstAmount: 0,
      totalAmount: 0,
    };
    return x;
  }

  @observable itemsInfoList: Array<ItemInfo> = [];

  @action addItem(newItem: ItemInfo) {
    this.itemsInfoList = this.itemsInfoList.filter((x) => x.id !== newItem.id);
    this.itemsInfoList.push(newItem);
  }

  @action removeItem(itemId: number) {
    this.itemsInfoList.splice(itemId);
  }

  getSize(): number {
    return this.itemsInfoList.length;
  }

  isItemAdded(id: number): boolean {
    let x: Array<number> = [];

    this.itemsInfoList.map((value: ItemInfo, key: number) => {
      x.push(value.id);
    });

    return x.some((item) => id === item);
  }
}

export default ItemsStore;
