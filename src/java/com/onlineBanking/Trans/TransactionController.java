/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Trans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author salim ahmed
 */
@ManagedBean
@ViewScoped
public class TransactionController {

    private Transaction transaction;
    private String oldPassword;
    private String newPassword;

    TransactionService transDoa = new TransactionServiceImpl();

    public TransactionController() {
    }

    FacesContext context = FacesContext.getCurrentInstance();
    String sessionUserID = context.getExternalContext().getSessionMap().get("user_name").toString();

    public void blnc_Transfer() {

        FacesContext cntx = FacesContext.getCurrentInstance();

//        if (this.transaction.getTo().equals("")) {
//            cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning : ", "Please Enter account number !!!"));
//        } else if(this.transaction.getTo().equals("")){
//            
//        }
        if (transDoa.isValidAccountNum(this.transaction.getTo())) {

            if (transDoa.isValidPassWord(this.transaction.getPassword(), sessionUserID)) {

                if (transDoa.balanceTransfer(this.transaction)) {

                    cntx.addMessage(null, new FacesMessage("Successful : ", "Balance transfer complete. Your balance is: " + transaction.getBalance() + "Tk."));
                    this.transaction = null;
                } else {

                    cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance transfer Fail : ", "Insufficient balance: " + transaction.getBalance() + "Tk."));
                }

            } else {
                cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance transfer Fail : ", "Invalid password !!!."));
            }
        } else {

            cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance transfer Fail : ", "Invalid account number !!!."));
        }
    }

    public void deposit() {

        FacesContext cntx = FacesContext.getCurrentInstance();

        if (transDoa.isValidAccountNum(this.transaction.getTo())) {

            if (transDoa.isValidPassWord(this.transaction.getPassword(), this.transaction.getTo())) {

                if (transDoa.deposit(this.transaction)) {

                    cntx.addMessage(null, new FacesMessage("Successful : ", "Balance deposit complete. Your balance is: " + transaction.getBalance() + "Tk."));
                    this.transaction = null;
                } else {

                    cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance deposit Fail : ", "Insufficient balance: " + transaction.getBalance() + "Tk."));
                }

            } else {
                cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance deposit Fail : ", "Invalid password !!!."));
            }

        } else {

            cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance deposit Fail : ", "Invalid account number !!!."));
        }
    }

    public void balanceQuery() {

        FacesContext cntx = FacesContext.getCurrentInstance();

        if (this.transaction.getPassword().equals("")) {

            cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning : ", "Please Enter Password !!!"));

        } else {

            if (transDoa.balanceQR(this.transaction)) {

                cntx.addMessage(null, new FacesMessage("Welcome : ", "Your total account balance is : " + transaction.getBalanceQR() + "Tk."));

            } else {

                cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry : ", " Your password is not correct !!!"));
            }

        }

    }

    public void userPasswordChange() {

        FacesContext cntx = FacesContext.getCurrentInstance();

        if (transDoa.isValidPassWord(this.oldPassword, sessionUserID)) {

            if (transDoa.changePaswd4User(this.oldPassword, this.newPassword)) {

                cntx.addMessage(null, new FacesMessage("Successful : ", "Password Change !!!"));

            } else {

                cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Sorry : ", "Fail to password change!!!."));
            }
        } else {
            cntx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Sorry : ", "Invalid password !!!."));
        }
    }

    public Transaction getTransaction() {
        if (this.transaction == null) {
            this.transaction = new Transaction();
        }
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
