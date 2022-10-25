package cu.edu.cujae.pweb.dto;

public class BrandDto {
    
    private int cod_brand;
    private String brand;
    private String cod_model;
    
    public BrandDto(int cod_brand, String brand, String cod_model) {
        super();
        this.cod_brand = cod_brand;
        this.brand = brand;
        this.cod_model = cod_model;
    }
    
    public BrandDto(String brand) {
    	if(brand.equals("mercedes") ) {
    		this.brand = brand;
    		this.cod_brand = 2;
    	}else if(brand.equals("toyota")) {
    		this.brand = brand;
    		this.cod_brand = 0;
    	}
    	else if(brand.equals("audi")) {
    		this.brand = brand;
    		this.cod_brand = 1;
    	}
    	
    	this.cod_model="default";
    }
    
    public int getCod_brand() {
        return cod_brand;
    }
    public void setCod_brand(int cod_brand) {
        this.cod_brand = cod_brand;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getCod_model() {
        return cod_model;
    }
    public void setCod_model(String cod_model) {
        this.cod_model = cod_model;
    }
    
    

}
