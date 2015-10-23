/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.Report;

import java.util.Date;
import java.util.List;

/**
 *
 * @author salim ahmed
 */
public interface ReportService {
    
    List<Report> monthlyTransactionList(Date from , Date to);
    
    List<Report> dailyTransactionList(Date dayDate);
    
    List<Report> allTransactionList();
    
    List<Report> depositList(Date from , Date to);
}
