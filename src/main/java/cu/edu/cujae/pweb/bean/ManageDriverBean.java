package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.dto.DriversCategoriesDto;
import cu.edu.cujae.pweb.service.DriverService;
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
public class ManageDriverBean {

  private DriverDto dto;
  private DriverDto selected;
  private List<DriverDto> drivers;
  private List<DriversCategoriesDto> categories;

  @Autowired
  private DriverService driverService;

  public ManageDriverBean() {}

  @PostConstruct
  public void init() {
    drivers = drivers == null ? driverService.getAll() : drivers;
    categories =
      categories == null ? driverService.getCategories() : categories;
  }

  //Se ejecuta al dar clic en el button Nuevo
  public void newDriver() {
    this.selected = new DriverDto();
  }

  //Permite eliminar un chofer
  public void deleteUser() {
    try {
      driverService.delete(selected.getId());
      this.selected = null;
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_user_removed"
      );
      PrimeFaces.current().ajax().update("form:dt-users"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    } catch (Exception e) {
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_ERROR,
        "message_error"
      );
    }
  }

  /* Getters and Setters */
  public DriverDto getDto() {
    return dto;
  }

  public void setDto(DriverDto driverDto) {
    this.dto = driverDto;
  }

  public DriverDto getSelected() {
    return selected;
  }

  public void setSelected(DriverDto selected) {
    this.selected = selected;
  }

  public List<DriverDto> getAll() {
    return driverService.getAll();
  }

  public void setDrivers(List<DriverDto> drivers) {
    this.drivers = drivers;
  }

  public List<DriverDto> getDrivers() {
    return drivers;
  }

  public List<DriversCategoriesDto> getCategories() {
    return categories;
  }

  public String getCategoryName(DriversCategoriesDto category) {
    if (selected != null) {
      return selected.getCategory().getCategory();
    } else {
      return category.getCategory();
    }
  }

  public void setCategories(List<DriversCategoriesDto> categories) {
    this.categories = categories;
  }

  public void openForEdit(DriverDto driver) {
    this.selected = driver;
  }
}
