package com.myaccountant.myaccountant.data;

import net.redwarp.library.database.annotation.PrimaryKey;

/**
 * Created by user on 11/29/2015.
 */
public class OpeningInventory {
    @PrimaryKey
    public long key;
    String category;
    String name;
    String description;
    String time;
    String date;
    double worth;

    public OpeningInventory(){}

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }
}
