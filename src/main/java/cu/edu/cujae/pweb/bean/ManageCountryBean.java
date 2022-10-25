package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.CountryDto;
import cu.edu.cujae.pweb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;


@ViewScoped
@Component
@ManagedBean
public class ManageCountryBean {

    private CountryDto country;
    private List<CountryDto> countries;


    @Autowired
    private CountryService service;

    @PostConstruct
    public void init() {

        //countries
        countries = service.getCountries();

    }


    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public List<CountryDto> getCountries() {
        return countries;
    }


    public void setService(CountryService service) {
        this.service = service;
    }
}
