package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    void deleteRole(long id);

    List<Role> getRoles();

    Role getRoleById(long id);

    Role getRoleByName(String rolename);

    Role getRoleById(Long id);

    Role createRoleIfNotFound(long id, String name);
}
