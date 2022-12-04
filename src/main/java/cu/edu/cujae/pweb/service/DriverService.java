package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.dto.DriversCategoriesDto;
import cu.edu.cujae.pweb.utils.CrudInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/* Implementamos CrudService para que cada uno de los servicios que hagamos
 * tengan los mismos metodos que esta interfaz, admeas les pueden añadir
 *  los que ustedes quieran
 */
@Service
public class DriverService implements CrudInterface {


  @Override
  public List<DriverDto> getAll() {
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

  public boolean existID(int code){
    return false;
  }

  public List<DriversCategoriesDto> getCategories() {
    return null;
  }
}
