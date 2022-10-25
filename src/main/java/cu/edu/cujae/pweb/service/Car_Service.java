package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.CarDto;
import cu.edu.cujae.pweb.dto.CarStatusDto;
import cu.edu.cujae.pweb.dto.ModelDto;
import cu.edu.cujae.pweb.utils.CrudInterface;



@Service
public class Car_Service implements CrudInterface{

	private List<CarDto> cars = initializeDrivers();

	  private List<BrandDto> brands = initializeBrands();
	  private List<CarStatusDto> status = initializeStatus();
	
	 private List<CarDto> initializeDrivers() {
		 List<CarDto> cars =new ArrayList<CarDto>();
		 cars.add(new CarDto(1, "k123456", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "rojo", 44));
		cars.add(new CarDto(2, "k123456", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "azul", 454));
			cars.add(new CarDto(3, "k123456",new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "azul",75));
			cars.add(new CarDto(4, "k156856", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "violeta", 55));
			cars.add(new CarDto(5, "k678456", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "azul", 44));
			cars.add(new CarDto(6, "k333456", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "azul", 66));
			cars.add(new CarDto(7, "k563456", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"),"negro", 77));
			cars.add(new CarDto(8, "k143456", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "blanco", 78));
			cars.add(new CarDto(9, "k333456", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "negro", 88));
			cars.add(new CarDto(10, "k123546", new CarStatusDto(0,"disponible"), new BrandDto(0,"toyota", "familiar"), "negro", 66));
			
			 return cars;
	 }
	 
	 private List<BrandDto> initializeBrands() {
		    List<BrandDto> brands = new ArrayList<BrandDto>();
		    brands.add(new BrandDto(0, "toyota", "familiar"));
		    brands.add(new BrandDto(1, "audi", "moderno"));
		    brands.add(new BrandDto(2, "mercedes", "personalizado"));
		    
		    return brands;
		  }
	 
	 private List<CarStatusDto> initializeStatus() {
		    List<CarStatusDto> statuses = new ArrayList<CarStatusDto>();
		    statuses.add(new CarStatusDto(0, "disponible"));
		    statuses.add(new CarStatusDto(1, "rentado"));
		    statuses.add(new CarStatusDto(2, "taller"));
		    return statuses;
		  }
	
	 
	 @Override
	  public List<CarDto> getAll() {
	    return cars;
	  }


	 @Override
	  public CarDto getById(String id) {
	    for (CarDto car : cars) {
	      if (car.getCarID().equals(id)) {
	        return car;
	      }
	    }
	    return null;
	  }

	  @Override
	  public void create(Object dto) {
	    CarDto car = (CarDto) dto;
	    cars.add(car);
	  }

	  @Override
	  public void update(Object obj, String id) {
		  CarDto dto = (CarDto) obj;
	    for (CarDto car : cars) {
	      if (car.getCarID().equals(id)) {
	        car.setCarID(dto.getCarID());
	        car.setColor(dto.getColor());
	        car.setBrand(dto.getBrand());
	        car.setMileage(dto.getMileage());
	        car.setStatus(dto.getStatus());
	      }
	    }
	  }

	  @Override
	  public void delete(String id) {
	    for (CarDto car : cars) {
	      if (car.getCarID().equals(id)) {
	        cars.remove(car);
	        break;
	      }
	    }
	  }
	  
	  public List<BrandDto> getBrands() {
		    return brands;
		  }
	  
	  public List<CarStatusDto> getStatus() {
		    return status;
		  }
	  
	  public boolean exitCar(String id) {
		  for(CarDto car : cars) {
			  if(car.getCarID().equals(id))
				  return true;
		  }
		  return false;
	  }

}
