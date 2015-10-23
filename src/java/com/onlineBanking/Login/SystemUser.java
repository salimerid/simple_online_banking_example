/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Login;

/**
 *
 * @author salim ahmed
 */
public class SystemUser {

    private String userName;
    private String password;
    
    private String userPhotos;
    private String adminName;
    private String adminPhotos;
    
  


    public SystemUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(String userPhotos) {
        this.userPhotos = userPhotos;
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPhotos() {
        return adminPhotos;
    }

    public void setAdminPhotos(String adminPhotos) {
        this.adminPhotos = adminPhotos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
