package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.service.RoleService;
import cu.edu.cujae.pweb.service.UserService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageUserBean {

  private UserDto userDto;
  private UserDto selectedUser;
  private List<UserDto> users;
  private Long[] selectedRoles;

  private List<RoleDto> roles;

  /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
   * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
   */
  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  public ManageUserBean() {}

  //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.
  @PostConstruct
  public void init() {
    users = users == null ? userService.getUsers() : users;
    roles = roleService.getRoles();
  }

  //Se ejecuta al dar clic en el button Nuevo
  public void openNew() {
    this.selectedUser = new UserDto();
    this.selectedRoles = null;
  }

  //Se ejecuta al dar clic en el button con el lapicito
  public void openForEdit() {
    List<RoleDto> roles = this.selectedUser.getRoles();
    this.selectedRoles =
      roles.stream().map(r -> r.getId()).toArray(Long[]::new);
  }

  //Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
  public void saveUser() {
    if (this.selectedUser.getId() == null) {
      this.selectedUser.setId(
          UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9)
        );
      this.selectedUser.setNewRecord(true);
      List<RoleDto> rolesToAdd = new ArrayList<RoleDto>();
      for (int i = 0; i < this.selectedRoles.length; i++) {
        rolesToAdd.add(roleService.getRolesById(selectedRoles[i]));
      }

      this.users.add(this.selectedUser);
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_user_added"
      ); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
    } else {
      JsfUtils.addMessageFromBundle(
        null,
        FacesMessage.SEVERITY_INFO,
        "message_user_edited"
      );
    }

    PrimeFaces.current().executeScript("PF('manageUserDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
    PrimeFaces.current().ajax().update("form:dt-users"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
  }

  //Permite eliminar un usuario
  public void deleteUser() {
    try {
      this.users.remove(this.selectedUser);
      this.selectedUser = null;
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

  public UserDto getUserDto() {
    return userDto;
  }

  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }

  public UserDto getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDto selectedUser) {
    this.selectedUser = selectedUser;
  }

  public List<UserDto> getUsers() {
    return users;
  }

  public void setUsers(List<UserDto> users) {
    this.users = users;
  }

  public Long[] getSelectedRoles() {
    return selectedRoles;
  }

  public void setSelectedRoles(Long[] selectedRoles) {
    this.selectedRoles = selectedRoles;
  }

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }
}
