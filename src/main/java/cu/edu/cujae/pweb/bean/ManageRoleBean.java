package cu.edu.cujae.pweb.bean;


import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.service.RoleService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageRoleBean {

    private RoleDto selectedRole;
    private List<RoleDto> roles;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserBean userBean;

    public void loadData() {
        try{
            roles = roleService.getAll();
        }catch (Exception e){
            PrimeFaces.current().executeScript("PF('manageLoggedDialog').show()");
        }
    }

    public void openNew() {
        this.selectedRole = new RoleDto();
    }


    //Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
    public void saveRole() {
        if (this.selectedRole.getCode() == 0) {
            roleService.create(selectedRole);
            roles = roleService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_role_added"
            ); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        } else {
            roleService.update(selectedRole);
            roles = roleService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_role_edited"
            );
        }

        PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-roles"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }


    //Permite eliminar un usuario
    public void deleteRole() {
        try {
            roleService.delete(selectedRole.getCode());
            selectedRole = null;
            roles = roleService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_role_deleted"
            );
            PrimeFaces.current().ajax().update("form:dt-roles"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_ERROR,
                    "message_error"
            );
        }
    }

    public RoleDto getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(RoleDto selectedRole) {
        this.selectedRole = selectedRole;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
