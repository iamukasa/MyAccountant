package com.myaccountant.myaccountant.data;

import net.redwarp.library.database.annotation.PrimaryKey;

/**
 * Created by user on 11/10/2015.
 */
public class NonCurrentAssets {

    @PrimaryKey
    public long key;
    String category;
    String depreciationRateMethod;
    String name;
    String description;
    String date;
    String time;
    double cost;
    double depreciationRate;
    double accumulatedDepreciation;
    double netBookValue;

    public NonCurrentAssets(){

    }

    public double getNetBookValue() {
        return netBookValue;
    }

    public void setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDepreciationRateMethod() {
        return depreciationRateMethod;
    }

    public void setDepreciationRateMethod(String depreciationRateMethod) {
        this.depreciationRateMethod = depreciationRateMethod;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(double depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public double getAccumulatedDepreciation() {
        return accumulatedDepreciation;
    }

    public void setAccumulatedDepreciation(double accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }
}
