package cu.edu.cujae.pweb.service;

import java.util.List;

public interface DriverCrud {
  <T> List<T> getAll();
  Object getById(String id);
  void create(Object dto);
  void update(Object dto, String id);
  void delete(String id);
}
