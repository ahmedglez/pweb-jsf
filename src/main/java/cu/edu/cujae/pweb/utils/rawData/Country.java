package cu.edu.cujae.pweb.utils.rawData;

import java.util.ArrayList;
import java.util.Locale;

public class Country {

    public static ArrayList<String> getCountries(){
        ArrayList<String> countries = new ArrayList<>();
        String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry(Locale.ENGLISH));
        }

        return countries;
    }
}
