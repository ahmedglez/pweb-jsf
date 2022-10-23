package cu.edu.cujae.pweb.dto;

public class DriversCategoriesDto {

  private int code;
  private String category;

  public DriversCategoriesDto(int code, String category) {
    this.code = code;
    this.category = category;
  }

  public DriversCategoriesDto() {}

  public DriversCategoriesDto(String name) {
    if (name.equals("novato")) {
      this.code = 0;
      this.category = "novato";
    } else if (name.equals("intermedio")) {
      this.code = 1;
      this.category = "intermedio";
    } else if (name.equals("profesional")) {
      this.code = 2;
      this.category = "profesional";
    }
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
