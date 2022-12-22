package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.CountryDto;
import cu.edu.cujae.pweb.service.CountryService;
import cu.edu.cujae.pweb.utils.DateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDate;
import java.util.List;


@ViewScoped
@Component
@ManagedBean
public class ManageCountryBean {

    private CountryDto selectedCountry;
    private List<CountryDto> countries;


    @Autowired
    private CountryService service;

    private String actualDate = DateController.getActualDate().toString();

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }

    
    public CountryDto getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(CountryDto selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<CountryDto> getCountries() {
        countries = service.getCountries();
        return countries;
    }


    public void setService(CountryService service) {
        this.service = service;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }
}
