package cu.edu.cujae.pweb.utils.reportTables;

import cu.edu.cujae.pweb.service.ServicesLocator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractReport {

    private String touristName;
    private String touristLastName;
    private String carId;
    private String carBrand;
    private String carModel;
    private String payment;
    private LocalDate startingDate;
    private LocalDate finalDate;
    private int extension;
    private String haveDriver;
    private float totalAmount;

    public ContractReport(String touristName, String touristLastName, String carId, String carBrand, String carModel, String payment, LocalDate startingDate, LocalDate finalDate, int extension, String haveDriver, float totalAmount) {
        this.touristName = touristName;
        this.touristLastName = touristLastName;
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.payment = payment;
        this.startingDate = startingDate;
        this.finalDate = finalDate;
        this.extension = extension;
        this.haveDriver = haveDriver;
        this.totalAmount = totalAmount;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String getHaveDriver() {
        return haveDriver;
    }

    public void setHaveDriver(String haveDriver) {
        this.haveDriver = haveDriver;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTouristLastName() {
        return touristLastName;
    }

    public void setTouristLastName(String touristLastName) {
        this.touristLastName = touristLastName;
    }

    public static ArrayList<ContractReport> getContractReport() throws SQLException {
        return ServicesLocator.getContractServices().contractReport();
    }
}
