package com.veeyaar.myscanner.model;

public class SessionLogin {
    public String UserName;
    public String Password;
    public String CompanyDB;

    public SessionLogin(String userName, String password, String companyDB) {
        UserName = userName;
        Password = password;
        CompanyDB = companyDB;
    }
}
