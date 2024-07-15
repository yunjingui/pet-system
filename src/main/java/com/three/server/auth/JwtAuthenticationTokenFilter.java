package com.three.server.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.three.server.entity.User;
import com.three.server.service.UserService;
import com.three.server.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    private static final String TokenSecret = "lhf20040208";
    private DecodedJWT parseToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(TokenSecret)).build().verify(token);
        jwt.getClaims();
        return jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = request.getHeader("token");
//
        System.out.println("dhusah"+request.getRequestURI());
//        String qwt = JWT.create()
//                .withClaim("username", "lhf")
//                .withClaim("password","123").sign(Algorithm.HMAC256("lhf20040208"));
//        System.out.println(qwt);

        String uri = request.getRequestURI();
        String[] uriParam = uri.split("/");
        System.out.println(Arrays.toString(uriParam));
        if (request.getMethod().equals("OPTIONS")){
            filterChain.doFilter(request, response);
            return;
        }
        if (uriParam[1].equals("user")){
            System.out.println(request.getMethod());
            if (uriParam[2].equals("login") && request.getMethod().equals("GET")){
                filterChain.doFilter(request, response);
                return;
            } else if (uriParam[2].equals("register") && request.getMethod().equals("POST")){
                filterChain.doFilter(request, response);
                return;
            } else if (uriParam[2].equals("sendVerificationCode") && request.getMethod().equals("POST")){
                filterChain.doFilter(request, response);
                return;
            } else if (uriParam[2].equals("forgetPassword") && request.getMethod().equals("POST")){
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("hhhhh");
        }

        System.out.println("token: " + token);


        if (!StringUtils.hasText(token)) {
            //不放行
            System.out.println("滚蛋");
//            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String username;
        String password;
        String id;
        try {
            DecodedJWT claims = parseToken(token);
            username = claims.getClaim("username").asString();
            password = claims.getClaim("password").asString();
            id = claims.getClaim("id").asString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("非法token");
            return;
        }

//        request.setAttribute("id", id);
//        request.setAttribute("username", username);
//        request.setAttribute("password", password);
//        request.setAttribute("type", userService.findUserByUsername(username).getType());

        filterChain.doFilter(request, response);
    }
}


