// @flow
import React, { Component } from "react";
import Toggle from "react-toggle";
import Select from "react-select";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import {
  setAddInfo,
  setPaidStatus,
  setCurrency,
  setDateFormat,
  setDownloadStatus,
} from "../../actions";

import { SideNavProps } from "./../../models/Props";
import { DATE_OPTIONS } from "./../../constants";

const currencyData = require("./../../resources/currencyData.json");

class SideNav extends Component {
  constructor(props: SideNavProps) {
    super(props);
  }

  handleChange = (e: Event) => {
    if (e.target instanceof HTMLInputElement) {
      let discount =
        e.target.name === "discount"
          ? e.target.value
          : this.props.addInfo.discount;
      let vat =
        e.target.name === "vat" ? e.target.value : this.props.addInfo.vat;
      let amountPaid =
        e.target.name === "amountPaid"
          ? e.target.value
          : this.props.addInfo.amountPaid;
      this.props.setAddInfo(discount, amountPaid, vat);
    }
  };

  currencyChange = (val: { value?: string, label?: string }) => {
    if (val) {
      this.props.setCurrency(val);
    } else {
      this.props.setCurrency({ value: "₹", label: "INR" });
    }
  };

  dateFormatChange = (val: { value: ?string, label: ?string }) => {
    if (val) {
      this.props.setDateFormat(val);
    } else {
      this.props.setDateFormat({ value: "DD/MM/YYYY", label: "DD/MM/YYYY" });
    }
  };

  render() {
    let paidAmountInput;
    let { paidStatus } = this.props;
    if (paidStatus) {
      paidAmountInput = (
        <div className="setting setting--paid">
          <input
            type="text"
            name="amountPaid"
            value={this.props.addInfo.amountPaid}
            onChange={this.handleChange}
          />
        </div>
      );
    }

    return (
      <div className="side-nav">
        <h4>Invoice Settings</h4>
        <hr />
        <div className="side-nav__element">
          <div className="setting">
            <span>Discount</span>
            <input
              type="text"
              name="discount"
              value={this.props.addInfo.discount}
              onChange={this.handleChange}
            />
          </div>
          <div className="setting">
            <span>Value added tax (VAT)</span>
            <input
              type="text"
              name="vat"
              value={this.props.addInfo.vat}
              onChange={this.handleChange}
            />
          </div>

          <div className="setting setting--inline">
            <span>Paid to date</span>
            <label>
              <Toggle
                checked={this.props.paidStatus}
                icons={false}
                onChange={() => {
                  this.props.setPaidStatus(!this.props.paidStatus);
                }}
              />
            </label>
          </div>

          {paidAmountInput}
        </div>
        <hr className="full-line" />
        <div className="side-nav__inline-element">
          <div className="side-nav__element side-nav__element--select">
            <div className="setting">
              <span>Currency</span>
              <Select
                name="currency"
                value={this.props.currency}
                options={currencyData}
                onChange={this.currencyChange}
              />
            </div>
          </div>
          <div className="side-nav__element side-nav__element--select">
            <div className="setting">
              <span>Date Format</span>
              <Select
                name="dateFormat"
                searchable={false}
                value={this.props.dateFormat}
                options={DATE_OPTIONS}
                onChange={this.dateFormatChange}
              />
            </div>
          </div>
        </div>
        <hr className="full-line visiblity-check" />
        <div className="side-nav__element visiblity-check">
          <div className="setting">
            <div className="solid-btn solid-btn--ghost">
              <Link to="preview" className="ghost-btn">
                <i className="fa fa-eye" aria-hidden="true"></i> Preview
              </Link>
              <Link
                to="preview"
                className="ghost-btn"
                onClick={() => {
                  this.props.setDownloadStatus(!this.props.downloadStatus);
                }}
              >
                <i className="fa fa-arrow-circle-down" aria-hidden="true"></i>{" "}
                Download
              </Link>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

function mapStateToProps(state, ownProps) {
  return {
    addInfo: state.addInfo,
    paidStatus: state.paidStatus,
    currency: state.currency,
    dateFormat: state.dateFormat,
    downloadStatus: state.downloadStatus,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    setAddInfo: (discount, tax, amountPaid, vat) =>
      dispatch(setAddInfo(discount, tax, amountPaid, vat)),
    setPaidStatus: (paidStatus) => dispatch(setPaidStatus(paidStatus)),
    setCurrency: (currency) => dispatch(setCurrency(currency)),
    setDateFormat: (dateFormat) => dispatch(setDateFormat(dateFormat)),
    setDownloadStatus: (downloadStatus) =>
      dispatch(setDownloadStatus(downloadStatus)),
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SideNav);
