package cu.edu.cujae.pweb.utils.reportTables;

import cu.edu.cujae.pweb.dto.CarDto;
import cu.edu.cujae.pweb.service.ServicesLocator;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarReport {

    private String car_Id;
    private String brand;
    private String model;
    private String color;
    private double mileage;

    public CarReport(String car_Id, String brand, String model, String color, double mileage) {
        this.car_Id = car_Id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
    }

    public String getCar_Id() {
        return car_Id;
    }

    public void setCar_Id(String car_Id) {
        this.car_Id = car_Id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double km_driver) {
        this.mileage = km_driver;
    }

    public static ArrayList<CarReport> getCarReport() throws SQLException, ClassNotFoundException {
        ArrayList<CarReport> table = new ArrayList<>();
        ArrayList<CarDto> cars = ServicesLocator.getCarsServices().listCars();
        for (CarDto e : cars) {
            table.add(new CarReport(e.getCarID(), e.getBrand().getBrandText(), e.getBrand().getModel().getModelText(), e.getColor(), e.getMileage()));
        }
        return table;
    }
}
