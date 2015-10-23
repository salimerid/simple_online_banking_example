/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.Auth;


import com.onlineBanking.Login.LoginController;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author salim ahmed
 */
public class AuthFilter implements Filter
{
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
        this.config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
        if(((HttpServletRequest)request).getSession().getAttribute(LoginController.AUTH_KEY)==null
                
                && !((HttpServletRequest)request).getRequestURI().endsWith("/bank/index.xhtml") && 
                
                !((HttpServletRequest)request).getRequestURI().startsWith(((HttpServletRequest)request).getContextPath()+"/bank"+ResourceHandler.RESOURCE_IDENTIFIER))
        {
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/bank/index.xhtml");
        }
        else
        {
           chain.doFilter(request,response); 
        }
        
    }
    
    @Override
    public void destroy() {  
        this.config=null;  
    }
}
