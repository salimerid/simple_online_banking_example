/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Admin;

import com.onlineBanking.PhotoUpload.ImgUpLoad;
import com.onlineBanking.PhotoUpload.ImgUpload_Impl;
import com.onlineBanking.Trans.TransactionService;
import com.onlineBanking.Trans.TransactionServiceImpl;
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
public class AdminController {

    private Admin admin;
    private String oldPassword;
    private String newPassword;

    private UploadedFile photoFile;

    ImgUpLoad uploadService = new ImgUpload_Impl();

    AdminService adminDao = new AdminServiceImpl();

    public AdminController() {
    }

    public void saveAdmin() {

        if (this.photoFile.getFileName().equals("")) {

            if (adminDao.addAdmin(admin)) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully add admin info."));

                this.admin = null;

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add admin info."));
            }
        } else {
            this.admin.setPhotos(photoFile.getFileName());

            if (adminDao.addAdmin(admin)) {

                uploadService.uploadImg("admin", admin.getAdminID() + "_" + photoFile.getFileName(), photoFile);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully add admin info."));

                this.admin = null;

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add admin info."));
            }
        }

    }
    
    FacesContext context = FacesContext.getCurrentInstance();
    String adminID = context.getExternalContext().getSessionMap().get("user_name").toString();

    public void adminPasswordChange() {

        FacesContext cntx = FacesContext.getCurrentInstance();

        if (adminDao.isValidPassWord(this.oldPassword, adminID)) {

            if (adminDao.changePaswd4Admin(this.oldPassword, this.newPassword)) {

                cntx.addMessage(null, new FacesMessage("Successful : ", "Password Change !!!"));

            } else {

                cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Sorry : ", "Fail to password change!!!."));
            }
        } else {
            cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Sorry : ", "Invalid password !!!."));
        }
    }

    public Admin getAdmin() {

        if (this.admin == null) {
            this.admin = new Admin();
        }
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public UploadedFile getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(UploadedFile photoFile) {
        this.photoFile = photoFile;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
