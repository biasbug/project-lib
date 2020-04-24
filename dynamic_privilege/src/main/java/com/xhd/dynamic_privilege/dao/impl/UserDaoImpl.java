package com.xhd.dynamic_privilege.dao.impl;

import com.xhd.dynamic_privilege.dao.UserDao;
import com.xhd.dynamic_privilege.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private static final Map<String, User> userMap = new HashMap<String, User>();

    static {

        User user = new User();
        user.setUsername("liyd");
        user.setPassword("{noop}123456");
        user.setRole("user");
        userMap.put(user.getUsername(), user);

        user = new User();
        user.setUsername("admin");
        user.setPassword("{noop}123456");
        user.setRole("admin");
        userMap.put(user.getUsername(), user);
    }

    @Override
    public User getUser(String username) {
        return userMap.get(username);
    }
}
