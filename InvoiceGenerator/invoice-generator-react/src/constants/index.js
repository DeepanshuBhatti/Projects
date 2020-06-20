// @flow

// Item.js
export const SORT_ITEMS: string = "SORT_ITEMS";
export const ADD_ITEM: string = "ADD_ITEM";
export const REMOVE_ITEM: string = "REMOVE_ITEM";

// ItemRow.js
export const SET_ITEM: string = "SET_ITEM";
export const SET_WIDTH: string = "SET_WIDTH";
export const SET_GST: string = "SET_GST";

// SideNav.js
export const SET_CURRENCY: string = "SET_CURRENCY";
export const SET_DATE_FORMAT: string = "SET_DATE_FORMAT";
export const SET_PAID_STATUS: string = "SET_PAID_STATUS";
export const SET_ADDINFO: string = "SET_ADDINFO";

// Invoice.js
export const SET_ISSUE_DATE: string = "SET_ISSUE_DATE";
export const SET_DUE_DATE: string = "SET_DUE_DATE";
export const SET_STATUS: string = "SET_STATUS";
export const SET_INVOICE_DETAILS: string = "SET_INVOICE_DETAILS";
export const SET_DOWNLOAD_STATUS: string = "SET_DOWNLOAD_STATUS";

export const INVOICE_OPTIONS = [
  { value: "paid", label: "Paid" },
  { value: "due", label: "Due" },
  { value: "overdue", label: "Overdue" },
  { value: "onhold", label: "On Hold" },
];

export const DATE_OPTIONS = [
  { value: "MM/DD/YYYY", label: "MM/DD/YYYY" },
  { value: "DD/MM/YYYY", label: "DD/MM/YYYY" },
  { value: "YYYY/MM/DD", label: "YYYY/MM/DD" },
];
