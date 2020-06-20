// @flow
import { SET_ADDINFO } from "../constants";
import { Action } from "../actions";
import { InfoState } from "./../models/States";

export default function addInfoReducer(
  state: InfoState = { discount: "0", tax: "0", amountPaid: "0", vat: "0" },
  action: Action
): InfoState {
  if (action.type === SET_ADDINFO) {
    return {
      discount: action.discount,
      tax: action.tax,
      amountPaid: action.amountPaid,
      vat: action.vat,
    };
  }
  return state;
}
