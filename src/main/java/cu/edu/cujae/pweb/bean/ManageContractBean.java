package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.dto.reportTables.ContractForBrandAndModelReport;
import cu.edu.cujae.pweb.dto.reportTables.ContractForCountryReport;
import cu.edu.cujae.pweb.dto.reportTables.IncomeAnnualReport;
import cu.edu.cujae.pweb.service.*;
import cu.edu.cujae.pweb.utils.DateController;
import cu.edu.cujae.pweb.utils.JsfUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
  private List<ContractForBrandAndModelReport> contractForBrandAndModelReports;
  private List<ContractForCountryReport> contractForCountryReports;
  private List<IncomeAnnualReport> incomeAnnualReports;
  private Date startingDate;
  private Date finalDate;
  private int selectedTourist;
  private int selectedCar;
  private int selectedDriver;
  private int selectedBill;
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

  @Autowired
  private UserBean userBean;

  private List<CarDto> cars;
  private List<TouristDto> tourists;
  private List<DriverDto> drivers;
  private List<BillDto> bills;
  private List<PaymentsDto> payments;

  @PostConstruct
  public void init() {
  }

  public void newContract() {
    this.selectedContract = new ContractDto();
    this.selectedCar = 0;
    this.selectedDriver = -1;
    this.selectedTourist = 0;
    this.startingDate = null;
    this.finalDate = null;
    this.selectedBill = 0;
    this.selectedPayment = 0;
  }

  public void loadData(){
    try{
      contracts = contractService.getAll();
      cars = getAvailableCars();
      tourists = touristServices.getAll();
      drivers = driverService.getAll();
      payments = paymentService.getAll();
      bills = billService.getAll();
    }catch (Exception e){
      userBean.logout();
    }

  }

  public void loadReports() throws ParseException {
    try{
      contractForBrandAndModelReports = contractForBrandAndModelReports();
      contractForCountryReports = contractForCountryReports();
      incomeAnnualReports = incomeAnnualReport();
    }catch (Exception e){
      PrimeFaces.current().executeScript("PF('manageLoggedDialog').show()");
    }
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
    this.selectedBill = contact.getBill().getCode();
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
        "message_contract_deleted"
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
      selectedContract.setStartingDate(startingDate.toString());
      selectedContract.setFinalDate(finalDate.toString());
      selectedContract.setBill(billService.getByCode(selectedBill));
      selectedContract.setTotalAmount(
        selectedContract.calculateTotalAmount(
          DateController.getLocalDate(startingDate),
          DateController.getLocalDate(finalDate)
        )
      );
      selectedContract.setTourist(touristServices.getByCode(selectedTourist));
      CarDto car = carService.getByCode(selectedCar);
      car.getStatus().setStatus("Rentado");
      car.getStatus().setCode(2);
      carService.update(car);
      selectedContract.setCar(carService.getByCode(selectedCar));
      selectedContract.setDriver(driverService.getByCode(selectedDriver));
      selectedContract.setPayment(paymentService.getByCode(selectedPayment));
      contractService.create(this.selectedContract);
      contracts = contractService.getAll();
      cars = getAvailableCars();
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_contract_added"
      );
    } else {
      selectedContract.setBill(billService.getByCode(selectedBill));
      selectedContract.setStartingDate(startingDate.toString());
      selectedContract.setFinalDate(finalDate.toString());
      selectedContract.setTotalAmount(
        selectedContract.calculateTotalAmount(
          DateController.getLocalDate(startingDate),
          DateController.getLocalDate(finalDate)
        )
      );
      selectedContract.setTourist(touristServices.getByCode(selectedTourist));
        CarDto car = carService.getByCode(selectedCar);
        car.getStatus().setStatus("Rentado");
        car.getStatus().setCode(2);
        carService.update(car);
      selectedContract.setCar(carService.getByCode(selectedCar));
      selectedContract.setDriver(driverService.getByCode(selectedDriver));
      selectedContract.setPayment(paymentService.getByCode(selectedPayment));
      contractService.update(this.selectedContract);
      contracts = contractService.getAll();
      cars = getAvailableCars();
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_contract_edited"
      );
    }

    PrimeFaces.current().executeScript("PF('manageContractDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
    PrimeFaces.current().ajax().update("form:dt-contract"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
  }

  private List<ContractForCountryReport> contractForCountryReports() throws ParseException {
      List<ContractForCountryReport> report = new ArrayList<>();
      List<ContractDto> contracts = contractService.getAll();
      List<String> countries = new LinkedList<>();
      for (ContractDto con : contracts) {
        String country = con.getTourist().getCountry();
        if (!countries.contains(country)) {
          countries.add(country);
          List<Integer> carsId = new LinkedList<>();
          for (ContractDto c : contracts) {
            int code = c.getCode();
            String country1 = c.getTourist().getCountry();
            String brand = c.getCar().getModel().getBrand().getBrand();
            String model = c.getCar().getModel().getModel();
            int amountExtension = 0;
            int amountRentedDays = 0;
            float incomeCash = 0;
            float totalAmount = 0;
            if (!carsId.contains(c.getCar().getCode()) && country1.equals(country)) {
              carsId.add(c.getCode());
              for (ContractDto d : contracts) {
                if (d.getCode() == code && country1.equals(country)) {
                  amountExtension += d.getExtension();
                  amountRentedDays += ChronoUnit.DAYS.between(DateController.getLocalDateByString(d.getStartingDate()), DateController.getLocalDateByString(d.getFinalDate()));
                  totalAmount += d.getTotalAmount();
                  if (d.getPayment().getPayment().equals("cash")) {
                    incomeCash += d.getTotalAmount();
                  }
                }
              }
              report.add(new ContractForCountryReport(country, brand, model, amountExtension, incomeCash, totalAmount));
            }
          }
        }
      }
      return report;
  }

  public List<IncomeAnnualReport> incomeAnnualReport() throws ParseException {
    List<IncomeAnnualReport> report = IncomeAnnualReport.generatedIncomeAnnualReport();
    List<ContractDto> contracts = contractService.getAll();
    float totalAmount = 0;
    for (ContractDto c : contracts) {
      if (DateController.getLocalDateByString(c.getStartingDate()).getYear() == LocalDate.now().getYear()) {
        int month = DateController.getLocalDateByString(c.getStartingDate()).getMonthValue();
        totalAmount += c.getTotalAmount();
        double amount = c.getTotalAmount() + report.get(month-1).getIncomeMonthly();
        report.get(month-1).setIncomeMonthly(amount);
      }
    }
    report.get(12).setIncomeMonthly(totalAmount);
    return report;
  }

  private List<ContractForBrandAndModelReport> contractForBrandAndModelReports() throws ParseException {
    List<ContractForBrandAndModelReport> report = new ArrayList<>();
    List<ContractDto> contracts = contractService.getAll();
    List<Integer> carsId = new LinkedList<>();
    for (ContractDto c : contracts) {
      int code = c.getCode();
      String brand = c.getCar().getModel().getBrand().getBrand();
      String model = c.getCar().getModel().getModel();
      int contractAmount = 0;
      int amountRentedDays = 0;
      float incomeCreditCard = 0;
      float incomeCash = 0;
      float incomeCheck = 0;
      if (!carsId.contains(c.getCar().getCode())) {
        carsId.add(c.getCode());
        for (ContractDto d : contracts) {
          if (d.getCode() == code) {
            contractAmount++;
            amountRentedDays += ChronoUnit.DAYS.between(DateController.getLocalDateByString(d.getStartingDate()), DateController.getLocalDateByString(d.getFinalDate()));
            if (d.getPayment().getPayment().equals("credit card")) {
              incomeCreditCard += d.getTotalAmount();
            } else {
              if (d.getPayment().getPayment().equals("cash")) {
                incomeCash += d.getTotalAmount();
              } else {
                if (d.getPayment().getPayment().equals("check")) {
                  incomeCheck += d.getTotalAmount();
                }
              }
            }
          }
        }
        report.add(new ContractForBrandAndModelReport(brand, model, contractAmount, amountRentedDays, incomeCreditCard, incomeCheck, incomeCash));
      }
    }
    return report;
  }

  private List<CarDto> getAvailableCars(){
    List<CarDto> available = new ArrayList<>();
    List<CarDto> carDtos = carService.getAll();
    for(CarDto c : carDtos){
      if(c.getStatus().getStatus().equals("Disponible")){
        available.add(c);
      }
    }
    return available;
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
    return contracts;
  }

  public void setContracts(List<ContractDto> contracts) {
    this.contracts = contracts;
  }

  public List<CarDto> getCars() {
    return cars;
  }

  public void setCars(List<CarDto> cars) {
    this.cars = cars;
  }

  public List<TouristDto> getTourists() {
    return tourists;
  }

  public void setTourists(List<TouristDto> tourists) {
    this.tourists = tourists;
  }

  public List<DriverDto> getDrivers() {
    return drivers;
  }

  public void setDrivers(List<DriverDto> drivers) {
    this.drivers = drivers;
  }

  public List<PaymentsDto> getPayments() {
    return payments;
  }

  public List<BillDto> getBills() {
    return bills;
  }

  public void setBills(List<BillDto> bills) {
    this.bills = bills;
  }

  public void setPayments(List<PaymentsDto> payments) {
    this.payments = payments;
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

  public int getSelectedBill() {
    return selectedBill;
  }

  public void setSelectedBill(int selectedBill) {
    this.selectedBill = selectedBill;
  }

  public int getSelectedPayment() {
    return selectedPayment;
  }

  public void setSelectedPayment(int selectedPayment) {
    this.selectedPayment = selectedPayment;
  }

  public List<ContractForBrandAndModelReport> getContractForBrandAndModelReports() {
    return contractForBrandAndModelReports;
  }

  public void setContractForBrandAndModelReports(List<ContractForBrandAndModelReport> contractForBrandAndModelReports) {
    this.contractForBrandAndModelReports = contractForBrandAndModelReports;
  }

  public List<ContractForCountryReport> getContractForCountryReports() {
    return contractForCountryReports;
  }

  public void setContractForCountryReports(List<ContractForCountryReport> contractForCountryReports) {
    this.contractForCountryReports = contractForCountryReports;
  }

  public List<IncomeAnnualReport> getIncomeAnnualReports() {
    return incomeAnnualReports;
  }

  public void setIncomeAnnualReports(List<IncomeAnnualReport> incomeAnnualReports) {
    this.incomeAnnualReports = incomeAnnualReports;
  }
}
