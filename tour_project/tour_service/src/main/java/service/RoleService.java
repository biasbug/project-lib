package service;

import domain.Permission;
import domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll();
    public void save(Role role) throws Exception;

    Role findByRoleId(String id);

    List<Permission> findOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
