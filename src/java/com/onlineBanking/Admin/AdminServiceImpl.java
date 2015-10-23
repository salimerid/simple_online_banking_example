/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.Admin;

import com.onlineBanking.Connection.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author salim ahmed
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public boolean addAdmin(Admin adm) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        int adminID = 0;

        try {
            prst = con.prepareStatement("select max(AdminID) as AdminID from admin");

            rs = prst.executeQuery();

            if (rs.next()) {
                adminID = rs.getInt("AdminID");
            }
            if (adminID == 0) {
                adminID = 15150;

            }
            adm.setAdminID(adminID + 1);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prst != null) {
                    prst.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        try {

            prst = con.prepareStatement("insert into admin values (?,?,?,?,?,?)");

            prst.setInt(1, adm.getAdminID());

            prst.setString(2, adm.getName());

            prst.setString(3, adm.getDesignation());

            prst.setString(4, adm.getPassword());

            if (adm.getPhotos() == null) {
                prst.setString(5, adm.getPhotos());
            } else {
                prst.setString(5, ((adminID + 1) + "_" + adm.getPhotos()));
            }

            prst.setBoolean(6, true);

            prst.execute();

            System.out.println("Admin Add ................!!!");

            return true;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prst != null) {
                    prst.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return false;
    }

    @Override
    public List<Admin> adminList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateAdmin(Admin upAdm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAdmin(Admin delAdm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changePaswd4Admin(String oldPass, String newPass) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        FacesContext context = FacesContext.getCurrentInstance();
        String adminID = context.getExternalContext().getSessionMap().get("user_name").toString();

        try {
            prst = con.prepareStatement("update admin set Password=? where AdminID=? and Password=?");

            prst.setString(1, newPass);

            prst.setString(2, adminID);

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

    @Override
    public boolean isValidPassWord(String pass, String adminID) {
        
        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        try {
            prst = con.prepareStatement("select AdminID from admin where Password=? and AdminID=?");

            prst.setString(1, pass);

            prst.setString(2, adminID);

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

}
