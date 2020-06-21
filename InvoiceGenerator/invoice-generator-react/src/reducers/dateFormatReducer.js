// @flow
import { SET_DATE_FORMAT } from "../constants";
import { Action } from "../actions";

type State = Object;

export default function dateFormatReducer(
  state: State = { value: "DD/MM/YYYY", label: "DD/MM/YYYY" },
  action: Action
) {
  if (action.type === SET_DATE_FORMAT) {
    return action.dateFormat;
  }
  return state;
}
