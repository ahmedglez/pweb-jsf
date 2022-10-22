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

  /* Los metodos de la interfaz que reciben por parametros un tipo Object
   * tienen que castearlo a su tipo correspondiente, en este caso DriverDto
   * al inicio de cada metodo
   */

  private List<DriverDto> drivers = initializeDrivers();

  //Inicializamos la lista de choferes
  private List<DriverDto> initializeDrivers() { 
    List<DriverDto> drivers = new ArrayList<DriverDto>();
    drivers.add(
      new DriverDto(
        "84051436521",
        "Pedro Luis",
        "Sosa Ramirez",
        "3era / 34 y 38 Playa",
        new DriversCategoriesDto(0, "profesional")
      )
    );
    drivers.add(
      new DriverDto(
        "85051436521",
        "Pedro Luis",
        "Sosa Ramirez",
        "3era / 34 y 38 Playa",
        new DriversCategoriesDto(0, "profesional")
      )
    );
    drivers.add(
      new DriverDto(
        "84056736521",
        "Jorge",
        "Lopez Arango",
        "Acosta / Damas y Cuba Habana Vieja",
        new DriversCategoriesDto(0, "novato")
      )
    );
    drivers.add(
      new DriverDto(
        "94051436521",
        "Felipe",
        "Garcia Fajardo",
        "Concordia / Espada y San Francisco Habana Vieja",
        new DriversCategoriesDto(0, "profesional")
      )
    );
    drivers.add(
      new DriverDto(
        "78051436521",
        "Humberto",
        "Blanco Barrios",
        "1era / 4 y 18 Playa",
        new DriversCategoriesDto(0, "profesional")
      )
    );

    drivers.add(
      new DriverDto(
        "79051436521",
        "Mauricio",
        "Acosta Bravo",
        "J / K y 27 Vedado",
        new DriversCategoriesDto(0, "profesional")
      )
    );

    return drivers;
  }

  //Obtenemos todos los choferes
  @Override
  public List<DriverDto> getAll() {
    return drivers;
  }

  @Override
  public DriverDto getById(String id) {
    for (DriverDto driver : drivers) {
      if (driver.getId().equals(id)) {
        return driver;
      }
    }
    return null;
  }

  @Override
  public void create(Object dto) {
    DriverDto driver = (DriverDto) dto;
    drivers.add(driver);
  }

  @Override
  public void update(Object obj, String id) {
    DriverDto dto = (DriverDto) obj;
    for (DriverDto driver : drivers) {
      if (driver.getId().equals(id)) {
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setAddress(dto.getAddress());
        driver.setCategory(dto.getCategory());
      }
    }
  }

  @Override
  public void delete(String id) {
    for (DriverDto driver : drivers) {
      if (driver.getId().equals(id)) {
        drivers.remove(driver);
        break;
      }
    }
  }
}
