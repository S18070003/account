package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserImpl implements User {
    @Autowired
    public UserMapper userMapper;
    @Override
    public int insert(com.example.demo.entity.User user){
        return userMapper.insertSelective(user);
    }
    @Override
    public com.example.demo.entity.User selectUserByUsername(String username){
        return userMapper.selectAllByUsername(username);
    }
    @Override
    public List<com.example.demo.entity.User> selectAll(){
        return userMapper.selectAll();
    }

    @Override
    public int checkByUsername(String username) {
        return userMapper.checkByUsername(username);
    }

    @Override
    public int delete(int id){
        return userMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int update(com.example.demo.entity.User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
