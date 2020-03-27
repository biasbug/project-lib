package dao;

import domain.Role;
import domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "dao.RoleDao.findById"))
    })
    public UserInfo findByName(String username);

    @Select("select * from users")
    public List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "dao.RoleDao.findById"))
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in(select roleId from users_role where userId = #{id})")
    List<Role> findOtherRole(String id);

    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);

    @Delete("delete from users where id = #{id} ")
    void deleteById(String id);

    @Delete("delete from users_role where userId = #{userId}")
    void deleteRoleToUserById(String userId);
}
