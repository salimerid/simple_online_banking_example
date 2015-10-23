/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Report;

import com.onlineBanking.Connection.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author mastermind
 */
public class ReportServiceImpl implements ReportService {

    FacesContext context = FacesContext.getCurrentInstance();
    String sessionUserID = context.getExternalContext().getSessionMap().get("user_name").toString();

    @Override
    public List<Report> monthlyTransactionList(Date from, Date to) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        List<Report> month_trans_List = new ArrayList();

        try {
            prst = con.prepareStatement("select TransID, TFrom, TTo, Amount, DOT FROM transaction where TFrom=? and DOT between ? and ?");

            prst.setString(1, sessionUserID);

            prst.setDate(2, new java.sql.Date(from.getTime()));

            prst.setDate(3, new java.sql.Date(to.getTime()));

            rs = prst.executeQuery();

            while (rs.next()) {
                month_trans_List.add(new Report(rs.getInt("TransID"), rs.getString("TFrom"), rs.getString("TTo"), rs.getDouble("Amount"), rs.getDate("DOT")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prst != null) {
                    prst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return month_trans_List;

    }

    @Override
    public List<Report> dailyTransactionList(Date dayDate) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        List<Report> daily_trans_List = new ArrayList();

        try {
            prst = con.prepareStatement("select TransID, TFrom, TTo, Amount, DOT FROM transaction where DOT=? and TFrom=?");

            prst.setDate(1, new java.sql.Date(dayDate.getTime()));

            prst.setString(2, sessionUserID);

            rs = prst.executeQuery();

            while (rs.next()) {
                daily_trans_List.add(new Report(rs.getInt("TransID"), rs.getString("TFrom"), rs.getString("TTo"), rs.getDouble("Amount"), rs.getDate("DOT")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prst != null) {
                    prst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return daily_trans_List;
    }

    @Override
    public List<Report> allTransactionList() {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        List<Report> all_trans_List = new ArrayList();

        try {
            prst = con.prepareStatement("select TransID, TFrom, TTo, Amount, DOT FROM transaction where TFrom=?");

            prst.setString(1, sessionUserID);

            rs = prst.executeQuery();

            while (rs.next()) {
                all_trans_List.add(new Report(rs.getInt("TransID"), rs.getString("TFrom"), rs.getString("TTo"), rs.getDouble("Amount"), rs.getDate("DOT")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prst != null) {
                    prst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return all_trans_List;
    }

    @Override
    public List<Report> depositList(Date from , Date to) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        List<Report> deposit_trans_List = new ArrayList();

        try {
            prst = con.prepareStatement("select TransID, TFrom, TTo, Amount, DOT FROM transaction where TTo=? and DOT between ? and ?");

            prst.setString(1, sessionUserID);

            prst.setDate(2, new java.sql.Date(from.getTime()));

            prst.setDate(3, new java.sql.Date(to.getTime()));

            rs = prst.executeQuery();

            while (rs.next()) {
                deposit_trans_List.add(new Report(rs.getInt("TransID"), rs.getString("TFrom"), rs.getString("TTo"), rs.getDouble("Amount"), rs.getDate("DOT")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prst != null) {
                    prst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return deposit_trans_List;
    }

}
