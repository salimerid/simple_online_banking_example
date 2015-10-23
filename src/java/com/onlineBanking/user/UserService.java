/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.user;

import java.util.List;

/**
 *
 * @author salim ahmed
 */
public interface UserService {
    
    boolean userReg(User userReg);

    List<User> userList();

    boolean userUpdate(User userUpt);
    
    boolean userDelete(int userID);
    
    boolean userAccept(String accountNo, int userID);
    
    boolean userReject(String accountNo, int userID);
    
    List<User> inActiveUserNotifiyList();
    
    List<User> activeUserList();

}
