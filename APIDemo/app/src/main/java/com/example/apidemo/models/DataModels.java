package com.example.apidemo.models;

import com.google.gson.annotations.SerializedName;

public class DataModels {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    @SerializedName("status")
    private String status;

    @SerializedName("dateCreated")
    private String dateCreated;

    @SerializedName("owner")
    private String owner;

    public DataModels(String title, String desc,String owner) {
        this.title = title;
        this.desc = desc;
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
