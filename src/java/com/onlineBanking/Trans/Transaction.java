/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.Trans;

import java.util.Date;

/**
 *
 * @author salim ahmed
 */
public class Transaction {
    
    private int transID;
    private String from;//me
    private String to;
    private double amount;
    private Date DOT;
    
    private double balance;
    private String password;
    private double balanceQR;

    public Transaction() {
    } 

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDOT() {
        return DOT;
    }

    public void setDOT(Date DOT) {
        this.DOT = DOT;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalanceQR() {
        return balanceQR;
    }

    public void setBalanceQR(double balanceQR) {
        this.balanceQR = balanceQR;
    }
    
    
}
