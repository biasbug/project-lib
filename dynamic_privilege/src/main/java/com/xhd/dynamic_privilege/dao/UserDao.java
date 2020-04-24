package com.xhd.dynamic_privilege.dao;

import com.xhd.dynamic_privilege.domain.User;

public interface UserDao {
    public User getUser(String username);
}
