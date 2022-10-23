package cu.edu.cujae.pweb.dto;

import cu.edu.cujae.pweb.utils.DateController;

import java.time.LocalDate;

public class ContractDto {
    private int code;
    private TouristDto tourist;
    private CarDto car;
    private LocalDate startingDate;
    private LocalDate finalDate;
    private int extension;
    private PaymentsDto payment;
    private BillDto bill;
    private DriverDto driver;
    private float totalAmount;

    public ContractDto(int code,TouristDto tourist, CarDto car, BillDto bill,PaymentsDto payment, DriverDto driver, LocalDate startingDate, LocalDate finalDate,int extension) {
        this.code = code;
        this.tourist = tourist;
        this.car = car;
        this.startingDate = startingDate;
        this.finalDate = finalDate;
        this.extension= extension;
        this.payment = payment;
        this.bill = bill;
        this.driver = driver;
        this.totalAmount = calculateTotalAmount();
    }

    public ContractDto(int code, TouristDto tourist, CarDto car, BillDto bill, PaymentsDto payment, LocalDate startingDate, LocalDate finalDate,int extension) {
        this.code = code;
        this.tourist = tourist;
        this.car = car;
        this.startingDate = startingDate;
        this.finalDate = finalDate;
        this.extension= extension;
        this.payment = payment;
        this.bill = bill;
        this.driver = null;
        this.totalAmount = calculateTotalAmount();
    }

    public ContractDto(BillDto bill, LocalDate startingDate, LocalDate finalDate, int extension) {
        this.startingDate = startingDate;
        this.finalDate = finalDate;
        this.extension = extension;
        this.bill = bill;
        this.totalAmount = calculateTotalAmount();
    }

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

    private float calculateTotalAmount(){ return (bill.getAmount()*DateController.extensionDate(startingDate,finalDate)) + (bill.getSpecialAmount() * extension); }
}
