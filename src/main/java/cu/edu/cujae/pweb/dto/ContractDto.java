package cu.edu.cujae.pweb.dto;

import cu.edu.cujae.pweb.utils.DateController;

import java.time.LocalDate;

public class ContractDto {
    private int code;
    private TouristDto tourist;
    private CarDto car;
    private String startingDate ;
    private String finalDate;
    private int extension;
    private PaymentsDto payment;
    private BillDto bill;
    private DriverDto driver;
    private float totalAmount;


    public ContractDto(){}

    public TouristDto getTourist() {
        return tourist;
    }

    public void setTourist(TouristDto tourist) {
        this.tourist = tourist;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public PaymentsDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentsDto payment) {
        this.payment = payment;
    }

    public BillDto getBill() {
        return bill;
    }

    public void setBill(BillDto bill) {
        this.bill = bill;
    }

    public DriverDto getDriver() {
        return driver;
    }

    public void setDriver(DriverDto driver) {
        this.driver = driver;
    }

    public float getTotalAmount() { return totalAmount; }

    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }

    public int getExtension() { return extension; }

    public void setExtension(int extension) { this.extension = extension; }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    //private float calculateTotalAmount(){ return (bill.getAmount()*DateController.extensionDate(startingDate,finalDate)) + (bill.getSpecialAmount() * extension); }
}
