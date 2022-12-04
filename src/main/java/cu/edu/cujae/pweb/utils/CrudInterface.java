package cu.edu.cujae.pweb.utils;

import java.util.List;

public interface CrudInterface {
  /* Cree esta interfaz para que el resto de Servicios implementen estos metodos
   * basicos en vez de que haya que crear una interfaz por cada servicio,
   * si un  servicio necesita mas metodos se los añade a esta interfaz
   */

  //Metodo para obtener todos los registros de la tabla
  <T> List<T> getAll();

  //Metodo para obtener un registro de la tabla
  Object getById(int code);

  //Metodo para insertar un registro en la tabla
  void create(Object dto);

  //Metodo para actualizar un registro en la tabla
  void update(Object dto);

  //Metodo para eliminar un registro de la tabla
  void delete(int code);
}
