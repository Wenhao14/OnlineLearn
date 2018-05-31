package com.oll.filter;

import com.oll.cache.ShareLogin;
import com.oll.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NewDarker on 2018/5/21.
 * 过滤未完善信息的用户
 */
public class PerfectMsgFilter implements Filter {
    private static ShareLogin shareLogin;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = shareLogin.getUser();
        if(user != null){
            if(user.getIsPerMsg().equals("0")){
                response.sendRedirect("/page/common/perfectUMsg.html");
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
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
