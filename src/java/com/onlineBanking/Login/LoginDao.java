/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Login;

/**
 *
 * @author salim ahmed
 */
public interface LoginDao
{   
    boolean isLogin4Admin(SystemUser obj); 
    boolean isLogin4User(SystemUser obj); 
}
