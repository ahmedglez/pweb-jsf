package cu.edu.cujae.pweb.dto;

public class CarModelDto {
    private int code;
    private String model;
    private BrandDto brand;

    public CarModelDto(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }
    
    
    
}
