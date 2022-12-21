package cu.edu.cujae.pweb.dto.reportTables;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CarStatusReport {

    private String carId;
    private String carBrand;
    private String status;
    private LocalDate endOfContract;

    public CarStatusReport(String carId, String carBrand, String status, LocalDate endOfContract) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.status = status;
        this.endOfContract = endOfContract;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEndOfContract() {
        return endOfContract;
    }

    public void setEndOfContract(LocalDate endOfContract) {
        this.endOfContract = endOfContract;
    }

    public static ArrayList<CarStatusReport> getCarStatusReport() throws SQLException, ClassNotFoundException {
        return new ArrayList<>();
    }
}
