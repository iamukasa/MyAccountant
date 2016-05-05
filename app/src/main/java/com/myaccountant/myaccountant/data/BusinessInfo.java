package com.myaccountant.myaccountant.data;

import net.redwarp.library.database.annotation.PrimaryKey;

/**
 * Created by user on 11/10/2015.
 */
public class BusinessInfo {

    @PrimaryKey
    public long key;
    String name;
    String location;
    String structure;
    String size;
    String description;

    public BusinessInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
