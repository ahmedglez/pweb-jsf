package cu.edu.cujae.pweb.dto;

public class BillDto {
    private int code;
    private double amount;
    private double specialAmount;

    public BillDto(double amount, double specialAmount) {
        this.amount = amount;
        this.specialAmount = specialAmount;
    }

    public BillDto(){}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getSpecialAmount() { return specialAmount; }

    public void setSpecialAmount(double specialAmount) { this.specialAmount = specialAmount; }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
