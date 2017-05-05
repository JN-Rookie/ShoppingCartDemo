package com.sjsm.shoppingcartdemo.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class ChildBean extends Observable implements Observer {
    private String title,address,leibie,shuxing,jiage,jincheng,danwei,date,zhuangtai,shoulidate;
    private boolean isChecked;

    public ChildBean() {
    }

    public String getShoulidate() {
        return shoulidate;
    }

    public void setShoulidate(String shoulidate) {
        this.shoulidate = shoulidate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLeibie() {
        return leibie;
    }

    public void setLeibie(String leibie) {
        this.leibie = leibie;
    }

    public String getShuxing() {
        return shuxing;
    }

    public void setShuxing(String shuxing) {
        this.shuxing = shuxing;
    }

    public String getJiage() {
        return jiage;
    }

    public void setJiage(String jiage) {
        this.jiage = jiage;
    }

    public String getJincheng() {
        return jincheng;
    }

    public void setJincheng(String jincheng) {
        this.jincheng = jincheng;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public void changeChecked(){
        isChecked = !isChecked;
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object data) {
        if (data instanceof Boolean) {
            this.isChecked = (Boolean) data;
        }
    }
}