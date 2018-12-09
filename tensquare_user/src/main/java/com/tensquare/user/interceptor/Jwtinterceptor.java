package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XSH on 2018/12/6.
 */
@Component
public class Jwtinterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //无论如何都要放行,拦截器只是把请求头进行中包含的token令牌进行解析
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            //如果包含有Authorization头信息,就对其进行解析
            if (header.startsWith("Bearer ")) {
                //得到token
                String token = header.substring(7);
                try {
                    //对令牌进行验证
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    System.out.println("登录角色:" + roles);
                    if (roles != null && "admin".equals(roles)) {
                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null && "user".equals(roles)) {
                        request.setAttribute("claims_user", token);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");
                }
            }
        }
        System.out.println("拦截器放行");
        return true;
    }
}
