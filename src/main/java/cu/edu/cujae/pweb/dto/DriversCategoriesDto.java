package cu.edu.cujae.pweb.dto;

public class DriversCategoriesDto {
    private int code;
    private String category;

    public DriversCategoriesDto(int code, String category) {
        this.code = code;
        this.category = category;
    }

    public DriversCategoriesDto() {
    }

    public String getCategory() { return category; }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
