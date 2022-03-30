import React from "react";

const Input = ({ name, label, value, error, onChange, type }) => {
  return (
    <div className="form-group">
      <input
        value={value}
        onChange={onChange}
        id={name}
        name={name}
        placeholder={name}
        type={type === "text" ? "text" : "password"}
        className="form-control"
        style={{ marginTop: "9px" }}
      />
      {error && <div className="alert alert-danger">{error}</div>}
    </div>
  );
};
export default Input;
