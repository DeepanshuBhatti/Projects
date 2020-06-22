// @flow
import React, { Component, useState, useEffect } from "react";
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
import axios from "axios";

function SideNav(props: SideNavProps) {
  const [currencyData, setCurrencyData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const { data } = await axios.get("/api/currencies");
      setCurrencyData(data);
    };
    fetchData();
    return () => {};
  }, []);

  const handleChange = (e: Event) => {
    if (e.target instanceof HTMLInputElement) {
      let discount =
        e.target.name === "discount" ? e.target.value : props.addInfo.discount;
      let vat = e.target.name === "vat" ? e.target.value : props.addInfo.vat;
      let amountPaid =
        e.target.name === "amountPaid"
          ? e.target.value
          : props.addInfo.amountPaid;
      props.setAddInfo(discount, amountPaid, vat);
    }
  };

  const currencyChange = (val: { value?: string, label?: string }) => {
    if (val) {
      props.setCurrency(val);
    } else {
      props.setCurrency({ value: "₹", label: "INR" });
    }
  };

  const dateFormatChange = (val: { value: ?string, label: ?string }) => {
    if (val) {
      props.setDateFormat(val);
    } else {
      props.setDateFormat({ value: "DD/MM/YYYY", label: "DD/MM/YYYY" });
    }
  };

  const getPaidAmountInput = () => {
    let paidAmountInput;
    let { paidStatus } = props;
    if (paidStatus) {
      paidAmountInput = (
        <div className="setting setting--paid">
          <input
            type="text"
            name="amountPaid"
            value={props.addInfo.amountPaid}
            onChange={handleChange}
          />
        </div>
      );
    }
    return paidAmountInput;
  };

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
            value={props.addInfo.discount}
            onChange={handleChange}
          />
        </div>
        <div className="setting">
          <span>Value added tax (VAT)</span>
          <input
            type="text"
            name="vat"
            value={props.addInfo.vat}
            onChange={handleChange}
          />
        </div>

        <div className="setting setting--inline">
          <span>Paid to date</span>
          <label>
            <Toggle
              checked={props.paidStatus}
              icons={false}
              onChange={() => {
                props.setPaidStatus(!props.paidStatus);
              }}
            />
          </label>
        </div>
        {getPaidAmountInput()}
      </div>
      <hr className="full-line" />
      <div className="side-nav__inline-element">
        <div className="side-nav__element side-nav__element--select">
          <div className="setting">
            <span>Currency</span>
            <Select
              name="currency"
              value={props.currency}
              options={currencyData}
              onChange={currencyChange}
            />
          </div>
        </div>
        <div className="side-nav__element side-nav__element--select">
          <div className="setting">
            <span>Date Format</span>
            <Select
              name="dateFormat"
              searchable={false}
              value={props.dateFormat}
              options={DATE_OPTIONS}
              onChange={dateFormatChange}
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
                props.setDownloadStatus(!props.downloadStatus);
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
