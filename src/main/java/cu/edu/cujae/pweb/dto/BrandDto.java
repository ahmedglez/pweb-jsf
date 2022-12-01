package cu.edu.cujae.pweb.dto;

import org.primefaces.component.breadcrumb.BreadCrumb;

public class BrandDto {

  private int code;
  private String brand;

  public BrandDto(String brand) {
	if(brand.equalsIgnoreCase("toyota")){
		this.code = 1;
		this.brand = brand;
	}else if(brand.equalsIgnoreCase("audi")){
		this.code = 3;
		this.brand = brand;
	}
	else if(brand.equalsIgnoreCase("mercedes benz")){
		this.code = 4;
		this.brand = brand;
	}
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }
}
