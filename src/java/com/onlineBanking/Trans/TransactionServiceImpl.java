/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Trans;

import com.onlineBanking.Connection.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author salim ahmed
 */
public class TransactionServiceImpl implements TransactionService {

    FacesContext context = FacesContext.getCurrentInstance();
    String sessionUserID = context.getExternalContext().getSessionMap().get("user_name").toString();

    @Override
    public boolean balanceTransfer(Transaction trans) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        boolean flag = false;

        try {
            con.setAutoCommit(false);

            prst = con.prepareStatement("select Balance from account where AccountNumber=?");

            prst.setString(1, sessionUserID);

            rs = prst.executeQuery();

            while (rs.next()) {
                if (rs.getDouble(1) > trans.getAmount()) {
                    flag = true;
                } else {
                    trans.setBalance(rs.getDouble(1));
                }
            }
            rs.close();

            if (flag) {
                prst = con.prepareStatement("update account set Balance=Balance-? where AccountNumber=?");

                prst.setDouble(1, trans.getAmount());

                prst.setString(2, sessionUserID);

                prst.executeUpdate();
                prst.close();

                prst = con.prepareStatement("update account set Balance=Balance+? where AccountNumber=?");

                prst.setDouble(1, trans.getAmount());

                prst.setString(2, trans.getTo());

                prst.executeUpdate();
                prst.close();

                prst = con.prepareStatement("insert into transaction values (null,?,?,?,now())");

                prst.setString(1, sessionUserID);

                prst.setString(2, trans.getTo());

                prst.setDouble(3, trans.getAmount());

                prst.execute();
                prst.close();

                prst = con.prepareStatement("select Balance from account where AccountNumber=?");

                prst.setString(1, sessionUserID);

                rs = prst.executeQuery();

                if (rs.next()) {
                    trans.setBalance(rs.getDouble(1));
                }

                rs.close();
                prst.close();

                con.commit();
                con.close();

                return true;

            }
        } catch (SQLException e) {
            System.out.println(e);
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

        return false;

    }

    @Override
    public boolean deposit(Transaction deposit) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        boolean flag = false;

        try {
            con.setAutoCommit(false);

            prst = con.prepareStatement("select Balance from account where AccountNumber=?");

            prst.setString(1, deposit.getTo());

            rs = prst.executeQuery();

            while (rs.next()) {
                if (rs.getDouble(1) > deposit.getAmount()) {
                    flag = true;
                } else {
                    deposit.setBalance(rs.getDouble(1));
                }
            }
            rs.close();

            if (flag) {
                prst = con.prepareStatement("update account set Balance=Balance-? where AccountNumber=?");

                prst.setDouble(1, deposit.getAmount());

                prst.setString(2, deposit.getTo());

                prst.executeUpdate();
                prst.close();

                prst = con.prepareStatement("update account set Balance=Balance+? where AccountNumber=?");

                prst.setDouble(1, deposit.getAmount());

                prst.setString(2, sessionUserID);

                prst.executeUpdate();
                prst.close();

                prst = con.prepareStatement("insert into transaction values (null,?,?,?,now())");

                prst.setString(1, deposit.getTo());

                prst.setString(2, sessionUserID);

                prst.setDouble(3, deposit.getAmount());

                prst.execute();
                prst.close();

                prst = con.prepareStatement("select Balance from account where AccountNumber=?");

                prst.setString(1, sessionUserID);

                rs = prst.executeQuery();

                if (rs.next()) {
                    deposit.setBalance(rs.getDouble(1));
                }

                rs.close();
                prst.close();

                con.commit();
                con.close();

                return true;

            }
        } catch (SQLException e) {
            System.out.println(e);
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

        return false;
    }

    @Override
    public boolean isValidAccountNum(String toAccountNum) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        try {
            prst = con.prepareStatement("select AccountNumber from account where AccountNumber=?");

            prst.setString(1, toAccountNum);

            rs = prst.executeQuery();

            if (rs.next()) {
                rs.close();

                prst.close();

                con.close();

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
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
        return false;
    }

    @Override
    public boolean isValidPassWord(String pass, String accountNum) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        try {
            prst = con.prepareStatement("select AccountNumber from account where Password=? and AccountNumber=?");

            prst.setString(1, pass);

            prst.setString(2, accountNum);

            rs = prst.executeQuery();

            if (rs.next()) {

                rs.close();

                prst.close();

                con.close();

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
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
        return false;
    }

    @Override
    public boolean balanceQR(Transaction qr) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        try {
            prst = con.prepareStatement("select Balance from account where AccountNumber=? and Password=?");

            prst.setString(1, sessionUserID);

            prst.setString(2, qr.getPassword());

            rs = prst.executeQuery();

            if (rs.next()) {
                qr.setBalanceQR(rs.getDouble(1));
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
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

        return false;
    }

    @Override
    public boolean changePaswd4User(String oldPass, String newPass) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        try {
            prst = con.prepareStatement("update account set Password=? where AccountNumber=? and Password=?");

            prst.setString(1, newPass);

            prst.setString(2, sessionUserID);

            prst.setString(3, oldPass);

            prst.executeUpdate();

            prst.close();

            con.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {

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

        return false;
    }

}
