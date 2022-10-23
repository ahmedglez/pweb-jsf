package cu.edu.cujae.pweb.dto;

public class PaymentsDto {
    private int code;
    private String payment;

    public PaymentsDto(int code, String payment) {

        this.payment = payment;
        this.code=code;
    }

    public String getPaymentText() { return payment; }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
