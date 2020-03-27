package service;

import domain.Role;
import domain.UserInfo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles);
    public List<UserInfo> findAll();
    public void save(UserInfo userInfo);
    UserInfo findById(String id);

    List<Role> findOtherRole(String id);

    void addRoleToUser(String userId, String[] roleIds);

    void deleteById(String id);
}
