package com.sjsm.shoppingcartdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;

import com.sjsm.shoppingcartdemo.model.ChildBean;
import com.sjsm.shoppingcartdemo.model.GroupBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ExpandableListView mListView;
    private CheckBox mCheckBox;
    private Button mBtn_del, mBtn_sub;
    private ElAdapter mAdapter;
    private List<GroupBean> mData = new ArrayList<GroupBean>();
    private GroupBean mGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        initData();
        initView();
    }

    private void initView() {
        mListView = (ExpandableListView) findViewById(R.id.expandableListView);
        mCheckBox = (CheckBox) findViewById(R.id.cb_select);
        mBtn_del = (Button)findViewById(R.id.btn_del);
        mBtn_sub = (Button)findViewById(R.id.btn_sub);
        mAdapter=new ElAdapter(mContext,mData);
        mListView.setGroupIndicator(null);
        mListView.setAdapter(mAdapter);
        for (int i = 0; i < mData.size(); i++) {
            mListView.expandGroup(i);
        }
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        mAdapter.notifyDataSetChanged();
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mAdapter.selectAll(mData,true);
                }else {
                    mAdapter.selectAll(mData,false);
                }
            }
        });

        mBtn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMsgUtils.ShowMessageWithCancelBtn(mContext, "确定要删除吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mCheckBox.isChecked()){
                            mAdapter.removeAll();
                        }

                        for (int i = 0; i < mData.size(); i++) {
                            if(mData.get(i).isChecked()){
                                mAdapter.removeGroupData(i);
                            }
                            for (int j = mData.get(i).getChildList().size()-1; j >=0 ; j--) {
                                if(mData.get(i).getChildList().get(j).isChecked()){
                                    mAdapter.removeChildData(i,j);
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

        mBtn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMsgUtils.ShowToast(mContext,"提交");
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            mGroup = new GroupBean();
            mGroup.setName("钢筋原材");
            mGroup.setChecked(false);
            List<ChildBean> data = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ChildBean child=new ChildBean();
                child.setTitle("钢筋原材");
                child.setAddress("某某某小区5号楼");
                child.setLeibie("钢筋混凝土用热轧带肋钢筋");
                child.setShuxing("HPB335");
                child.setJiage("8");
                child.setJincheng("初检");
                child.setDanwei("济南钢铁厂");
                child.setDate("2016-05-01");
                child.setZhuangtai("待提交");
                child.setChecked(false);
                data.add(child);
                child.addObserver(mGroup);
                mGroup.addObserver(child);
            }
            mGroup.setChildList(data);
            mData.add(mGroup);
        }
    }


}
