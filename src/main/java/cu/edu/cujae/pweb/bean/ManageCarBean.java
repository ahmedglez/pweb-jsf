package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.dto.reportTables.CarStatusReport;
import cu.edu.cujae.pweb.service.*;
import cu.edu.cujae.pweb.utils.DateController;
import cu.edu.cujae.pweb.utils.JsfUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class ManageCarBean {

  private CarDto cardto;
  private CarDto selectedCar;
  private List<CarDto> cars;
  private String newCarModel;
  private List<CarModelDto> models;
  private List<CarStatusDto> statuses;
  private List<BrandDto> brands;
  private List<CarStatusReport> carStatusReports;
  private Integer selectedModel;
  private Integer selectedStatus;
  private Integer selectedBrand = 0;

  @Autowired
  private CarService carService;

  @Autowired
  private ModelService modelService;

  @Autowired
  private CarStatusService statusService;

  @Autowired
  private BrandService brandService;

  @Autowired
  private ContractService contractService;

  @Autowired
  private UserBean userBean;

  public ManageCarBean() {}

  public void newCar() {
    this.selectedCar = new CarDto();
    this.selectedModel = 0;
    this.selectedStatus = 0;
    this.selectedBrand = 0;
  }

  public void newModel(){
    this.newCarModel = null;
    this.selectedBrand = 0;
    PrimeFaces.current().resetInputs("model-form:manage-model-content");
  }

  public void loadData(){
    try{
      cars = carService.getAll();
      models = modelService.getAll();
      brands = brandService.getAll();
      statuses = statusService.getAll();
    }catch (Exception e){
      PrimeFaces.current().executeScript("PF('manageLoggedDialog').show()");
    }

  }

  public void loadReports(){
    try{
      carStatusReports = carStatusReport();
    }catch (Exception e){
      userBean.logout();
    }

  }

  public void saveModel(){
    modelService.create(new CarModelDto(0,newCarModel,brandService.getByCode(selectedBrand)));
  }

  //Se ejecuta al dar clic en el button con el lapicito
  public void openForEdit(CarDto car) {
    this.selectedCar = car;

    this.selectedModel = car.getModel().getCode();

    this.selectedStatus = car.getStatus().getCode();

    this.selectedBrand = car.getModel().getBrand().getCode();
  }

  public void saveCar() {
    if (this.selectedCar.getCode() == 0) {
      this.selectedCar.setModel(modelService.getByCode(selectedModel));
      this.selectedCar.setStatus(statusService.getByCode(selectedStatus));
      this.selectedCar.getModel()
        .setBrand(brandService.getByCode(selectedBrand));
      carService.create(this.selectedCar);
      cars = carService.getAll();
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_car_added"
      );
    } else {
      this.selectedCar.setModel(modelService.getByCode(selectedModel));
      this.selectedCar.setStatus(statusService.getByCode(selectedStatus));
      this.selectedCar.getModel()
        .setBrand(brandService.getByCode(selectedBrand));
      carService.update(this.selectedCar);
      cars = carService.getAll();
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_car_edited"
      );
    }

    PrimeFaces.current().executeScript("PF('manageCarDialog').hide()");
    PrimeFaces.current().ajax().update("form1:dt-car");
  }

  //Permite eliminar un carro
  public void deleteCar() {
    try {
     carService.delete(selectedCar.getCode());
      this.selectedCar = null;

      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_car_deleted"
      );
      PrimeFaces.current().ajax().update("form1:dt-car"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    } catch (Exception e) {
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_ERROR,
        "message_error"
      );
    }
  }

  public List<CarStatusReport> carStatusReport() throws ParseException {
    List<ContractDto> contracts = contractService.getAll();
    List<CarDto> cars = carService.getAll();
    List<CarStatusReport> report = new ArrayList<>();
    for(CarDto c : cars){
      String plate = c.getCarID();
      String brand = c.getModel().getBrand().getBrand();
      String endOfContract = "Disponible";
      List<LocalDate> finDeContrato = new ArrayList<>();
      if(c.getStatus().getStatus().equals("Rentado")){
        for(int i=0; i<contracts.size();i++){
          if (contracts.get(i).getCar().getCarID().equals(c.getCarID())){
            finDeContrato.add(DateController.getLocalDateByString(contracts.get(i).getFinalDate()));
          }
        }
           endOfContract = DateController.getMostRecent(finDeContrato).toString();
      }else{
        if(c.getStatus().getStatus().equals("Taller")){
          endOfContract = "Taller";
        }
      }

      report.add(new CarStatusReport(plate,brand,c.getStatus().getStatus(),endOfContract));
    }
    return report;
  }

  //SETTERS AND GETTERS

  public CarDto getCardto() {
    return cardto;
  }

  public void setCardto(CarDto cardto) {
    this.cardto = cardto;
  }

  public CarDto getSelectedCar() {
    return selectedCar;
  }

  public void setSelectedCar(CarDto selectedCar) {
    this.selectedCar = selectedCar;
  }

  public List<CarDto> getCars() {
    return cars;
  }

  public void setCars(List<CarDto> cars) {
    this.cars = cars;
  }

  public List<CarModelDto> getModels() {
    return models;
  }

  public void setModels(List<CarModelDto> models) {
    this.models = models;
  }

  public List<CarStatusDto> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<CarStatusDto> statuses) {
    this.statuses = statuses;
  }

  public int getSelectedModel() {
    return selectedModel;
  }

  public void setSelectedModel(int selectedModel) {
    this.selectedModel = selectedModel;
  }

  public int getSelectedStatus() {
    return selectedStatus;
  }

  public void setSelectedStatus(int selectedStatus) {
    this.selectedStatus = selectedStatus;
  }

  public List<BrandDto> getBrands() {
    brands = brandService.getAll();
    return brands;
  }

  public void setBrands(List<BrandDto> brands) {
    this.brands = brands;
  }

  public int getSelectedBrand() {
    return this.selectedBrand;
  }

  public void setSelectedBrand(int selectedBrand) {
    this.selectedBrand = selectedBrand;
  }

  public void setSelectedModel(Integer selectedModel) {
    this.selectedModel = selectedModel;
  }

  public void setSelectedStatus(Integer selectedStatus) {
    this.selectedStatus = selectedStatus;
  }

  public void setSelectedBrand(Integer selectedBrand) {
    this.selectedBrand = selectedBrand;
  }

  public CarService getCarService() {
    return carService;
  }

  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  public ModelService getModelService() {
    return modelService;
  }

  public void setModelService(ModelService modelService) {
    this.modelService = modelService;
  }

  public CarStatusService getStatusService() {
    return statusService;
  }

  public void setStatusService(CarStatusService statusService) {
    this.statusService = statusService;
  }

  public BrandService getBrandService() {
    return brandService;
  }

  public void setBrandService(BrandService brandService) {
    this.brandService = brandService;
  }

  public String getNewCarModel() {
    return newCarModel;
  }

  public void setNewCarModel(String newCarModel) {
    this.newCarModel = newCarModel;
  }

  public List<CarStatusReport> getCarStatusReports() {
    return carStatusReports;
  }

  public void setCarStatusReports(List<CarStatusReport> carStatusReports) {
    this.carStatusReports = carStatusReports;
  }
}
