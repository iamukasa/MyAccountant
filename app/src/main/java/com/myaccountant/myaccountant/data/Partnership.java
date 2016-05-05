package com.myaccountant.myaccountant.data;

import net.redwarp.library.database.annotation.PrimaryKey;

/**
 * Created by user on 11/10/2015.
 */
public class Partnership {

    @PrimaryKey
    public long key;
    String name;
    double amount;
    double interestOnCapital;
    double interestOnDrawings;
    double profitSharingRatio;

    public Partnership(){

    }

    public double getInterestOnDrawings() {
        return interestOnDrawings;
    }

    public void setInterestOnDrawings(double interestOnDrawings) {
        this.interestOnDrawings = interestOnDrawings;
    }

    public double getProfitSharingRatio() {
        return profitSharingRatio;
    }

    public void setProfitSharingRatio(double profitSharingRatio) {
        this.profitSharingRatio = profitSharingRatio;
    }

    public double getInterestOnCapital() {
        return interestOnCapital;
    }

    public void setInterestOnCapital(double interestOnCapital) {
        this.interestOnCapital = interestOnCapital;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
