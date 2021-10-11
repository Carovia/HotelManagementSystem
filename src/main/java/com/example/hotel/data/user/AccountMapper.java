package com.example.hotel.data.user;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @return
     */
    int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     * @param email
     * @return
     */
    User getAccountByName(@Param("email") String email);

    User getAccountById(@Param("id") int id);

    /**
     * 更新用户信息
     * @param id
     * @param password
     * @param username
     * @param phonenumber
     * @return
     */
    int updateAccount(@Param("id") int id, @Param("password") String password,@Param("userName") String username, @Param("phoneNumber") String phonenumber);

    /**
     * 给指定邮箱账户充值信用值
     * @param credit
     * @param email
     * @return
     */
    int chargeCredit(@Param("credit") double credit, @Param("email") String email);

    int recoverCredit(@Param("credit") double credit, @Param("id") Integer id);

    /**
     * 更新信用值
     * @param id
     * @param credit
     * @return
     */
    void updateCredit(@Param("id") int id, @Param("credit") double credit);

    /**
     * 更新用户头像
     * @param id
     * @param portrait
     * @return
     */
    int updatePortrait(@Param("id") int id, @Param("portrait") String portrait);

//    /**
//     * 更新用户头像
//     * @param id
//     * @param portrait
//     * @return
//     */
//    int updatePortrait(@Param("id") int id, @Param("portrait") String portrait);

}
