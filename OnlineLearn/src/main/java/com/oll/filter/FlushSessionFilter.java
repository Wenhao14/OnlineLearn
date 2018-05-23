package com.oll.filter;


import com.oll.cache.ShareLogin;
import com.oll.model.User;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/26.
 * 保持用户登录状态
 */
public class FlushSessionFilter implements Filter {
    private static ShareLogin shareLogin;

    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = shareLogin.getUser();
        if(user != null){
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
