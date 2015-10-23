/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.user;

import com.onlineBanking.Connection.DB_Connection;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.context.FacesContext;

/**
 *
 * @author salim ahmed
 */
public class UserServiceImpl implements UserService {

    FacesContext context = FacesContext.getCurrentInstance();
    String sessionUserID = context.getExternalContext().getSessionMap().get("user_name").toString();

    @Override
    public boolean userReg(User userReg) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        int accountNum = 0;

        int userID = 0;

        try {
            prst = con.prepareStatement("select max(AccountNumber) as AccountNumber from account");

            rs = prst.executeQuery();

            if (rs.next()) {
                accountNum = rs.getInt("AccountNumber");
            }
            if (accountNum == 0) {
                accountNum = 15000;

            }
            userReg.setAccountNumber(String.valueOf((accountNum) + 1));

            rs.close();
            prst.close();

            prst = con.prepareStatement("select max(UserID) as UserID from user");

            rs = prst.executeQuery();

            if (rs.next()) {
                userID = rs.getInt("UserID");
            }
            if (userID == 0) {
                userID = 0;

            }
            userReg.setUserID(userID + 1);

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
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        try {
            con.setAutoCommit(false);

            prst = con.prepareStatement("insert into user values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())");

            prst.setInt(1, userReg.getUserID());

            prst.setString(2, userReg.getName());

            prst.setString(3, userReg.getGender());

            prst.setDate(4, new java.sql.Date(userReg.getDob().getTime()));

            prst.setString(5, userReg.getOccupation());

            prst.setString(6, userReg.getMaritalStatus());

            prst.setString(7, userReg.getReligion());

            prst.setString(8, userReg.getFatherName());

            prst.setString(9, userReg.getMotherName());

            prst.setString(10, userReg.getPresentAddress());

            prst.setString(11, userReg.getPermanentAddress());

            prst.setString(12, userReg.getEmail());

            prst.setString(13, userReg.getPhoneNum());

            prst.setString(14, userReg.getNID());

            prst.setBoolean(15, false);

            if (userReg.getPhotos() == null) {
                prst.setString(16, userReg.getPhotos());
            } else {
                prst.setString(16, ((userID + 1) + "_" + userReg.getPhotos()));
            }

            prst.execute();
            prst.close();

            System.out.println("user Reg .................!!!");

            prst = con.prepareStatement("insert into account values(?,?,null,null,null,?,null)");

            prst.setString(1, userReg.getAccountNumber());

            prst.setString(2, userReg.getAccountType());

            prst.setInt(3, userReg.getUserID());

            prst.execute();
            prst.close();

            System.out.println("Account Add ................!!!");

            con.commit();

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
    public List<User> userList() {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        List<User> user_List = new ArrayList();

        try {
            prst = con.prepareStatement("select u.UserID, u.Name, u.Gender, u.DOB, u.Occupation, u.MaritalStatus, u.Religion, u.FatherName, u.MotherName, u.PresentAddress, u.PermantAddress, u.Email, u.Phone, u.NID,"
                    + "  u.Status, u.Photos, u.DOA, ac.AccountNumber, ac.Type"
                    + "	 from user u, account ac where u.UserID=ac.UserID and u.Status=false");

            rs = prst.executeQuery();

            while (rs.next()) {
                user_List.add(new User(rs.getInt("u.UserID"), rs.getString("u.Name"), rs.getString("u.Gender"), rs.getDate("u.DOB"), rs.getString("u.Occupation"),
                        rs.getString("u.MaritalStatus"), rs.getString("u.Religion"), rs.getString("u.FatherName"), rs.getString("u.MotherName"), rs.getString("u.PresentAddress"),
                        rs.getString("u.PermantAddress"), rs.getString("u.Email"), rs.getString("u.Phone"), rs.getString("u.NID"), rs.getBoolean("u.Status"),
                        rs.getString("u.Photos"), rs.getDate("u.DOA"), rs.getString("ac.AccountNumber"), rs.getString("ac.Type")
                ));
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
        return user_List;

    }

    @Override
    public boolean userUpdate(User userUpt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean userDelete(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean userAccept(String accountNo, int userID) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        try {

            con.setAutoCommit(false);

            prst = con.prepareStatement("update account set Balance=?, DateOfVarification=now(), password=?, AdminID=? where AccountNumber=?");

            prst.setDouble(1, 0.0);

            prst.setString(2, generateRandomPassword());

            prst.setString(3, sessionUserID);

            prst.setString(4, accountNo);

            prst.execute();
            prst.close();

            prst = con.prepareStatement("update user set Status=true where UserID=?");

            prst.setInt(1, userID);

            prst.execute();
            prst.close();
            con.commit();
//
//            MailSend ms = new MailSend();
//            ms.SendMail(accountNo, generateRandomPassword());
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
    public boolean userReject(String accountNo, int userID) {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        try {
            con.setAutoCommit(false);

            prst = con.prepareStatement("delete from account where AccountNumber=?");

            prst.setString(1, accountNo);

            prst.execute();
            prst.close();

            prst = con.prepareStatement("delete from user where UserID=?");

            prst.setInt(1, userID);

            prst.execute();
            prst.close();

            con.commit();

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

    private String generateRandomPassword() {

        Random random = new SecureRandom();

        int passwordLength = 8;

        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

        String pw = "";

        for (int i = 0; i < passwordLength; i++) {

            int index = (int) (random.nextDouble() * letters.length());

            pw += letters.substring(index, index + 1);
        }

        return pw;
    }

    @Override
    public List<User> inActiveUserNotifiyList() {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        List<User> inActiveUser_List = new ArrayList();

        try {
            prst = con.prepareStatement("select count(UserID) as user from user where Status = false");

            rs = prst.executeQuery();

            while (rs.next()) {
                inActiveUser_List.add(new User(rs.getInt("user")));
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
        return inActiveUser_List;

    }

    @Override
    public List<User> activeUserList() {

        DB_Connection o = new DB_Connection();

        Connection con = o.getConnection();

        PreparedStatement prst = null;

        ResultSet rs = null;

        List<User> activeUser_List = new ArrayList();

        try {
            prst = con.prepareStatement("select count(UserID) as user from user where Status = true");

            rs = prst.executeQuery();

            while (rs.next()) {
                activeUser_List.add(new User(rs.getString("user")));
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
        return activeUser_List;
    }

}
