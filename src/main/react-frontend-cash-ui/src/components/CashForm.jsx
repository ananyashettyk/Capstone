import React, { Component } from "react";
import CashService from "../services/CashService";
import axios from "axios";

class CashForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cashId: "",
      accountName: "",
      type: "",
      providerName: "",
      providerID: "",
      accountReference: "",
      accountHolderName: "",
      balanceDate: "",
      amount: "",
      aer: "",
      overdraftLimit: "",
      sortCodeAccountNumber: "",
      iban: "",
      cashContent: "",
      error: "",
      showMe: true,
      isPost: true,
    };
    this.getCashById = this.getCashById.bind(this);
  }

  validate = () => {
    const errors = {};
    if (this.state.cashId.trim() === "") errors.cashId = "CashId is required";
    return Object.keys(errors).length === 0 ? null : errors;
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/cashService/getCash")
      .then((response) => {
        this.setState(this.state.cashContent, response.data);
        console.log("viewCash in then ====>" + this.state.cashContent);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  getCashById = (e) => {
    e.preventDefault();
    console.log("getCashById ====>");
    CashService.getCashById(this.state.cashId)
      .then((res) => {
        console.log(">>>>>>>>>>>>>>>>>" + res.message);
      })
      .catch((err) => {
        console.log(">>>>>>>>>>>>>>>>>" + err.message);
      });
    window.location = "/view-search";
  };

  deleteCashById = (e) => {
    e.preventDefault();
    console.log("deleteCashById ====>");
    alert("Cash details deleted");
    CashService.deleteCash(this.state.cashId)
      .then((res) => {
        console.log("res >>>>>>>>>>>>>>>>>>>>>:", res);
        this.setState({ cash: res.data });
        this.setState.errshowMe = true;
        console.log("deleteCashById in then ====>");
      })
      .catch((err) => {
        if (err.response === null) {
          alert("Invalid CashId");
          this.setState.err = err.message;
          console.log("err.response:" + err.message);
        }

        this.setState.showMe = false;
        if (err.message !== "Network Error")
          this.setState({
            errResp: JSON.stringify(err.response).substring(10, 129),
          });
        else {
          this.setState({
            errResp: JSON.stringify(err.message),
          });
        }
        console.log("DeleteCash Clicked" + this.state.errResp);
      });
  };

  updateCashById = (e) => {
    e.preventDefault();
    console.log("updateCashById ====>");
    window.location = "/view-update";
  };

  postSubmitHandler = (e) => {
    this.setState.isPost = false;
    e.preventDefault();
    if (this.state.type === "") {
      this.setState.showMe = false;
      console.log("this.state : ", this.state);
    }
    axios
      .post("http://localhost:8080/cashService/postCash", this.state)
      .then((response) => {
        console.log(response.data);
        alert("Cash details saved");
        this.setState.showMe = false;
      })
      .catch((error) => {
        alert("Cash Id already exists");
        this.setState({
          errors: JSON.stringify(error.response)
            .split(":")[12]
            .split(";")[3]
            .substring(18, 35),
        });
      });
  };

  getSubmitHandler = (e) => {
    e.preventDefault();
    console.log("getSubmitHandler>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    window.location = "/view-cashList";
  };

  changeCashHandler = (e) => {
    this.setState({ [e.target.name]: e.target.value });
    console.log(e.target.value);
  };

  logout = (e) => {
    e.preventDefault();
    window.location = "/";
    console.log("logout to cashList page");
  };

  render() {
    const {
      cashId,
      accountName,
      type,
      providerName,
      providerID,
      accountReference,
      accountHolderName,
      balanceDate,
      amount,
      aer,
      overdraftLimit,
      sortCodeAccountNumber,
      iban,
    } = this.state;

    return (
      <div>
        <div className="outer-container" style={{ marginTop: "2px" }}>
          <div className="row">
            <div className="card col-md-8 offset-md-2">
              <h4 className="text-center" style={{ marginTop: "4px" }}>
                Cash Input Form
              </h4>
              <div
                className="card-body"
                style={{
                  marginTop: "-45px",
                  marginBottom: "-18px",
                  marginLeft: "-20px",
                  marginRight: "-20px",
                }}
              >
                <form className="form-control-lg" onSubmit={this.handleSubmit}>
                  <div className="flex  text-danger">
                    <p className="m-auto ">{this.state.errors}</p>
                  </div>
                  <div className="form-group move-left">
                    <input
                      placeholder="ID"
                      name="cashId"
                      className="form-control"
                      value={cashId}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Account Name"
                      name="accountName"
                      className="form-control"
                      value={accountName}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Type mandatory field"
                      name="type"
                      className="form-control"
                      value={type}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Provider Name"
                      name="providerName"
                      className="form-control"
                      value={providerName}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Provider ID"
                      name="providerID"
                      className="form-control"
                      value={providerID}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Account Reference"
                      name="accountReference"
                      className="form-control"
                      value={accountReference}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Account Holder Name"
                      name="accountHolderName"
                      className="form-control"
                      value={accountHolderName}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Balance Date"
                      name="balanceDate"
                      className="form-control"
                      value={balanceDate}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Amount"
                      name="amount"
                      className="form-control"
                      value={amount}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Aer"
                      name="aer"
                      className="form-control"
                      value={aer}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Overdraft Limit"
                      name="overdraftLimit"
                      className="form-control"
                      value={overdraftLimit}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Sort Code Account Number"
                      name="sortCodeAccountNumber"
                      className="form-control"
                      value={sortCodeAccountNumber}
                      onChange={this.changeCashHandler}
                    />
                    <input
                      placeholder="Iban"
                      name="iban"
                      className="form-control"
                      value={iban}
                      onChange={this.changeCashHandler}
                    />
                  </div>

                  <button
                    type="submit"
                    className="btn bg-info btn-sm"
                    disabled={this.validate()}
                    onClick={this.postSubmitHandler}
                    style={{ marginTop: "10px" }}
                  >
                    Submit Cash Details
                  </button>
                  <button
                    className="btn bg-info btn-sm"
                    onClick={this.getSubmitHandler}
                    style={{ marginLeft: "15px", marginTop: "10px" }}
                  >
                    Display Cash
                  </button>
                  <button
                    className="btn bg-info btn-sm"
                    onClick={this.getCashById}
                    style={{ marginLeft: "15px", marginTop: "10px" }}
                  >
                    Search Cash By Id
                  </button>
                  <button
                    className="btn bg-info btn-sm"
                    onClick={this.updateCashById}
                    style={{ marginLeft: "15px", marginTop: "10px" }}
                  >
                    Update Cash
                  </button>
                  <button
                    className="btn btn-danger btn-sm"
                    disabled={this.validate()}
                    onClick={this.deleteCashById}
                    style={{ marginLeft: "15px", marginTop: "10px" }}
                  >
                    Delete Cash
                  </button>
                  <button
                    className="btn bg-info btn-sm"
                    onClick={this.logout}
                    style={{ marginLeft: "149px", marginTop: "10px" }}
                  >
                    Logout
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CashForm;
