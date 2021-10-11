package com.example.hotel.data.admin;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface AdminMapper {

    int addManager(User user);

    List<User> getAllManagers();

    int addMarketer(User user);

    List<User> getAllMarketers();

    int deleteUser(@Param("id") int id);

    int updateAccount(@Param("id") int id, @Param("password") String password, @Param("userName") String username, @Param("phoneNumber") String phonenumber,@Param("email") String email);
}
