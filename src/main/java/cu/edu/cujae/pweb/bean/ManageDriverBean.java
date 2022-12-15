package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.dto.DriversCategoriesDto;
import cu.edu.cujae.pweb.service.DriverCategoryService;
import cu.edu.cujae.pweb.service.DriverService;
import cu.edu.cujae.pweb.utils.JsfUtils;

import java.util.ArrayList;
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
public class ManageDriverBean {

  private DriverDto driverDto;
  private DriverDto selectedDriver;
  private List<DriverDto> drivers;
  private List<DriversCategoriesDto> categories;
  private int selectedCategory;

  @Autowired
  private DriverService driverService;
  @Autowired
  private DriverCategoryService driverCategoryService;

  @PostConstruct
  public void init() {
    drivers = drivers == null ? getAllWithOutSinDriver() : drivers;
    categories =
      categories == null ? getAllWithOutSinCategory() : categories;
  }

  //Se ejecuta al dar clic en el button Nuevo
  public void newDriver() {
    this.selectedDriver = new DriverDto();
    this.selectedCategory = -1;
  }

  //Permite eliminar un chofer
  public void deleteDriver() {
    try {
      driverService.delete(this.selectedDriver.getCode());
      drivers = getAllWithOutSinDriver();
      this.selectedDriver = null;
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_driver_deleted"
      );
      PrimeFaces.current().ajax().update("form:dt-drivers"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    } catch (Exception e) {
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_ERROR,
        "message_error"
      );
    }
  }

  public void saveDriver() {
    DriversCategoriesDto category = driverCategoryService.getByCode(selectedCategory);
    this.selectedDriver.setCategory(category);
    if (this.selectedDriver.getCode() == 0) {
        driverService.create(this.selectedDriver);
        drivers = getAllWithOutSinDriver();
        JsfUtils.addMessageFromBundle(
          null,
          FacesMessage.SEVERITY_INFO,
          "message_driver_added"
        );
    } else {
      driverService.update(this.selectedDriver);
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_driver_edited"
      );
    }
    PrimeFaces.current().executeScript("PF('manageDriverDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
    PrimeFaces.current().ajax().update("form:dt-drivers"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
  }

  private List<DriverDto> getAllWithOutSinDriver(){
    List<DriverDto> allWhitOutSinDriver = new ArrayList<>();
    for(DriverDto d: driverService.getAll()){
      if(d.getCode()!=0){
        allWhitOutSinDriver.add(d);
      }
    }
    return allWhitOutSinDriver;
  }

  private List<DriversCategoriesDto> getAllWithOutSinCategory(){
    List<DriversCategoriesDto> allWhitOutSinCategory = new ArrayList<>();
    for(DriversCategoriesDto d: driverCategoryService.getAll()){
      if(d.getCode()!=0){
        allWhitOutSinCategory.add(d);
      }
    }
    return allWhitOutSinCategory;
  }

  /* Getters and Setters */
  public DriverDto getDriverDto() {
    return driverDto;
  }

  public Integer getSelectedCategory() {
    return this.selectedCategory;
  }

  public void setSelectedCategory(Integer selectedCategory) {
    this.selectedCategory = selectedCategory;
  }

  public DriverService getDriverService() {
    return this.driverService;
  }

  public void setDriverService(DriverService driverService) {
    this.driverService = driverService;
  }

  public void setDriverDto(DriverDto driverDto) {
    this.driverDto = driverDto;
  }

  public DriverDto getSelectedDriver() {
    return selectedDriver;
  }

  public void setSelectedDriver(DriverDto selectedDriver) {
    this.selectedDriver = selectedDriver;
  }



  public void setDrivers(List<DriverDto> drivers) {
    this.drivers = drivers;
  }

  public List<DriverDto> getDrivers() {
    drivers = driverService.getAll();
    return drivers;
  }

  public List<DriversCategoriesDto> getCategories() {
    return categories;
  }

  public void setCategories(List<DriversCategoriesDto> categories) {
    this.categories = categories;
  }

  public void setSelectedCategory(int selectedCategory) {
    this.selectedCategory = selectedCategory;
  }
}
