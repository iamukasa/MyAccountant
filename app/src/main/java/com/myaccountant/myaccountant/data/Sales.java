package com.myaccountant.myaccountant.data;

import net.redwarp.library.database.annotation.PrimaryKey;

/**
 * Created by user on 11/10/2015.
 */
public class Sales {

    @PrimaryKey
    public long key;
    String name;
    String description;
    String time;
    String date;
    double quantity;
    double amount;

    public Sales(){

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
