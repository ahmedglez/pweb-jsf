package cu.edu.cujae.pweb.dto;

public class TouristDto {
    private int code;
    private String name;
    private String lastName;
    private String idPassport;
    private int age;
    private String sex;
    private String telephoneNumber;
    private String country;

    public TouristDto( int code, String name, String lastName, String idPassport, int age, String sex, String telephoneNumber, String country) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.idPassport = idPassport;
        this.age = age;
        this.sex = sex;
        this.telephoneNumber = telephoneNumber;
        this.country = country;
    }

    public TouristDto(){
        this.name = "" ;
        this.lastName = "";
        this.idPassport = "";
        this.age = 0;
        this.sex = "";
        this.telephoneNumber = "";
        this.country = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(String idPassport) {
        this.idPassport = idPassport;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }
}
