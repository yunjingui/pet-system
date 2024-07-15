package com.three.server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.three.server.common.email.EmailSender;
import com.three.server.common.email.VerificationCodeService;
import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.User;
import com.three.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    VerificationCodeService verificationCodeService;
    // 发送验证码
    @PostMapping("/sendVerificationCode/{username}")
    public ApiResponse<Void> sendVerificationCode(@PathVariable String username) {
        System.out.println("toEmail: " + username);
        return EmailSender.sendEmail(username) ? ApiResponse.success() : ApiResponse.fail(500, "请勿重复发送验证码");
    }

    // 注册
    @PostMapping("/register/{username}/{password}/{type}/{verificationCode}")
    public ApiResponse<Void> register(@PathVariable String username, @PathVariable String password,@PathVariable Integer type, @PathVariable String verificationCode) {
        if (!verificationCodeService.validateCode(username, verificationCode)) {
            return ApiResponse.fail(500, "验证码错误");
        }
        return userService.register(new User(username, password, type)) ? ApiResponse.success() : ApiResponse.fail(500, "账号已注册，请勿重复注册");
    }

    // 登录
    @GetMapping("/login/{username}/{password}")
    public ApiResponse<String> login(@PathVariable String username, @PathVariable String password) {
        if (userService.login(username, password)){
            String tokenSecret = "lhf20040208";
            System.out.println("login: " + username + " " + password);
            String token = JWT.create()
                    .withClaim("id", userService.findUserByUsername(username).getId())
                    .withClaim("username", username)
                    .withClaim("password", password).sign(Algorithm.HMAC256(tokenSecret));
            return ApiResponse.success(token);
        }
        else{
            return ApiResponse.fail(500, "账号密码错误，请重试");
        }
    }

    // 找回密码
    @PostMapping("/forgetPassword/{username}/{password}/{verificationCode}")
    public ApiResponse<Void> findPassword(@PathVariable String username, @PathVariable String password, @PathVariable String verificationCode) {
        if (!verificationCodeService.validateCode(username, verificationCode)) {
            return ApiResponse.fail(500, "验证码错误");
        }
        return userService.changePassword(username, password) ? ApiResponse.success() : ApiResponse.fail(500, "修改密码失败");
    }

    // 修改密码
    @PutMapping("/changePassword/{password}")
    public ApiResponse<Void> changePassword(@PathVariable String password, HttpServletRequest request) {
        String username = JWT.decode(request.getHeader("token")).getClaim("username").asString();
        return userService.changePassword(username, password) ? ApiResponse.success() : ApiResponse.fail(500, "修改密码失败");
    }

    // 删除用户
    @DeleteMapping("/deleteUser/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Integer id) {
        return userService.deleteUserById(id) ? ApiResponse.success() : ApiResponse.fail(500, "删除用户失败");
    }

    // 查找所有用户
    @GetMapping("/findAll")
    public ApiResponse<List<User>> findAllUsers() {
        if (userService.findAllUsers().isEmpty())
            return ApiResponse.fail(500, "无用户");
        return ApiResponse.success(userService.findAllUsers());
    }
}
