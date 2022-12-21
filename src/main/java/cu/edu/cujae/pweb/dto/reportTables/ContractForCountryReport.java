package cu.edu.cujae.pweb.dto.reportTables;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContractForCountryReport {

    private String country;
    private String carBrand;
    private String carModel;
    private int totalExtensionDays;
    private float incomeForCash;
    private float totalIncome;

    public ContractForCountryReport(String country, String carBran, String carModel, int totalExtensionDays, float incomeForCash, float totalIncome) {
        this.country = country;
        this.carBrand = carBran;
        this.carModel = carModel;
        this.totalExtensionDays = totalExtensionDays;
        this.incomeForCash = incomeForCash;
        this.totalIncome = totalIncome;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getTotalExtensionDays() {
        return totalExtensionDays;
    }

    public void setTotalExtensionDays(int totalExtensionDays) {
        this.totalExtensionDays = totalExtensionDays;
    }

    public float getIncomeForCash() {
        return incomeForCash;
    }

    public void setIncomeForCash(float incomeForCash) {
        this.incomeForCash = incomeForCash;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }

    public static ArrayList<ContractForCountryReport> getContractForCountryReport() throws SQLException, ClassNotFoundException {
        return new ArrayList<>();
    }


}
