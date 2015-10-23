/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.Trans;

/**
 *
 * @author salim ahmed
 */
public interface TransactionService {
    
    boolean balanceTransfer(Transaction trans);
    
    boolean deposit(Transaction deposit);
    
    boolean isValidAccountNum(String toAccountNum);
    
    boolean isValidPassWord(String pass, String accountNum);
    
    boolean balanceQR(Transaction qr);
    
    boolean changePaswd4User(String oldPass , String newPass);
}
