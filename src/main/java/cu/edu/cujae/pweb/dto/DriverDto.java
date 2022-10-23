package cu.edu.cujae.pweb.dto;

import java.util.Random;

public class DriverDto {

  private int code;
  private String id;
  private String firstName;
  private String lastName;
  private String address;
  private DriversCategoriesDto category;

  public DriverDto(
    String id,
    String firstName,
    String lastName,
    String address,
    DriversCategoriesDto category
  ) {
    /* generate random code id */
    Random random = new Random();
    this.code = random.nextInt(1000);
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.category = category;
  }

  public DriverDto() {
    this.code = 0;
    this.id = "";
    this.firstName = "";
    this.lastName = "";
    this.address = "";
    this.category = null;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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
}
