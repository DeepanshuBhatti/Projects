// @flow
import { SET_INVOICE_DETAILS } from "../constants";
import { InvoiceDetailState } from "./../models/States";

export type Action = {
  name: string,
  val: string,
};

const initialState: InvoiceDetailState = {
  to: "",
  from: "",
  addressTo: "",
  addressFrom: "",
  phoneTo: "",
  phoneFrom: "",
  emailTo: "",
  emailFrom: "",
  invoiceNumber: "001",
  job: "",
  invoiceType: "Invoice",
};

export default function invoiceDetailsReducer(
  state: InvoiceDetailState = initialState,
  action: Action
) {
  if (action.type === SET_INVOICE_DETAILS) {
    return { ...state, [action.name]: action.val };
  }
  return state;
}
