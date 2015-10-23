/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.user;

import com.onlineBanking.PhotoUpload.ImgUpLoad;
import com.onlineBanking.PhotoUpload.ImgUpload_Impl;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author salim ahmed
 */
@ManagedBean
@ViewScoped
public class UserController {

    private User user;

    private UploadedFile photoFile;

    private List<User> user_List;

    private List<User> notifationList;

    private List<User> activeUserList;

    ImgUpLoad uploadService = new ImgUpload_Impl();

    UserService userDao = new UserServiceImpl();

    public UserController() {
        this.user_List = userDao.userList();
        this.notifationList = userDao.inActiveUserNotifiyList();
        this.activeUserList = userDao.activeUserList();
    }

    public void saveUser() {

        if (this.photoFile.getFileName().equals("")) {

            if (userDao.userReg(user)) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully add user info.",""));

                this.user = null;

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to add user info.",""));
            }
        } else {
            this.user.setPhotos(photoFile.getFileName());

            if (userDao.userReg(user)) {

                uploadService.uploadImg("user", user.getUserID() + "_" + photoFile.getFileName(), photoFile);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Successfully add user info.",""));

                this.user = null;

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to add user info.",""));
            }
        }

    }

    public void acceptUser(String accoutNo, int userID) {

        if (userDao.userAccept(accoutNo, userID)) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully accept user account!!."));

            this.user_List = userDao.userList();

            this.notifationList = userDao.inActiveUserNotifiyList();

            this.activeUserList = userDao.activeUserList();

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to accept user account!!."));
        }
    }

    public void rejectUser(String accoutNo, int userID) {

        if (userDao.userReject(accoutNo, userID)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully reject user account!!."));

            this.user_List = userDao.userList();

            this.notifationList = userDao.inActiveUserNotifiyList();

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to reject user account!!."));
        }
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUser_List() {
        return user_List;
    }

    public void setUser_List(List<User> user_List) {
        this.user_List = user_List;
    }

    public UploadedFile getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(UploadedFile photoFile) {
        this.photoFile = photoFile;
    }

    public List<User> getNotifationList() {
        return notifationList;
    }

    public void setNotifationList(List<User> notifationList) {
        this.notifationList = notifationList;
    }

    public List<User> getActiveUserList() {
        return activeUserList;
    }

    public void setActiveUserList(List<User> activeUserList) {
        this.activeUserList = activeUserList;
    }

}
