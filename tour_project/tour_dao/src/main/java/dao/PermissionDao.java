package dao;

import domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);

    @Select("select * from permission where id in(select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId);
}
