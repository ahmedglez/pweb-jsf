package cu.edu.cujae.pweb.dto;

public class BillDto {
    private int code;
    private float amount;
    private float specialAmount;

    public BillDto(int code, float amount, float specialAmount) {
        this.code = code;
        this.amount = amount;
        this.specialAmount = specialAmount;
    }

    public BillDto(){}

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getSpecialAmount() { return specialAmount; }

    public void setSpecialAmount(float specialAmount) { this.specialAmount = specialAmount; }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
