 package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.CountryDto;
import cu.edu.cujae.pweb.dto.TouristDto;
import cu.edu.cujae.pweb.service.CountryService;
import cu.edu.cujae.pweb.service.TouristServices;
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
public class ManageTouristBean {

  private TouristDto tourist;
  private TouristDto selectedTourist;
  private List<TouristDto> tourists;
  private List<CountryDto> countries;
  private int selectedCountry;

  @Autowired
  private CountryService countryService;

  @Autowired
  private TouristServices service;

  @PostConstruct
  public void onInit() {
   /*  tourists = service.getAll();
    countries = countryService.getCountries(); */
  }

  public void newTourist() {
    this.selectedTourist = new TouristDto();
    this.selectedCountry = -1;
  }

  public void openForEdit(TouristDto tourist){
    selectedCountry = countryService.getCountryIdByCountryName(tourist.getCountry());
  }

  public void deleteTourist() {
    try {
      service.delete(this.selectedTourist.getCode());
      this.selectedTourist = null;
      tourists = service.getAll();
      JsfUtils.addMessageFromBundle(
              null,
              FacesMessage.SEVERITY_INFO,
              "message_tourist_deleted"
      );
      PrimeFaces.current().ajax().update("form:dt-tourist"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    } catch (Exception e) {
      JsfUtils.addMessageFromBundle(
              null,
              FacesMessage.SEVERITY_ERROR,
              "message_error"
      );
    }
  }

  public void saveTourist() {

    if (this.selectedTourist.getCode() == 0) {
      boolean repeatedId = service.existID(this.selectedTourist.getCode());
      if (!repeatedId) {
        this.selectedTourist.setCountry(countryService.getCountryByCode(selectedCountry).getName());
        service.create(this.selectedTourist);
        tourists = service.getAll();
        JsfUtils.addMessageFromBundle(
                null,
                FacesMessage.SEVERITY_INFO,
                "message_tourist_added"
        );
      } else {
        JsfUtils.addMessageFromBundle(
                null,
                FacesMessage.SEVERITY_ERROR,
                "message_error_id_already_exists"
        );
      }
    } else {
      this.selectedTourist.setCountry(countryService.getCountryByCode(selectedCountry).getName());
      service.update(this.selectedTourist);
      JsfUtils.addMessageFromBundle(
              null,
              FacesMessage.SEVERITY_INFO,
              "message_tourist_edited"
      );
    }

    PrimeFaces.current().executeScript("PF('manageTouristDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
    PrimeFaces.current().ajax().update("form:dt-tourist"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
  }

  public TouristDto getTourist() {
    return tourist;
  }

  public void setTourist(TouristDto tourist) {
    this.tourist = tourist;
  }

  public TouristDto getSelectedTourist() {
    return selectedTourist;
  }

  public void setSelectedTourist(TouristDto selectedTourist) {
    this.selectedTourist = selectedTourist;
  }

  public List<TouristDto> getTourists() {
    tourists = service.getAll();
    return tourists;
  }

  public void setTourists(List<TouristDto> tourists) {
    this.tourists = tourists;
  }

  public List<CountryDto> getCountries() {
    return countries;
  }

  public void setCountries(List<CountryDto> countries) {
    this.countries = countries;
  }

  public int getSelectedCountry() {
    return selectedCountry;
  }

  public void setSelectedCountry(int selectedCountry) {
    this.selectedCountry = selectedCountry;
  }

}
