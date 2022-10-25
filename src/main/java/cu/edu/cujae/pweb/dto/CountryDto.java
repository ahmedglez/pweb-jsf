package cu.edu.cujae.pweb.dto;


public class CountryDto {

    private String country;

    public CountryDto(String country){
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
