package cu.edu.cujae.pweb.dto;


public class DriverDto {

  private int code;
  private String ci;
  private String name;
  private String lastName;
  private String address;
  private DriversCategoriesDto category;
  


  
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public DriversCategoriesDto getCategory() {
    return category;
  }

  public void setCategory(DriversCategoriesDto category) {
    this.category = category;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }



  public String getCi() {
    return ci;
  }



  public void setCi(String ci) {
    this.ci = ci;
  }
}
