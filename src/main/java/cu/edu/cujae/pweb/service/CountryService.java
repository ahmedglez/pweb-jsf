package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class CountryService {

    private ArrayList<CountryDto> cou =  getCounties();

    private ArrayList<CountryDto> getCounties(){
        ArrayList<CountryDto> countries = new ArrayList<>();
        ArrayList<String> names = countries();
        for(String a : names){
            countries.add(new CountryDto(a));
        }
        return countries;
    }


    private ArrayList<String> countries(){
        ArrayList<String> countries = new ArrayList<>();
        String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry(Locale.ENGLISH));
        }

        return countries;
    }

    public ArrayList<CountryDto> getAll(){return cou;}

}
