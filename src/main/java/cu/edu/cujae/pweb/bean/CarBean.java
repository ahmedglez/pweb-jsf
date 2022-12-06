package cu.edu.cujae.pweb.bean;


import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.CarDto;
import cu.edu.cujae.pweb.dto.CarStatusDto;
import cu.edu.cujae.pweb.dto.CarModelDto;
import cu.edu.cujae.pweb.service.CarStatusService;
import cu.edu.cujae.pweb.service.BrandService;
import cu.edu.cujae.pweb.service.CarService;
import cu.edu.cujae.pweb.service.ModelService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
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
  private List<CarModelDto> models;
  private List<CarStatusDto> statuses;
  private List<BrandDto> brands;
  private Integer selectedModel;
  private Integer selectedStatus;
  private Integer selectedBrand;

  @Autowired
  private CarService carService;
  
  @Autowired
  private ModelService modelService;
  
  @Autowired
  private CarStatusService statusService;

  @Autowired
  private BrandService brandService;

  public CarBean() {}

  @PostConstruct
  public void init() {
//    cars = cars == null ? carService.getAll() : cars;
    cars = carService.getAll();
    models = modelService.getAll();
    statuses = statusService.getAll();
    brands = brandService.getAll();
  }

  public void newCar() {
    this.selectedCar = new CarDto();
    this.selectedModel = 0;
    this.selectedStatus = 0;
    this.selectedBrand = 0;
  }

  //Se ejecuta al dar clic en el button con el lapicito
  public void openForEdit() {
    
    CarModelDto model = this.selectedCar.getModel();
    this.selectedModel = model.getCode();
    
    CarStatusDto status = this.selectedCar.getStatus();
    this.selectedStatus = status.getCode();
    
    BrandDto brand = this.selectedCar.getModel().getBrand();
    this.selectedBrand = brand.getCode();
  }
  
  public void saveCar() {
      if(this.selectedCar.getCode()==0) {
              this.selectedCar.setModel(modelService.getByCode(selectedModel));
              this.selectedCar.setStatus(statusService.getByCode(selectedStatus));
              this.selectedCar.getModel().setBrand(brandService.getByCode(selectedBrand));
              carService.create(this.selectedCar);
              cars = carService.getAll();
              JsfUtils.addMessageFromBundle(
                      null,
                      FacesMessage.SEVERITY_INFO,
                      "message_car_added");
              
            
          } else {
              this.selectedCar.setModel(modelService.getByCode(selectedModel));
              this.selectedCar.setStatus(statusService.getByCode(selectedStatus));
              this.selectedCar.getModel().setBrand(brandService.getByCode(selectedBrand));
              carService.update(this.selectedCar);
              cars = carService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_car_edited"
            );
          }


      PrimeFaces.current().executeScript("PF('manageCarDialog').hide()");
      PrimeFaces.current().ajax().update("form:dt-car");
    }

  //Permite eliminar un carro
  public void deleteCar() {
    try {
        this.cars.remove(this.selectedCar);
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
    return brands;
}

public void setBrands(List<BrandDto> brands) {
    this.brands = brands;
}

public int getSelectedBrand() {
    return selectedBrand;
}

public void setSelectedBrand(int selectedBrand) {
    this.selectedBrand = selectedBrand;
}
  


}