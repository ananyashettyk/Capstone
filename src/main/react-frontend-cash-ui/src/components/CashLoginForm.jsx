import React, { Component } from "react";
import Input from "./Input";
import "../css/style.css";

class CashLoginForm extends Component {
  state = {
    account: { username: "", password: "" },
    errors: {},
    loginError: false,
  };

  validate = () => {
    const errors = {};
    const { account } = this.state;
    if (account.username.trim() === "")
      errors.username = "Username is required";
    if (account.password.trim() === "")
      errors.password = "Password is required";
    return Object.keys(errors).length === 0 ? null : errors;
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const errors = this.validate();
    this.setState({ errors: errors || {} });
    if (errors) return;
    console.log("Submitted");
  };

  validateProperty = ({ name, value }) => {
    if (name === "username") {
      if (value.trim() === "") return "Username is required";
    }
    if (name === "password") {
      if (value.trim() === "") return "Password is required";
    }
  };

  handleChange = ({ currentTarget: input }) => {
    const errors = { ...this.state.errors };
    const errorMessage = this.validateProperty(input);
    if (errorMessage) errors[input.name] = errorMessage;
    else delete errors[input.name];

    const account = { ...this.state.account };
    account[input.name] = input.value;

    this.setState({ account, errors });
  };

  submitHandler = (e) => {
    e.preventDefault();
    console.log("View Cash >>>>>>>>>>>>>>>>");
    if (
      this.state.account.username === "admin" &&
      this.state.account.password === "admin"
    ) {
      window.location = "/view-form";
      this.setState({ loginError: false });
    } else {
      this.setState({ loginError: true });
    }
  };

  render() {
    const { account, errors, loginError } = this.state;
    return (
      <section className="main-container ">
        <div
          className="col-md-3  move-left offset-md-4"
          style={{
            marginTop: "120px",
            marginLeft: "450px",
            color: "lightblue",
          }}
        >
          <h2>
            <strong>Login</strong>
          </h2>
          <form onSubmit={this.handleSubmit}>
            <Input
              name="username"
              value={account.username}
              label="Username"
              onChange={this.handleChange}
              type="text"
              error={errors.username}
            />
            <Input
              name="password"
              value={account.password}
              label="Password"
              onChange={this.handleChange}
              type="password"
              error={errors.password}
            />
            <button
              disabled={this.validate()}
              className="btn bg-info btn-bg"
              onClick={this.submitHandler}
              style={{ marginTop: "10px" }}
            >
              Login
            </button>
          </form>
          {loginError && (
            <div class="alert alert-danger" role="alert">
              Wrong username or password credential
            </div>
          )}
        </div>
      </section>
    );
  }
}
export default CashLoginForm;
