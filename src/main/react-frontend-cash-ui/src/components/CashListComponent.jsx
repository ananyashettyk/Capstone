import React, { Component } from "react";
import CashService from "../services/CashService";

class CashListComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cashId: "",
      cashes: [],
      errResp: "",
    };
  }

  componentDidMount() {
    CashService.getCashes()
      .then((resp) => {
        console.log(resp.data);
        if (resp.request.status !== 200) {
          console.log("=========== >>>>>>>>>>>>>>>>>>>>" + resp);
          throw Error("getCash exception");
        }
        this.setState({ cashes: resp.data });
        console.log(JSON.stringify(this.state.cashes));
      })
      .catch((err) => {
        console.log("catch getcash exception: " + err.response);
      });
  }

  back = (e) => {
    e.preventDefault();
    window.location = "/view-search";
    console.log("back to cashList page");
  };

  logout = (e) => {
    e.preventDefault();
    window.location = "/";
    console.log("back to login page");
  };

  render() {
    const { length: count } = this.state.cashes;

    if (count === 0)
      return (
        <p className="fst-italic text-danger">
          There are no cashes in the database.
        </p>
      );

    return (
      <div>
        <div className="flex text-danger">
          <p className="m-auto">{this.state.errResp}</p>
        </div>
        <h4 className="text-center">List Of Cash Information</h4>
        <div className="overflow-auto">
          <table className="table-striped table-bordered table-sm">
            <thead>
              <tr className="bg-info">
                <th>CashId</th>
                <th>AccountName</th>
                <th>Type</th>
                <th>ProviderName</th>
                <th>ProviderID</th>
                <th>AccountReference</th>
                <th>AccountHolderName</th>
                <th>BalanceDate</th>
                <th>Amount</th>
                <th>Aer</th>
                <th>OverdraftLimit</th>
                <th>SortCodeAccountNumber</th>
                <th>Iban</th>
              </tr>
            </thead>

            <tbody>
              {this.state.cashes.map((cash) => (
                <tr key={cash.cashId}>
                  <td>{cash.cashId}</td>
                  <td>{cash.accountName}</td>
                  <td>{cash.type}</td>
                  <td>{cash.providerName}</td>
                  <td>{cash.providerID}</td>
                  <td>{cash.accountReference}</td>
                  <td>{cash.accountHolderName}</td>
                  <td>{cash.balanceDate}</td>
                  <td>{cash.amount}</td>
                  <td>{cash.aer}</td>
                  <td>{cash.overdraftLimit}</td>
                  <td>{cash.sortCodeAccountNumber}</td>
                  <td>{cash.iban}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <p className="text-primary">Showing {count} cash details.</p>
        <button
          className="btn bg-info btn-sm"
          onClick={this.back}
          style={{ marginLeft: "1200px", marginTop: "10px" }}
        >
          back
        </button>
        <button
          className="btn bg-info btn-sm"
          onClick={this.logout}
          style={{ marginLeft: "15", marginTop: "10px" }}
        >
          Logout
        </button>
      </div>
    );
  }
}
export default CashListComponent;
