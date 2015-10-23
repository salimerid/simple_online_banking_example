/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Login;

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
public class LoginDaoImpl implements LoginDao {

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean isLogin4Admin(SystemUser obj) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement pst = null;

        ResultSet rs = null;

        FacesContext context = FacesContext.getCurrentInstance();

        try {

            pst = con.prepareStatement("select AdminID from admin where AdminID=? and Password=? and Status=?");

            pst.setString(1, obj.getUserName());

            pst.setString(2, obj.getPassword());

            pst.setBoolean(3, true);

            rs = pst.executeQuery();

            while (rs.next()) {
                context.getExternalContext().getSessionMap().put("AdminID", rs.getInt(1));

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            System.out.println("Hello admin:" + obj.getUserName());

            obj = null;
        }

        return false;
    }

    @Override
    public boolean isLogin4User(SystemUser obj) {
        
        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement pst = null;

        ResultSet rs = null;

        FacesContext context = FacesContext.getCurrentInstance();

        try {

            pst = con.prepareStatement("select a.AccountNumber from account a, user u where a.AccountNumber=? and a.Password=? and a.UserID=u.UserID and u.Status=?");

            pst.setString(1, obj.getUserName());

            pst.setString(2, obj.getPassword());

            pst.setBoolean(3, true);

            rs = pst.executeQuery();

            while (rs.next()) {
                context.getExternalContext().getSessionMap().put("a.AccountNumber", rs.getString(1));

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            System.out.println("Hello user:" + obj.getUserName());

            obj = null;
        }

        return false;
    }
}
