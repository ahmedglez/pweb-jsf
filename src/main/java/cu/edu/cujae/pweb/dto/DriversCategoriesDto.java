package cu.edu.cujae.pweb.dto;

public class DriversCategoriesDto {

  private int code;
  private String category;

  public DriversCategoriesDto(String category) {
    switch (category) {
      case "Sin categoria":
        this.code = 0;
        this.category = category;
        break;
      case "Bachiller":
        this.code = 1;
        this.category = category;
        break;
      case "Universitario":
        this.code = 2;
        this.category = category;
        break;
      case "Tecnico medio":
        this.code = 3;
        this.category = category;
        break;
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
