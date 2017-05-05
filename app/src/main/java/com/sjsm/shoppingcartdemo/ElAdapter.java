package com.sjsm.shoppingcartdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sjsm.shoppingcartdemo.model.ChildBean;
import com.sjsm.shoppingcartdemo.model.GroupBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class ElAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<GroupBean> mData = new ArrayList<GroupBean>();
    private String Acceptance;

    public ElAdapter(Context context, List<GroupBean> data) {
        mContext = context;
        mData=data;
    }

    public ElAdapter(Context context, List<GroupBean> data, String Acceptance) {
        mContext = context;
        mData=data;
        this.Acceptance=Acceptance;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder=null;
        final GroupBean group = (GroupBean) getGroup(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.rv_list_item_title, parent,false);
            holder=new GroupViewHolder();
            holder.select=(CheckBox) convertView.findViewById(R.id.cb_select_group);
            holder.title = (TextView) convertView.findViewById(R.id.tv_group_title);
            convertView.setTag(holder);
        }else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.title.setText(group.getName());
        holder.select.setChecked(group.isChecked());
        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.changeChecked();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder=null;
        ChildBean child= (ChildBean) getChild(groupPosition,childPosition);
        if(convertView==null){
            holder=new ChildViewHolder();
            if(Acceptance!=null){
                convertView= LayoutInflater.from(mContext).inflate(R.layout.item_ysl_recyclerview,parent,false);
                holder.shoulidate= (TextView) convertView.findViewById(R.id.shoulidate);
            }else {
                convertView= LayoutInflater.from(mContext).inflate(R.layout.rv_list_item_content,parent,false);
            }
            holder.child_select= (CheckBox) convertView.findViewById(R.id.cb_select_child);
            holder.title= (TextView) convertView.findViewById(R.id.title);
            holder.address= (TextView) convertView.findViewById(R.id.address);
            holder.leibie= (TextView) convertView.findViewById(R.id.leibie);
            holder.shuxing= (TextView) convertView.findViewById(R.id.shuxing);
            holder.jiage= (TextView) convertView.findViewById(R.id.jiage);
            holder.jincheng= (TextView) convertView.findViewById(R.id.jincheng);
            holder.danwei= (TextView) convertView.findViewById(R.id.danwei);
            holder.date= (TextView) convertView.findViewById(R.id.date);
            holder.zhuangtai= (TextView) convertView.findViewById(R.id.zhuangtai);
            convertView.setTag(holder);
        }else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        if(Acceptance!=null){
            holder.shoulidate.setText(child.getShoulidate());
        }
        holder.title.setText(child.getTitle());
        holder.address.setText(child.getAddress());
        holder.leibie.setText(child.getLeibie());
        holder.shuxing.setText(child.getShuxing());
        holder.jincheng.setText(child.getJincheng());
        holder.danwei.setText(child.getDanwei());
        holder.date.setText(child.getDate());
        holder.zhuangtai.setText(child.getZhuangtai());
        holder.child_select.setChecked(child.isChecked());
        holder.child_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildBean bean=mData.get(groupPosition).getChildList().get(childPosition);
                bean.changeChecked();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public boolean selectAll(List<GroupBean> list, boolean isSelectAll) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChecked(isSelectAll);
            for (int j = 0; j < list.get(i).getChildList().size(); j++) {
                list.get(i).getChildList().get(j).setChecked(isSelectAll);
            }
        }
        notifyDataSetChanged();
        return isSelectAll;
    }

    public void removeGroupData(int GroupPotion){
        mData.remove(GroupPotion);
        notifyDataSetChanged();
    }

    public void removeChildData(int GroupPotion,int ChildPotion){
        List<ChildBean> childList = mData.get(GroupPotion).getChildList();
        childList.remove(ChildPotion);
    }

    public void removeAll(){
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class GroupViewHolder {
        CheckBox select;
        TextView title;
    }

    class ChildViewHolder{
        CheckBox child_select;
        TextView title,address,leibie,shuxing,jiage,jincheng,danwei,date,zhuangtai,shoulidate;
    }

}
