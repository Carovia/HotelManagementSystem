package com.example.hotel.controller.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserInfoVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping("/api/user")
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR = "用户名或密码错误";
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm) {
        User user = accountService.login(userForm);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);

    }

    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserVO userVO) {
        return accountService.registerAccount(userVO);
    }


    @GetMapping("/{id}/getUserInfo")
    public ResponseVO getUserInfo(@PathVariable int id) {
        User user = accountService.getUserInfo(id);
        if(user==null){
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);
    }

    @PostMapping("/{id}/userInfo/update")
    public ResponseVO updateInfo(@RequestBody UserInfoVO userInfoVO,@PathVariable int id){
        return accountService.updateUserInfo(id,userInfoVO.getPassword(),userInfoVO.getUserName(),userInfoVO.getPhoneNumber());

    }

    @PostMapping("/chargeCredit")
    public ResponseVO chargeCredit(@RequestBody UserVO userVO){
        return accountService.chargeCredit(userVO.getCredit(), userVO.getEmail());
    }
    @PostMapping("/recoverCredit")
    public ResponseVO recoverCredit(@RequestBody UserVO userVO){
        return accountService.recoverCredit(userVO.getCredit(),userVO.getId());
    }
    @PostMapping("/portrait")
    public ResponseVO updatePortait(@RequestBody UserVO userVO){
        return ResponseVO.buildSuccess(accountService.updateAccountPortrait(userVO));
    }


    @PostMapping("/{id}/userInfo/updateCredit")
    public ResponseVO updateInfo(@RequestParam double addCredit, @PathVariable int id){
        return accountService.updateUserCredit(id, addCredit);

    }

}
