package cu.edu.cujae.pweb.dto;

import java.util.List;

public class BillDto {
    private int code;
    private float amount;
    private float specialAmount;
    private List<ContractDto> contracts;

    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public float getSpecialAmount() {
        return specialAmount;
    }
    public void setSpecialAmount(float specialAmount) {
        this.specialAmount = specialAmount;
    }
    public List<ContractDto> getContracts() {
        return contracts;
    }
    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }



    


}
