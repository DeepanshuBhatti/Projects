import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class HomePage extends Component {
  render() {
    return (
      <div className="home">
        <div className="home__banner home__banner--bg">
          <div className="overlay"></div>
          <h2>
            Generate Free and simple Invoices in <span>INDIA</span>
          </h2>
          <Link to="/dashboard" className="solid-btn">
            Create Invoice
          </Link>
        </div>
        <div className="home__banner home__banner--footer">
          <h4>Author - Deepanshu Bhatti</h4>
          <h5>
            Inspired By{" "}
            <a href="https://github.com/PunitGr/QuickBill">Quick Bill</a>
          </h5>
        </div>
      </div>
    );
  }
}
