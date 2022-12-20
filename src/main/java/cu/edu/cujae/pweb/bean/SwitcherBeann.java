package cu.edu.cujae.pweb.bean;

import java.io.Serializable;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

@Component
@ManagedBean
@SessionScoped
public class SwitcherBeann implements Serializable {

    private static final long serialVersionUID = 1L;
    private TreeMap<String, String>themes;
    private String theme;
    private GuestPreferences gp = new GuestPreferences();

    public void setGp(GuestPreferences gp) {
        this.gp = gp;
    }

    public GuestPreferences getGp() {
        return gp;
    }

    public TreeMap<String, String> getThemes() {
        return themes;
    }
    public void setThemes(TreeMap<String, String> themes) {
        this.themes = themes;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


    public SwitcherBeann() {

        theme = gp.getTheme();

        themes = new TreeMap<String, String>();
        themes.put("Saga", "saga");
        themes.put("Arya", "arya");
    }

    public void saveTheme() {
        System.out.println("theme: " + theme);
        gp.setTheme(theme);
        
    }
    
}
