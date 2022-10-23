package cu.edu.cujae.pweb.dto;

public class CarDto {
    private int code ;
    private String carID;
    private String color;
    private CarStatusDto status;
    private BrandDto brand;
    private double mileage;

    public CarDto(int code, String carID, CarStatusDto status, BrandDto brand, String color, double mileage) {
        this.code = code;
        this.carID = carID;
        this.status = status;
        this.brand = brand;
        this.color = color;
        this.mileage = mileage;

    }


    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarID() {
        return carID;
    }

    public CarStatusDto getStatus() {
        return status;
    }

    public void setStatus(CarStatusDto status) {
        this.status = status;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public double getMileage() { return mileage; }

    public void setMileage(double mileage) { this.mileage = mileage; }
}
