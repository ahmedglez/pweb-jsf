package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.RoleDto;

@Service
public class RoleServiceImpl implements RoleService{

	@Override
	public List<RoleDto> getRoles() {
		List<RoleDto> roles = new ArrayList<>();
		roles.add(new RoleDto(1L, "admin", "Administrador del sistema"));
		roles.add(new RoleDto(2L, "employee", "Empleado de la empresa"));
		roles.add(new RoleDto(3L, "asesor", "Asesor de la empresa"));
		roles.add(new RoleDto(4L, "manager", "Manager de la empresa"));
		roles.add(new RoleDto(5L, "reporter", "Visualizador de reportes"));
		return roles;
	}

	@Override
	public List<RoleDto> getRolesByUser(Long userId) {
		return getRoles().stream().filter(r -> r.getId() == userId).collect(Collectors.toList());
	}

	@Override
	public List<RoleDto> getRolesByName(String name) {
		return getRoles().stream().filter(r -> r.getRoleName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public RoleDto getRolesById(Long roleId) {
		return getRoles().stream().filter(r -> r.getId().equals(roleId)).findFirst().get();
	}

}
