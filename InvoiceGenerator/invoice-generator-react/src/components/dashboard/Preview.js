// @flow
import React, { Component } from "react";
import { connect } from "react-redux";
import moment from "moment";
import { ROW_HEADER_DIV } from "./../../constants";

class Preview extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    const app = document.querySelector("#app");
    if (app) {
      app.className = "fix-navbar";
    }
    window.scrollTo(0, 0);
    if (this.props.downloadStatus) {
      this.pdfToHTML();
    }
  }

  componentWillUnmount() {
    const app = document.querySelector("#app");
    if (app) {
      app.className = "";
    }
  }

  render() {
    const {
      items,
      addInfo,
      invoiceDetails,
      issueDate,
      dueDate,
      dateFormat,
      paidStatus,
    } = this.props;
    let currencySymbol = this.props.currency["value"];
    let discountElement;
    let vatElement;
    let subTotal = 0;
    let job;
    let itemElement;
    let amountPaidElement;

    if (invoiceDetails.job.length > 0) {
      job = (
        <div className="info">
          <label htmlFor="job">Job</label>
          {invoiceDetails.job}
        </div>
      );
    }

    if (items) {
      itemElement = Object.keys(items).map((key, index) => {
        let data = items[key];
        if (data) {
          let subAmount = 0;
          let gstAmount = 0;
          let totalAmount = 0;
          let gstPercent = parseFloat(data["gst"] || 0);
          let price = parseFloat(data["price"] || 0);
          let quantity = parseFloat(data["quantity"] || 0);

          if (price > 0 && quantity > 0 && gstPercent > 0) {
            subAmount = quantity * price;
            gstAmount = (subAmount * gstPercent) / 100;
            totalAmount = subAmount + gstAmount;
          }

          return (
            <div key={index} className="invoice__item-list__item">
              <div>
                <span>{data["name"]}</span>
              </div>
              <div>
                <span>{data["description"]}</span>
              </div>
              <div>
                <span>{data["quantity"]}</span>
              </div>
              <div>
                <span>
                  {currencySymbol} {data["price"]}
                </span>
              </div>
              <div>
                <span>
                  {currencySymbol} {subAmount}
                </span>
              </div>
              <div>
                <span>{data["gst"]}</span>
              </div>
              <div>
                <span>
                  {currencySymbol} {gstAmount}
                </span>
              </div>
              <div>
                <span>
                  {currencySymbol} {totalAmount}
                </span>
              </div>
            </div>
          );
        } else {
          return;
        }
      });
    }

    let amountTotal = 0;
    subTotal = 0;
    let gstTotal = 0;

    let discountPercent = addInfo["discount"] || 0;
    let vatPercent = addInfo["vat"] || 0;

    if (discountPercent > 0) {
      discountElement = (
        <div>
          <span>Discount</span>
          <h2>{discountPercent} %</h2>
        </div>
      );
    }

    if (vatPercent > 0) {
      vatElement = (
        <div>
          <span>VAT</span>
          <h2>{vatPercent} %</h2>
        </div>
      );
    }

    if (addInfo["amountPaid"] && addInfo["amountPaid"] > 0 && paidStatus) {
      amountPaidElement = (
        <div>
          <span>Paid to Date</span>
          <h2>
            {currencySymbol} {addInfo["amountPaid"]}
          </h2>
        </div>
      );
    }
    for (let key in items) {
      if (items.hasOwnProperty(key)) {
        let quantity = parseFloat(items[key]["quantity"] || 0);
        let price = parseFloat(items[key]["price"]) || 0;
        let gst = parseFloat(items[key]["gst"] || 0);

        if (quantity > 0 && price > 0 && gst > 0) {
          subTotal = subTotal + quantity * price;
          gstTotal = gstTotal + (quantity * price * gst) / 100;
        }
      }
    }

    amountTotal =
      subTotal +
      gstTotal -
      (subTotal * discountPercent) / 100 +
      (subTotal * vatPercent) / 100;
    return (
      <div className="dashboard">
        <div className="preview-wrapper">
          <div className="preview">
            <div className="invoice">
              <link rel="stylesheet" href="/assets/css/styles.css" />
              <div className="invoice__header">
                <h4>{this.props.status["label"]}</h4>
                <h2>{invoiceDetails.invoiceType}</h2>
              </div>

              <div className="invoice__info">
                <div className="info">
                  <label htmlFor="date">Date</label>
                  {moment(issueDate).format(dateFormat["value"])}
                </div>

                <div className="info">
                  <label htmlFor="date">Due Date</label>
                  {moment(dueDate).format(dateFormat["value"])}
                </div>

                <div className="info">
                  <label htmlFor="invoice">Invoice #</label>
                  {invoiceDetails.invoiceNumber}
                </div>

                {job}
              </div>

              <div className="invoice__info">
                <div className="address-element">
                  <label htmlFor="from">Bill from:</label>
                  <span>{invoiceDetails.from}</span>
                  <span>{invoiceDetails.addressFrom}</span>
                  <span>{invoiceDetails.phoneFrom}</span>
                  <span>{invoiceDetails.emailFrom}</span>
                </div>
                <div className="address-element">
                  <label htmlFor="to">Bill to:</label>
                  <span>{invoiceDetails.to}</span>
                  <span>{invoiceDetails.addressTo}</span>
                  <span>{invoiceDetails.phoneTo}</span>
                  <span>{invoiceDetails.emailTo}</span>
                </div>
              </div>
              <hr />
              <div className="invoice__item-list">
                <div className="invoice__item-list__head">{ROW_HEADER_DIV}</div>
                {itemElement}
              </div>
              <hr />
              <div className="invoice__bill">
                <div className="bill-detail">
                  <div>
                    <span>Subtotal</span>
                    <h2>
                      {currencySymbol} {subTotal.toFixed(2)}
                    </h2>
                  </div>
                  <div>
                    <span>GST</span>
                    <h2>
                      {currencySymbol} {gstTotal.toFixed(2)}
                    </h2>
                  </div>
                  {discountElement}
                  {vatElement}
                  {amountPaidElement}
                  <div>
                    <span>Total ({this.props.currency["label"]})</span>
                    <h2 className="bill-total">
                      {currencySymbol} {amountTotal.toFixed(2)}
                    </h2>
                  </div>
                </div>
              </div>
              <hr />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

function mapStateToProps(state, ownProps) {
  return {
    currency: state.currency,
    addInfo: state.addInfo,
    items: state.items,
    invoiceDetails: state.invoiceDetails,
    status: state.status,
    issueDate: state.issueDate,
    dueDate: state.dueDate,
    dateFormat: state.dateFormat,
    downloadStatus: state.downloadStatus,
    paidStatus: state.paidStatus,
  };
}

export default connect(mapStateToProps, null)(Preview);
