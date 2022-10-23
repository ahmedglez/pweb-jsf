package cu.edu.cujae.pweb.dto;

public class BrandDto {
    private String brand;
    private int code;
    private ModelDto model;

    public BrandDto(int code, String brand,ModelDto model) {
        this.code = code;
        this.brand = brand;
        this.model = model;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public String getBrandText() {
        return brand;
    }

    public void setBrandText(String brand) {
        this.brand = brand;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
