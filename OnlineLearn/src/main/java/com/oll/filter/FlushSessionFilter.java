package com.oll.filter;


import com.oll.cache.ShareLogin;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/26.
 * 登录过滤器
 */
public class FlushSessionFilter implements Filter {
    private static ShareLogin shareLogin;
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Object o = shareLogin.getUser();
        if(o != null){
            shareLogin.flushState();
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {

    }
    public static void setShareLogin(ShareLogin sLogin){
        shareLogin = sLogin;
    }
}
