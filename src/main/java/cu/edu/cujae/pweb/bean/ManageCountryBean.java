package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.CountryDto;
import cu.edu.cujae.pweb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

@Component
@ManagedBean
public class ManageCountryBean {

    private ArrayList<CountryDto> countries;
    private CountryDto selectedCountryDto;

    @Autowired
    private CountryService countryService;

    @PostConstruct
    public void onInit(){
        countries = countryService.getAll();
    }

    public ArrayList<CountryDto> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<CountryDto> countries) {
        this.countries = countries;
    }

    public CountryDto getSelectedCountry() {
        return selectedCountryDto;
    }

    public void setSelectedCountry(CountryDto selectedCountryDto) {
        this.selectedCountryDto = selectedCountryDto;
    }
}
