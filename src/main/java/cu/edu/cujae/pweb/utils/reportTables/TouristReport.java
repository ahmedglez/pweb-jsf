package cu.edu.cujae.pweb.utils.reportTables;

import cu.edu.cujae.pweb.service.ServicesLocator;

import java.sql.SQLException;
import java.util.ArrayList;

public class TouristReport {

    private String country;
    private String name;
    private String last_name;
    private String passport;
    private int amountContract;
    private float rentValue;

    public TouristReport(String country, String name, String last_name, String passport, int amount_contract, float rent_value) {
        this.country = country;
        this.name = name;
        this.last_name = last_name;
        this.passport = passport;
        this.amountContract = amount_contract;
        this.rentValue = rent_value;
    }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLast_name() { return last_name; }

    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getPassport() { return passport; }

    public void setPassport(String passport) { this.passport = passport; }

    public int getAmountContract() { return amountContract; }

    public void setAmountContract(int amountContract) { this.amountContract = amountContract; }

    public float getRentValue() { return rentValue; }

    public void setRentValue(float rentValue) { this.rentValue = rentValue; }

    public static ArrayList<TouristReport> getTouristReport() throws SQLException, ClassNotFoundException {
        return ServicesLocator.getTouristServices().touristReport();
    }



}
