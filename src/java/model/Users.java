/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Users {
    private int userID;
    private String username;
    private String fullname;
    private String password;
    private String email;
    private int role;
    private String avatarURL;
    private int balance;
    private String phone;
    private String address;
    private String dob;
    
    public Users() {
    }

    public Users(int userID, String username, String fullname, String password, String email, int role, String avatarURL, int balance, String phone, String address, String dob) {
        this.userID = userID;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.avatarURL = avatarURL;
        this.balance = balance;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFullname(){
        return fullname;
    }
    
    public void setFullname(String fullname){
        if(fullname == null) fullname = "";
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Users{" + "userID=" + userID + ", username=" + username + ", fullname=" + fullname + ", password=" + password + ", email=" + email + ", role=" + role + ", avatarURL=" + avatarURL + ", balance=" + balance + ", phone=" + phone + ", address=" + address + ", dob=" + dob + '}';
    }
}