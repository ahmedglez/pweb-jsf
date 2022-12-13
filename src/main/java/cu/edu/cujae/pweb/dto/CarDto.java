package cu.edu.cujae.pweb.dto;


public class CarDto {
    private int code ;
    private String carID;
    private String color;
    private CarStatusDto status;
    private CarModelDto model;
    private double mileage;

    public CarDto(){
        this.status = new CarStatusDto();
        this.model = new CarModelDto();
    }

    public CarDto(int code, String carID, String color, CarStatusDto status, CarModelDto model, double mileage) {
        this.code = code;
        this.carID = carID;
        this.color = color;
        this.status = status;
        this.model = model;
        this.mileage = mileage;
    }

    @Override
    public CarDto clone(){
        return new CarDto(getCode(),getCarID(),getColor(),getStatus(),getModel(),getMileage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarStatusDto getStatus() {
        return status;
    }

    public void setStatus(CarStatusDto status) {
        this.status = status;
    }

    

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public CarModelDto getModel() {
        return model;
    }

    public void setModel(CarModelDto model) {
        this.model = model;
    }


    

}





