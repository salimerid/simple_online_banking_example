/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.Report;

import java.util.Date;

/**
 *
 * @author salim ahmed
 */
public class Report {
    
    private Date fromDate;
    private Date toDate;
    private Date dayDate;
    
    private int transID;
    private String accountFrom;
    private String accountTo;
    private double Amount;
    private Date dot;

    public Report() {
    }

    public Report(int transID, String accountFrom, String accountTo, double Amount, Date dot) {
        this.transID = transID;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.Amount = Amount;
        this.dot = dot;
    }
    

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public Date getDot() {
        return dot;
    }

    public void setDot(Date dot) {
        this.dot = dot;
    }

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }
    
    
}
