package com.sjsm.shoppingcartdemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class GroupBean extends Observable implements Observer {
    private String name;
    private boolean isChecked;
    private List<ChildBean> childList = new ArrayList<ChildBean>();

    public GroupBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildBean> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildBean> childList) {
        this.childList = childList;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void changeChecked(){
        isChecked = !isChecked;
        setChanged();
        notifyObservers(isChecked);
    }

    public GroupBean(String name, List<ChildBean> childList) {
        this.name = name;
        this.childList = childList;
    }

    @Override
    public void update(Observable o, Object data) {
        boolean flag = true;
        for (ChildBean Bean : childList) {
            if (Bean.isChecked() == false) {
                flag = false;
            }
        }
        this.isChecked = flag;
    }


}
