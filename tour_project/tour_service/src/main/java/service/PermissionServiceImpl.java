package service;

import dao.PermissionDao;
import domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        List<Permission> all = null;
        try {
            all = permissionDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
