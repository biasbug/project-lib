package service;

import domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll();
    public void save(Role role) throws Exception;
}
