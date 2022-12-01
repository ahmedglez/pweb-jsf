package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.dto.DriversCategoriesDto;
import cu.edu.cujae.pweb.utils.CrudInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/* Implementamos CrudService para que cada uno de los servicios que hagamos
 * tengan los mismos metodos que esta interfaz, admeas les pueden añadir
 *  los que ustedes quieran
 */
@Service
public class DriverService implements CrudInterface {

  @Override
  public List<DriverDto> getAll() {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    return null;
  }

  @Override
  public Object getById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void create(Object dto) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(Object dto, String id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub

  }
}
