/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Report;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mastermind
 */
@ManagedBean
@ViewScoped
public class ReportController {

    private Report report;

    private List<Report> monthly_report;
    private List<Report> daily_report;
    private List<Report> allTrans_report;
    private List<Report> deposit_report;

    ReportService reportDao = new ReportServiceImpl();

    public ReportController() {
        this.allTrans_report = reportDao.allTransactionList();
    }

    public void dailyTransReport() {
        this.daily_report = reportDao.dailyTransactionList(this.report.getDayDate());
    }

    public void monthlyTransReport() {
        this.monthly_report = reportDao.monthlyTransactionList(this.report.getFromDate(), this.report.getToDate());
    }

    public void depositReport() {
        this.deposit_report = reportDao.depositList(this.report.getFromDate(), this.report.getToDate());
    }

    public Report getReport() {
        if (this.report == null) {
            this.report = new Report();
        }
        return this.report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<Report> getMonthly_report() {
        return monthly_report;
    }

    public void setMonthly_report(List<Report> monthly_report) {
        this.monthly_report = monthly_report;
    }

    public List<Report> getDaily_report() {
        return daily_report;
    }

    public void setDaily_report(List<Report> daily_report) {
        this.daily_report = daily_report;
    }

    public List<Report> getAllTrans_report() {
        return allTrans_report;
    }

    public void setAllTrans_report(List<Report> allTrans_report) {
        this.allTrans_report = allTrans_report;
    }

    public List<Report> getDeposit_report() {
        return deposit_report;
    }

    public void setDeposit_report(List<Report> deposit_report) {
        this.deposit_report = deposit_report;
    }

}
