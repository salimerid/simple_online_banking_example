/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.Admin;

import java.util.List;

/**
 *
 * @author salim ahmed
 */
public interface AdminService {

    boolean addAdmin(Admin adm);
    
    List<Admin> adminList();
    
    boolean updateAdmin(Admin upAdm);
    
    boolean deleteAdmin(Admin delAdm);
    
    boolean changePaswd4Admin(String oldPass , String newPass);
    
    boolean isValidPassWord(String pass, String adminID);
}
