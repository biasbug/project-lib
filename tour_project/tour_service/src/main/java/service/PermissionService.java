package service;

import domain.Permission;

public interface PermissionService {
    public Permission findAll();
    public void save(Permission permission);
}
