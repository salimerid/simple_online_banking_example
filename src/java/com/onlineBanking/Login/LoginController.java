/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Login;

import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author salim ahmed
 */
@ManagedBean
@SessionScoped

public class LoginController implements Serializable {

    private SystemUser system_user;
    private String userImg;
    private String useName;

    /**
     *
     */
    public static String AUTH_KEY = "";

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {

    }

    /**
     *
     * @return
     */
    public String checkLogin() {
        
        FacesContext context = FacesContext.getCurrentInstance();

        LoginDao dao = new LoginDaoImpl();
        
        String str1 = this.getSystem_user().getUserName();
        String str2 = str1.substring(0, 4);
        
        if (str2.equals("1515")) {

            boolean flag = dao.isLogin4Admin(this.getSystem_user());

            if (flag) {
                context.getExternalContext().getSessionMap().put("user_name", this.getSystem_user().getUserName());

                context.getExternalContext().getSessionMap().put(AUTH_KEY, this.getSystem_user().getUserName());

                this.system_user = null;

                return "/s_admin/index?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid System User.", ""));
            }
        }else{
            boolean flag = dao.isLogin4User(this.getSystem_user());
            if (flag) {
                context.getExternalContext().getSessionMap().put("user_name", this.getSystem_user().getUserName());

                context.getExternalContext().getSessionMap().put(AUTH_KEY, this.getSystem_user().getUserName());

                this.system_user = null;

                return "/s_user/index?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid System User.", ""));
            }
        }

        return "login";
    }

    /**
     *
     * @param event
     */
//    public void isAdmin(ComponentSystemEvent event) {
//        FacesContext fc = FacesContext.getCurrentInstance();
//
//        if (!"1515".equals(fc.getExternalContext().getSessionMap().get(LoginController.AUTH_KEY))) {
//            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
//
//            nav.performNavigation("/errorPage.xhtml?faces-redirect=true");
//        }
//    }

    /**
     * @return the system_user
     */
    public SystemUser getSystem_user() {
        if (this.system_user == null) {
            this.system_user = new SystemUser();
        }

        return this.system_user;
    }

    /**
     * @param system_user the system_user to set
     */
    public void setSystem_user(SystemUser system_user) {
        this.system_user = system_user;
    }

    //get user name in session and put it userName 
    public String getUseName() {
        FacesContext context = FacesContext.getCurrentInstance();
        useName = context.getExternalContext().getSessionMap().get("user_name").toString();
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getUserImg() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        userImg = context.getExternalContext().getSessionMap().get("user_img").toString();
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

}
