package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XSH on 2018-12-8.
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * return pre 表示在之前执行, return post表示在之后执行
     *
     * @return
     */
    public String filterType() {
        return "pre";
    }

    /**
     * 返回的数字越小,表示越先执行
     *
     * @return
     */
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否继续执行该过滤器,return true表示继续过滤
     *
     * @return
     */
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作,return 任何object的值都表示继续执行
     *
     * @return
     * @throws ZuulException
     */
    public Object run() throws ZuulException {
        System.out.println("经过了后台过滤器");
        //得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到reqest域
        HttpServletRequest request = currentContext.getRequest();
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        if (request.getMethod().indexOf("login") > 0) {
            return null;
        }
        //得到头信息
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if ("admin".equals(roles)) {
                        //把头信息转发下去,并且放行
                        currentContext.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false);//终止运行
                }
            }
        }
        currentContext.setSendZuulResponse(false);//终止运行
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
