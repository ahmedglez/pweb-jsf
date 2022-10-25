package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.CountryDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private List<CountryDto> countries;

    @PostConstruct
    public void init() {
        countries = new ArrayList<>();

        String[] isoCodes = Locale.getISOCountries();

        for (int i = 0; i < isoCodes.length; i++) {
            Locale locale = new Locale("", isoCodes[i]);
            countries.add(new CountryDto(i, locale));
        }

    }

    public List<CountryDto> getCountries(){
        return countries;
    }

}
