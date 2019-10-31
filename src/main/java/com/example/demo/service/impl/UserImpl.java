package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserImpl implements User {
    @Autowired
    public UserMapper userMapper;
    @Override
    public int insert(com.example.demo.entity.User user){
        return userMapper.insertSelective(user);
    }
}
