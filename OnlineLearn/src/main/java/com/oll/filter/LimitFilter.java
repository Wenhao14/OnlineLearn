package com.oll.filter;

import com.oll.cache.ShareLogin;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NewDarker on 2018/5/20.
 */
public class LimitFilter implements Filter {
    private static ShareLogin shareLogin;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(shareLogin.getUser() == null){
            //未登录重定向回首页
            response.sendRedirect("/index.html");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
    public static void setShareLogin(ShareLogin sLogin){
        shareLogin = sLogin;
    }
}
