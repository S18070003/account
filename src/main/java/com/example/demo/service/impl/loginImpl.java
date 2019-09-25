package com.example.demo.service.impl;

import com.example.demo.dao.UserListMapper;
import com.example.demo.entity.UserList;
import com.example.demo.service.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class loginImpl implements login {
    @Autowired
    public UserListMapper userListMapper;
    @Override
    public UserList login(String username) {
        return userListMapper.selectAllByUsername(username);
    }
}
