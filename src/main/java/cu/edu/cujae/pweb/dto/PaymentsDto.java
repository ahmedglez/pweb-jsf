package cu.edu.cujae.pweb.dto;


import java.util.List;

public class PaymentsDto {

  private int code;
  private String payment;
  private List<ContractDto> contracts;


  public String getPaymentText() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

public String getPayment() {
    return payment;
}

public List<ContractDto> getContracts() {
    return contracts;
}

public void setContracts(List<ContractDto> contracts) {
    this.contracts = contracts;
}

  


}
