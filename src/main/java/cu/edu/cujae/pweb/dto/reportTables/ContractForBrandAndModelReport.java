package cu.edu.cujae.pweb.dto.reportTables;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContractForBrandAndModelReport {

    private String brand;
    private String model;
    private int totalContract;
    private int totalDaysRented;
    private float incomeForCreditCard;
    private float incomeForCheck;
    private float incomeForCash;

    public ContractForBrandAndModelReport(String brand, String model, int totalContract, int totalDaysRented, float incomeForCreditCard, float incomeForCheck, float incomeForCash) {
        this.brand = brand;
        this.model = model;
        this.totalContract = totalContract;
        this.totalDaysRented = totalDaysRented;
        this.incomeForCreditCard = incomeForCreditCard;
        this.incomeForCheck = incomeForCheck;
        this.incomeForCash = incomeForCash;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTotalDaysRented() {
        return totalDaysRented;
    }

    public void setTotalDaysRented(int totalDaysRented) {
        this.totalDaysRented = totalDaysRented;
    }

    public float getIncomeForCreditCard() {
        return incomeForCreditCard;
    }

    public void setIncomeForCreditCard(float incomeForCreditCard) {
        this.incomeForCreditCard = incomeForCreditCard;
    }

    public float getIncomeForCheck() {
        return incomeForCheck;
    }

    public void setIncomeForCheck(float incomeForCheck) {
        this.incomeForCheck = incomeForCheck;
    }

    public float getIncomeForCash() {
        return incomeForCash;
    }

    public void setIncomeForCash(float incomeForCash) {
        this.incomeForCash = incomeForCash;
    }

    public int getTotalContract() { return totalContract; }

    public void setTotalContract(int totalContract) { this.totalContract = totalContract; }

    public static ArrayList<ContractForBrandAndModelReport> getContractForBrandAndModelReport() throws SQLException, ClassNotFoundException {
        return new ArrayList<>();
    }
}
