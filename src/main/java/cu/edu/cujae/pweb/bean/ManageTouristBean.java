package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.TouristDto;
import cu.edu.cujae.pweb.service.TouristServices;
import cu.edu.cujae.pweb.utils.JsfUtils;
import java.util.ArrayList;
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
  private ArrayList<TouristDto> tourists;

  @Autowired
  private TouristServices service;

  @PostConstruct
  public void onInit() {
    tourists = service.getAll();
  }

  public void newTourist() {
    this.selectedTourist = new TouristDto();
  }

  public void updateTourist(TouristDto tourist) {
    this.selectedTourist = tourist;
  }

  public void deleteTourist() {
    try {
      tourists.remove(selectedTourist);
      this.selectedTourist = null;
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_user_removed"
      );
      PrimeFaces.current().ajax().update("form:dt-users");
    } catch (Exception e) {
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_ERROR,
        "message_error"
      );
    }
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

  public ArrayList<TouristDto> getTourists() {
    return tourists;
  }

  public void setTourists(ArrayList<TouristDto> tourists) {
    this.tourists = tourists;
  }
}
