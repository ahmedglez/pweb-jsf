package cu.edu.cujae.pweb.bean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;


@Component
@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String theme = "arya"; //default

    public String getTheme() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(params.containsKey("theme") ) {
            theme = params.get("theme");
        }
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
