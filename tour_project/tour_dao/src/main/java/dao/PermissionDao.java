package dao;

import domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface PermissionDao {

    @Select("select * from permission")
    public Permission findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);
}
