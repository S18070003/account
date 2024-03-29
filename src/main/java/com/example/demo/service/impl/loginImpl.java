package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class loginImpl implements login {
    @Autowired
    public UserMapper userMapper;
    @Override
    public User login(String username) {
        return userMapper.selectAllByUsername(username);
    }
}
