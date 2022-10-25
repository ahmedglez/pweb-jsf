package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.CarDto;
import cu.edu.cujae.pweb.dto.CarStatusDto;
import cu.edu.cujae.pweb.service.Car_Service;
import cu.edu.cujae.pweb.utils.JsfUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class CarBean {

  private CarDto cardto;
  private CarDto selectedCar;
  private List<CarDto> cars;
  private List<BrandDto> brands;
  private List<CarStatusDto> statuses;
  private String selectedBrand;
  private String selectedStatus;

  @Inject
  @Autowired
  private Car_Service carService;

  public CarBean() {}

  @PostConstruct
  public void init() {
    cars = cars == null ? carService.getAll() : cars;
    brands = brands == null ? carService.getBrands() : brands;
    statuses = statuses == null ? carService.getStatus() : statuses;
  }

  public void newCar() {
    this.selectedCar = new CarDto();
  }

  //Se ejecuta al dar clic en el button con el lapicito
  public void openForEdit(CarDto car) {
    this.selectedCar = car;
    this.selectedBrand = this.selectedCar.getBrand().getBrand();
    this.selectedStatus = this.selectedCar.getStatus().getStatus();
  }

  //Permite eliminar un carro
  public void deleteCar() {
    try {
      carService.delete(selectedCar.getCarID());
      this.selectedCar = null;
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_car_removed"
      );
      PrimeFaces.current().ajax().update("form:carForm"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    } catch (Exception e) {
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_ERROR,
        "message_error"
      );
    }
  }

  //setters and getters
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

  public Car_Service getCarService() {
    return carService;
  }

  public void setCarService(Car_Service carService) {
    this.carService = carService;
  }

  public List<CarDto> getAll() {
    return carService.getAll();
  }

  public List<BrandDto> getBrands() {
    return brands;
  }

  public void setBrands(List<BrandDto> brands) {
    this.brands = brands;
  }

  public List<CarStatusDto> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<CarStatusDto> statuses) {
    this.statuses = statuses;
  }

  public String getStatusName(CarStatusDto status) {
    if (selectedCar != null) {
      return selectedCar.getStatus().getStatus();
    } else {
      return status.getStatus();
    }
  }

  public String getBrandName(BrandDto brand) {
    if (selectedCar != null) {
      return selectedCar.getBrand().getBrand();
    } else {
      return brand.getBrand();
    }
  }

  public void saveCar() {
    BrandDto brand = new BrandDto(this.selectedBrand);
    CarStatusDto status = new CarStatusDto(this.selectedStatus);
    this.selectedCar.setBrand(brand);
    this.selectedCar.setStatus(status);
    if (carService.exitCar(this.selectedCar.getCarID())) {
      carService.update(this.selectedCar, this.selectedCar.getCarID());
    } else {
      carService.create(this.selectedCar);
    }
    cars = carService.getAll();

    PrimeFaces.current().executeScript("PF('manageCarDialog').hide()");
    PrimeFaces.current().ajax().update("form:dt-car");
  }

  public String getSelectedStatus() {
    return selectedStatus;
  }

  public void setSelectedStatus(String selectedStatus) {
    this.selectedStatus = selectedStatus;
  }

  public String getSelectedBrand() {
    return selectedBrand;
  }

  public void setSelectedBrand(String selectedBrand) {
    this.selectedBrand = selectedBrand;
  }
}
