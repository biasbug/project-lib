package dao;

import domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    public List<Role> findById(String userId);

    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;
}
