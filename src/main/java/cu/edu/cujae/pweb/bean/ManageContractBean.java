package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.service.*;
import cu.edu.cujae.pweb.utils.DateController;
import cu.edu.cujae.pweb.utils.JsfUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ViewScoped
@Component
@ManagedBean
public class ManageContractBean {

  private ContractDto contractDto;
  private ContractDto selectedContract;
  private List<ContractDto> contracts;
  private double normalBill;
  private double especialBill;
  private Date startingDate;
  private Date finalDate;
  private int selectedTourist;
  private int selectedCar;
  private int selectedDriver;
  private int billCode;
  private int selectedPayment;

  @Autowired
  private ContractService contractService;

  @Autowired
  private BillService billService;

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private TouristServices touristServices;

  @Autowired
  private CarService carService;

  @Autowired
  private DriverService driverService;

  private List<CarDto> cars;
  private List<TouristDto> tourists;
  private List<DriverDto> drivers;
  private List<PaymentsDto> payments;

  @PostConstruct
  public void init() {
    /*  this.contracts = contractService.getAll();
        this.payments = paymentService.getAll();
        this.tourists = touristServices.getAll();
        this.cars = carService.getAll();
        this.drivers = driverService.getAll(); */
  }

  public void newContract() {
    this.selectedContract = new ContractDto();
    this.selectedCar = 0;
    this.selectedDriver = -1;
    this.selectedTourist = 0;
    this.startingDate = null;
    this.finalDate = null;
    this.normalBill = 0;
    this.especialBill = 0;
    this.billCode = 0;
    this.selectedPayment = 0;
  }

  public void openForEdit(ContractDto contact) throws ParseException {
    this.selectedCar = contact.getCar().getCode();
    this.selectedDriver = contact.getDriver().getCode();
    this.selectedTourist = contact.getTourist().getCode();
    System.out.println(contact.getStartingDate());
    System.out.println(contact.getFinalDate());
    this.startingDate =
      new SimpleDateFormat("yyyy-MM-dd").parse(contact.getStartingDate());
    this.finalDate =
      new SimpleDateFormat("yyyy-MM-dd").parse(contact.getFinalDate());
    this.normalBill = contact.getBill().getAmount();
    this.especialBill = contact.getBill().getSpecialAmount();
    this.billCode = contact.getBill().getCode();
    this.selectedPayment = contact.getPayment().getCode();
  }

  public void deleteContract() {
    try {
      int codeBill = selectedContract.getBill().getCode();
      contractService.delete(this.selectedContract.getCode());
      billService.delete(codeBill);
      this.selectedContract = null;
      contracts = contractService.getAll();
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_tourist_deleted"
      );
      PrimeFaces.current().ajax().update("form:dt-contract"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    } catch (Exception e) {
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_ERROR,
        "message_error"
      );
    }
  }

  public void saveContract() {
    if (this.selectedContract.getCode() == 0) {
      BillDto bill = new BillDto(normalBill, especialBill);
      selectedContract.setStartingDate(startingDate.toString());
      selectedContract.setFinalDate(finalDate.toString());
      billService.create(bill);
      selectedContract.setBill(
        billService.getAll().get(billService.getAll().size() - 1)
      );
      selectedContract.setTotalAmount(
        selectedContract.calculateTotalAmount(
          DateController.getLocalDate(startingDate),
          DateController.getLocalDate(finalDate)
        )
      );
      selectedContract.setTourist(touristServices.getByCode(selectedTourist));
      selectedContract.setCar(carService.getByCode(selectedCar));
      selectedContract.setDriver(driverService.getByCode(selectedDriver));
      selectedContract.setPayment(paymentService.getByCode(selectedPayment));
      contractService.create(this.selectedContract);
      contracts = contractService.getAll();
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_tourist_added"
      );
    } else {
      BillDto bill = selectedContract.getBill();
      bill.setCode(billCode);
      bill.setAmount(normalBill);
      bill.setSpecialAmount(especialBill);
      selectedContract.setStartingDate(startingDate.toString());
      selectedContract.setFinalDate(finalDate.toString());
      selectedContract.setTotalAmount(
        selectedContract.calculateTotalAmount(
          DateController.getLocalDate(startingDate),
          DateController.getLocalDate(finalDate)
        )
      );
      selectedContract.setTourist(touristServices.getByCode(selectedTourist));
      selectedContract.setCar(carService.getByCode(selectedCar));
      selectedContract.setDriver(driverService.getByCode(selectedDriver));
      selectedContract.setPayment(paymentService.getByCode(selectedPayment));
      contractService.update(this.selectedContract);
      contracts = contractService.getAll();
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_tourist_edited"
      );
    }

    PrimeFaces.current().executeScript("PF('manageContractDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
    PrimeFaces.current().ajax().update("form:dt-contract"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
  }

  public ContractDto getContractDto() {
    return contractDto;
  }

  public void setContractDto(ContractDto contractDto) {
    this.contractDto = contractDto;
  }

  public ContractDto getSelectedContract() {
    return selectedContract;
  }

  public void setSelectedContract(ContractDto selectedContract) {
    this.selectedContract = selectedContract;
  }

  public List<ContractDto> getContracts() {
    contracts = contractService.getAll();
    return contracts;
  }

  public void setContracts(List<ContractDto> contracts) {
    this.contracts = contracts;
  }

  public List<CarDto> getCars() {
    cars = carService.getAll();
    return cars;
  }

  public void setCars(List<CarDto> cars) {
    this.cars = cars;
  }

  public List<TouristDto> getTourists() {
    tourists = touristServices.getAll();
    return tourists;
  }

  public void setTourists(List<TouristDto> tourists) {
    this.tourists = tourists;
  }

  public List<DriverDto> getDrivers() {
    drivers = driverService.getAll();
    return drivers;
  }

  public void setDrivers(List<DriverDto> drivers) {
    this.drivers = drivers;
  }

  public List<PaymentsDto> getPayments() {
    payments = paymentService.getAll();
    return payments;
  }

  public void setPayments(List<PaymentsDto> payments) {
    this.payments = payments;
  }

  public double getNormalBill() {
    return normalBill;
  }

  public void setNormalBill(double normalBill) {
    this.normalBill = normalBill;
  }

  public double getEspecialBill() {
    return especialBill;
  }

  public void setEspecialBill(double especialBill) {
    this.especialBill = especialBill;
  }

  public ContractService getContractService() {
    return contractService;
  }

  public void setContractService(ContractService contractService) {
    this.contractService = contractService;
  }

  public BillService getBillService() {
    return billService;
  }

  public void setBillService(BillService billService) {
    this.billService = billService;
  }

  public PaymentService getPaymentService() {
    return paymentService;
  }

  public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public Date getStartingDate() {
    return startingDate;
  }

  public void setStartingDate(Date startingDate) {
    this.startingDate = startingDate;
  }

  public Date getFinalDate() {
    return finalDate;
  }

  public void setFinalDate(Date finalDate) {
    this.finalDate = finalDate;
  }

  public int getSelectedTourist() {
    return selectedTourist;
  }

  public void setSelectedTourist(int selectedTourist) {
    this.selectedTourist = selectedTourist;
  }

  public int getSelectedCar() {
    return selectedCar;
  }

  public void setSelectedCar(int selectedCar) {
    this.selectedCar = selectedCar;
  }

  public int getSelectedDriver() {
    return selectedDriver;
  }

  public void setSelectedDriver(int selectedDriver) {
    this.selectedDriver = selectedDriver;
  }

  public TouristServices getTouristServices() {
    return touristServices;
  }

  public void setTouristServices(TouristServices touristServices) {
    this.touristServices = touristServices;
  }

  public CarService getCarService() {
    return carService;
  }

  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  public DriverService getDriverService() {
    return driverService;
  }

  public void setDriverService(DriverService driverService) {
    this.driverService = driverService;
  }

  public int getBillCode() {
    return billCode;
  }

  public void setBillCode(int billCode) {
    this.billCode = billCode;
  }

  public int getSelectedPayment() {
    return selectedPayment;
  }

  public void setSelectedPayment(int selectedPayment) {
    this.selectedPayment = selectedPayment;
  }
}
