package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/addManager")
    public ResponseVO addManager(@RequestBody UserForm userForm) {
        return adminService.addManager(userForm);
    }

    @PostMapping("/getAllManagers")
    public ResponseVO getAllManagers() { return ResponseVO.buildSuccess(adminService.getAllManagers()); }

    @PostMapping("/addMarketer")
    public ResponseVO addMarketer(@RequestBody UserForm userForm) {
        return adminService.addMarketer(userForm);
    }

    @PostMapping("/getAllMarketers")
    public ResponseVO getAllMarketers() {
        return ResponseVO.buildSuccess(adminService.getAllMarketers());
    }

    @PostMapping("/{id}/userInfo/updateinfo")
    public ResponseVO updateUserInfo(@RequestBody UserInfoVO userInfoVO, @PathVariable int id){
        return adminService.updateUserInfo(id,userInfoVO.getPassword(),userInfoVO.getUserName(),userInfoVO.getPhoneNumber(),userInfoVO.getemail());

    }

    @PostMapping("/{id}/deleteUser")
    public ResponseVO deleteUser(@PathVariable Integer id){
        return adminService.deleteUser(id);
    }
}
