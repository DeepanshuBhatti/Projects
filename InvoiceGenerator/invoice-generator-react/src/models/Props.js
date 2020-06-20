export type InvoiceProps = {
  currency: Object,
  items: Object,
  addInfo: {
    discount: ?number,
    tax: ?number,
    amountPaid: ?number,
    vat: ?number,
  },
  invoiceDetails: {
    to: string,
    from: string,
    addressTo: string,
    addressFrom: string,
    phoneTo: string,
    phoneFrom: string,
    emailTo: string,
    emailFrom: string,
    invoiceNumber: string,
    job: string,
    invoiceType: string,
  },
  status: { value: ?string, label: ?string },
  paidStatus: ?boolean,
  issueDate: ?Date,
  dueDate: ?Date,
  setInvoiceDetails: Function,
  setStatus: Function,
  setIssueDate: Function,
  setDueDate: Function,
};

export type ItemRowProps = {
  items: Object,
  itemId: number,
  setItem: Function,
  width: number,
  gst: Object,
  setGst: Function,
};

export type SideNavProps = {
  setAddInfo: Function,
  setPaidStatus: Function,
  setCurrency: Function,
  setDateFormat: Function,
  setDownloadStatus: Function,
  currency: Object,
  addInfo: {
    discount: ?number,
    tax: ?number,
    amountPaid: ?number,
    vat: ?number,
  },
  paidStatus: ?boolean,
  downloadStatus: ?boolean,
  dateFormat: ?string,
};
