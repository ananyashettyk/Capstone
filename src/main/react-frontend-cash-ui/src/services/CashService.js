import axios from "axios";

const CASH_BASE_URL = "http://localhost:8080/cashService/";

class CashService {
  getCashById(cashId) {
    console.log(CASH_BASE_URL + "getCashById/" + cashId);
    return axios.get(CASH_BASE_URL + "getCashById/" + cashId);
  }

  putCash(cashId) {
    console.log(CASH_BASE_URL + "updateCash/" + cashId);
    return axios.patch(CASH_BASE_URL + "updateCash/" + cashId);
  }

  deleteCash(cashId) {
    console.log(CASH_BASE_URL + "deleteCash/" + cashId);
    return axios.delete(CASH_BASE_URL + "deleteCash/" + cashId);
  }

  getCashes() {
    return axios.get(CASH_BASE_URL + "/getCash");
  }
}

export default new CashService();
