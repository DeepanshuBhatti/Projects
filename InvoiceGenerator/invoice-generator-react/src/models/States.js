export type InvoiceState = {
  issueFocused: boolean,
  dueFocused: boolean,
};

export type ItemRowState = {
  obj: {
    name: string,
    description: string,
    quantity?: string,
    price?: string,
    subAmount?: number,
    gst?: number,
    gstAmount?: number,
    totalAmount?: number,
  },
};

export type InfoState = {
  discount?: string,
  tax?: string,
  amountPaid?: string,
  vat?: string,
};

export type InvoiceDetailState = {
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
};
