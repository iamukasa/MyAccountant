package com.myaccountant.myaccountant.data;

import net.redwarp.library.database.annotation.PrimaryKey;

/**
 * Created by user on 11/30/2015.
 */
public class Account {

    @PrimaryKey
    public long key;
    String email;
    String password;

    public Account(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
