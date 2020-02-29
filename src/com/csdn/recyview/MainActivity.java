package com.csdn.recyview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.csdn.recyview.recylerview.BaseEnty;
import com.csdn.recyview.recylerview.NameAdapter;
import com.csdn.recyview.recylerview.NameItem;
import com.csdn.recyview.recylerview.SimplePaddingDecoration;
import com.csdn.recyview.recylerview.TwoItemAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {


    private RecyclerView recycler_view;
    private List<NameItem> nameItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }


    private void initView() {
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        nameItems.clear();
        for (int i = 0; i < 10; i++) {
            NameItem item = new NameItem();
            item.setName("0000000000e" + i);

            if (i < 5) {
                item.setType(BaseEnty.AA);
            } else {
                item.setType(BaseEnty.BB);
            }
            nameItems.add(item);
        }
        NameAdapter nameAdapter = new NameAdapter(R.layout.name_item, nameItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.addItemDecoration(new SimplePaddingDecoration(this));
        recycler_view.setLayoutManager(layoutManager);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_header1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_header1, null);

        View footer = LayoutInflater.from(this).inflate(R.layout.layout_footerone, null);


        nameAdapter.addHeaderView(view);
        nameAdapter.addHeaderView(view2);
        nameAdapter.addFootView(footer);


        TwoItemAdapter twoItemAdapter = new TwoItemAdapter(nameItems);


        twoItemAdapter.addHeaderView(view);
        twoItemAdapter.addHeaderView(view2);
        twoItemAdapter.addFootView(footer);


        recycler_view.setAdapter(twoItemAdapter);


    }
}
