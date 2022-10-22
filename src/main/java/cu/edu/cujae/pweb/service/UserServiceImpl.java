package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.dto.UserDto;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podrá inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired 
	private RoleService roleService;

	@Override
	public List<UserDto> getUsers() {
		
		List<RoleDto> adminRole = roleService.getRolesByName("admin");
		List<RoleDto> asesorRole = roleService.getRolesByName("asesor");
		List<RoleDto> allRoles = roleService.getRoles();
		
		List<UserDto> users = new ArrayList<>();
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "arojas", "Arnaldo Rojas Fuentes", "4weer4ewere", "arojas@email.com", "47856985245", adminRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "psuarez", "Perseo Suarez Tamyo", "sdfsd545", "psuarez@email.com", "69852147856", adminRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "scamejo", "Sandor Camejo Rayas", "343fsdfddds", "scamejo@email.com", "54785698547", adminRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "rcoas", "Ronaldo Coas Saldivar", "2w2ee22ww2", "rcoas@email.com", "36985214785", adminRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "hribas", "Hector Ribas Traki", "wwew443ewe", "hribas@email.com", "23698547852", adminRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "cestrada", "Camilo Estrada Lopez", "34hjhj3343k", "cestrada@email.com", "23698547412", adminRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "fromero", "Facundo Romero Ramen", "3www4ewwewe", "fromero@email.com", "85669854125", asesorRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "ptobal", "Pánfilo Tobal Madrid", "343sd3443wwe", "ptobal@email.com", "25147856325", asesorRole, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "imeriño", "Ian Meriño Sandival", "4weer4ewere", "imeriño@email.com", "21254789632", allRoles, false));
		users.add(new UserDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "jpacheco", "Javier Pacheco Losada", "8555weweww", "jpacheco@email.com", "96582365147", allRoles, false));
		
		return users;
	}

	@Override
	public UserDto getUserById(String userId) {
		return getUsers().stream().filter(r -> r.getId().equals(userId)).findFirst().get();
	}

	@Override
	public void createUser(UserDto user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDto user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
