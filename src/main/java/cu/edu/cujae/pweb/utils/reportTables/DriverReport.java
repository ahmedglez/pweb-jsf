package cu.edu.cujae.pweb.utils.reportTables;

import cu.edu.cujae.pweb.service.ServicesLocator;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverReport {

    private String Id;
    private String name;
    private String last_name;
    private String address;
    private String category;
    private int numberOfCarsDriven;

    public DriverReport(String id, String name, String last_name, String address, String category, int numberOfCarsDriven) {
        Id = id;
        this.name = name;
        this.last_name = last_name;
        this.address = address;
        this.category = category;
        this.numberOfCarsDriven = numberOfCarsDriven;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getNumberOfCarsDriven() {
        return numberOfCarsDriven;
    }

    public void setNumberOfCarsDriven(int numberOfCarsDriven) {
        this.numberOfCarsDriven = numberOfCarsDriven;
    }

    public static ArrayList<DriverReport> getDriverReport() throws SQLException {
        return ServicesLocator.getDriverServices().driverReport();
    }


}
