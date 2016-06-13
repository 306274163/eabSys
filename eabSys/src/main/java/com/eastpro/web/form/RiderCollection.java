package com.eastpro.web.form;

import java.util.ArrayList;

/**
 * Created by Administrator on 15-6-17.
 */
public class RiderCollection {
    private String riderType;
    private String riderTypeDesc;
    private ArrayList<Rider> riderList = new ArrayList<Rider>();

    public String getRiderType() {
        return riderType;
    }

    public void setRiderType(String riderType) {
        this.riderType = riderType;
    }

    public String getRiderTypeDesc() {
        return riderTypeDesc;
    }

    public void setRiderTypeDesc(String riderTypeDesc) {
        this.riderTypeDesc = riderTypeDesc;
    }

    public ArrayList<Rider> getRiderList() {
        return riderList;
    }

    public void setRiderList(ArrayList<Rider> riderList) {
        this.riderList = riderList;
    }
}
