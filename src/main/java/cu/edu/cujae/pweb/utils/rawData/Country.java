package cu.edu.cujae.pweb.utils.rawData;

import java.util.ArrayList;
import java.util.Locale;

public class Country {

    private String country;

    public Country(String country){
        this.country = country;
    }

    private static ArrayList<String> countries(){
        ArrayList<String> countries = new ArrayList<>();
        String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry(Locale.ENGLISH));
        }

        return countries;
    }

    public static ArrayList<Country> getCountries(){

        ArrayList<String> paises = countries();
        ArrayList<Country> countries = new ArrayList<>();
        for(String a : paises){
            countries.add(new Country(a));
        }
        return countries;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
