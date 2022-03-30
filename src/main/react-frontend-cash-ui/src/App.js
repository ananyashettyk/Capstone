import React, { Component } from "react";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import SearchCashComponent from "./components/SearchCashComponent";
import CashListComponent from "./components/CashListComponent";
import CashForm from "./components/CashForm";
import CashLoginForm from "./components/CashLoginForm";
import CashFormUpdate from "./components/CashFormUpdate";

class App extends Component {
  render() {
    return (
      <Router>
        <Route exact path="/" component={CashLoginForm}></Route>
        <Route path="/view-form" component={CashForm}></Route>
        <Route path="/view-cashList" component={CashListComponent}></Route>
        <Route path="/view-search" component={SearchCashComponent}></Route>
        <Route path="/view-update" component={CashFormUpdate}></Route>
      </Router>
    );
  }
}

export default App;
