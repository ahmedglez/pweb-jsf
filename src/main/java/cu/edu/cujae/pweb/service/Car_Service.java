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


	@Override
	public <T> List<T> getAll() {
		return null;
	}

	@Override
	public Object getById(int code) {
		return null;
	}

	@Override
	public void create(Object dto) {

	}

	@Override
	public void update(Object dto) {

	}

	@Override
	public void delete(int code) {

	}

	public List<BrandDto> getBrands() {
		return null;
	}

	public List<CarStatusDto> getStatus() {
		return null;
	}

	public boolean exitCar(int code) {
		return false;
	}
}
