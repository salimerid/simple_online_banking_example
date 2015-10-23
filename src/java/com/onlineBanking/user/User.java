/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineBanking.user;

import java.util.Date;

/**
 *
 * @author salim ahmed
 */
public class User {
    private int userID;
    private String name;
    private String gender;
    private Date dob;
    private String occupation;
    private String maritalStatus;
    private String religion;
    private String fatherName;
    private String motherName;
    private String presentAddress;
    private String permanentAddress;
    private String email;
    private String phoneNum;
    private String NID;
    private boolean status;
    private String photos;
    private Date doa;
    
    private String accountNumber;
    private String accountType;
    
    private int inActiveUserCount;
    private String activeUser;


    public User() {
    }

    public User(int userID, String name, String gender, Date dob, String occupation, String maritalStatus, String religion, String fatherName, String motherName, String presentAddress, String permanentAddress, String email, String phoneNum, String NID, boolean status, String photos, Date doa, String accountNumber, String accountType) {
        this.userID = userID;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.religion = religion;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.email = email;
        this.phoneNum = phoneNum;
        this.NID = NID;
        this.status = status;
        this.photos = photos;
        this.doa = doa;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public User(int inActiveUserCount) {
        this.inActiveUserCount = inActiveUserCount;
    }

    public User(String activeUser) {
        this.activeUser = activeUser;
    }
   
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public Date getDoa() {
        return doa;
    }

    public void setDoa(Date doa) {
        this.doa = doa;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getInActiveUserCount() {
        return inActiveUserCount;
    }

    public void setInActiveUserCount(int inActiveUserCount) {
        this.inActiveUserCount = inActiveUserCount;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }
    
    
    
    
}
