import RootStore from "./RootStore";
import { observable, action } from "mobx";
import { ItemInfo } from "./../models/ItemInfo";

export class ItemsStore {
  rootStore: RootStore;

  constructor(rootStore: RootStore) {
    this.rootStore = rootStore;
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

  getSubAmountSum() {
    let sum = 0;
    this.itemsInfoList.map((value, index) => {
      sum = sum + value.subAmount;
      return index;
    });
    return sum;
  }

  getTotalAmountSum() {
    let sum = 0;
    this.itemsInfoList.map((value, index) => {
      sum = sum + value.totalAmount;
      return index;
    });
    return sum;
  }

  getGstAmountSum() {
    let sum = 0;
    this.itemsInfoList.map((value, index) => {
      sum = sum + value.gstAmount;
      return index;
    });
    return sum;
  }
}

export default ItemsStore;
