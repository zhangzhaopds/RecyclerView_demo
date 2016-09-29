package com.tongxingpay.zhangzhao.recyclerview_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private MyAdapter myAdapter;
    private List<Actor> myDatas;
    private String[] pics = {"p1", "p2", "p3", "p2", "p3", "p2", "p3", "p2", "p3"};
    private String[] names = {"name1", "name2", "name3", "name2", "name3", "name2", "name3", "name2", "name3"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myRecyclerView = (RecyclerView)findViewById(R.id.main_recyclerview);


        myDatas = new ArrayList<Actor>();

        for (int i = 0; i < names.length; i++) {
            myDatas.add(new Actor(names[i], pics[i]));
        }

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter(this, myDatas);
        myRecyclerView.setAdapter(myAdapter);




    }
}
