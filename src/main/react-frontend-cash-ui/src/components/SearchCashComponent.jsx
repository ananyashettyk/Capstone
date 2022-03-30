import React, { Component } from "react";
import CashService from "../services/CashService";

class SearchCashComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      // cashId: this.props.match.params.cashId,
      showMe: false,
      cashData: {},
      errResp: "",
      err: "",
      cashId: "",
      accountName: "",
      type: "",
      providerName: "",
      providerId: "",
      accountReference: "",
      accountHolderName: "",
      balanceDate: "",
      amount: "",
      // transactionData: { count: "", earliestDate: "", lastDate: "" },
      aer: "",
      overdraftLimit: "",
      sortCodeAccountNumber: "",
      iban: "",
      cashContent: "",
      errors: {},
    };

    this.changeCashHandlerId = this.changeCashHandlerId.bind(this);
    this.viweCash = this.viweCash.bind(this);
  }

  validate = () => {
    const errors = {};
    if (this.state.cashId.trim() === "") errors.cashId = "CashId is required";
    return Object.keys(errors).length === 0 ? null : errors;
  };

  changeCashHandlerId = (event) => {
    this.setState({ cashId: event.target.value });
  };

  viweCash = (e) => {
    e.preventDefault();
    CashService.getCashById(parseInt(this.state.cashId))
      .then((res) => {
        this.state.showMe = true;
        this.setState({ cashData: res.data });
      })
      .catch((err) => {
        this.setState({
          error: JSON.stringify(err.response).split(":")[7].substring(1, 24),
        });
        alert("Enter valid Cash Id");
      });
  };

  back = (e) => {
    e.preventDefault();
    window.location = "/view-form";
    console.log("back to cashList page");
  };

  render() {
    return (
      <div style={{ marginTop: "-5px" }}>
        <div
          className="outer-container"
          style={{ marginTop: "10px", marginBottom: "-10px" }}
        >
          <div className="row">
            <div className="card col-md-4 offset-md-4">
              <h4 className="text-center" style={{ marginTop: "10px" }}>
                Search a Cash
              </h4>
              <div className="card-body">
                <form className="form-control-lg" onSubmit={this.handleSubmit}>
                  <div className="form-group move-left">
                    <input
                      placeholder="Cash ID"
                      name="CashId"
                      className="form-control"
                      value={this.state.cashId}
                      onChange={this.changeCashHandlerId}
                      style={{ marginTop: "-20px" }}
                    />
                  </div>

                  <button
                    className="btn bg-info btn-sm"
                    disabled={this.validate()}
                    onClick={this.viweCash}
                    style={{ marginLeft: "110px", marginTop: "8px" }}
                  >
                    Search
                  </button>
                  <button
                    className="btn bg-info btn-sm"
                    onClick={this.back}
                    style={{
                      marginLeft: "10px",
                      marginTop: "8px",
                    }}
                  >
                    Back
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
        <br></br>
        {this.state.showMe ? (
          <div
            className="card col-md-8  offset-md-2  flex  text-success"
            style={{ marginTop: "-8px" }}
          >
            <h5 className="text-center">Cash Details</h5>
            <div className="card-body" style={{ marginTop: "-10px" }}>
              <div className="row">
                <label>
                  <strong>Cash Id : </strong>
                  {this.state.cashId}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>Account Name : </strong>
                  {this.state.cashData.accountName}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong> Type : </strong>
                  {this.state.cashData.type}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>Provider Name : </strong>
                  {this.state.cashData.providerName}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>providerId : </strong>
                  {this.state.cashData.providerID}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>accountReference : </strong>
                  {this.state.cashData.accountReference}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>accountHolderName : </strong>
                  {this.state.cashData.accountHolderName}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>accountHolderName : </strong>
                  {this.state.cashData.accountHolderName}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>balanceDate : </strong>
                  {this.state.cashData.balanceDate}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>amount : </strong>
                  {this.state.cashData.amount}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>Aer : </strong>
                  {this.state.cashData.aer}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>Overdraft Limit : </strong>
                  {this.state.cashData.overdraftLimit}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>Sort Code Account Number : </strong>
                  {this.state.cashData.sortCodeAccountNumber}{" "}
                </label>
              </div>

              {/* <div className="row">
                <label>
                  <strong>Transaction Data Count : </strong>
                  {this.state.cashData.transactionData.count}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>Transaction Data Earliest Date : </strong>
                  {this.state.cashData.transactionData.earliestDate}{" "}
                </label>
              </div>

              <div className="row">
                <label>
                  <strong>Transaction Data Last Date : </strong>
                  {this.state.cashData.transactionData.lastDate}{" "}
                </label>
              </div> */}

              <div className="row">
                <label>
                  <strong>Iban : </strong>
                  {this.state.cashData.iban}{" "}
                </label>
              </div>
            </div>
          </div>
        ) : (
          <div className="flex  text-danger">
            <p className="m-auto ">{this.state.error}</p>
          </div>
        )}
      </div>
    );
  }
}

export default SearchCashComponent;
