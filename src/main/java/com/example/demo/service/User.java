package com.example.demo.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface User {
    int delete(int id);
    int update(com.example.demo.entity.User user);
    int insert(com.example.demo.entity.User user);
    com.example.demo.entity.User selectUserByUsername(@Param("username") String username);
    List<com.example.demo.entity.User> selectAll();
    int checkByUsername(String username);
}
