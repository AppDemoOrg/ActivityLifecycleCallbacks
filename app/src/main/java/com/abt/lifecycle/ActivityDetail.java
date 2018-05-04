package com.abt.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huangweiqi on 05/05/2018.
 */
public class ActivityDetail extends AppCompatActivity {

    @BindView(R.id.currentGoodsId)
    TextView mCurrentGoodsId;
    @BindView(R.id.recGoods1)
    Button mRecGoods1;
    @BindView(R.id.recGoods2)
    Button mRecGoods2;
    @BindView(R.id.recGoods3)
    Button mRecGoods3;

    private String ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ID = getIntent().getStringExtra("ID");
        mCurrentGoodsId.setText("当前页面的ID: "+ID);
    }

    public String getID() {
        return ID;
    }

    public void toGoodDetail(String id) {
        LifecycleApp.toGoodDetail(id);
        Intent intent = new Intent(ActivityDetail.this, ActivityDetail.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    @OnClick ({R.id.recGoods1, R.id.recGoods2, R.id.recGoods3})
    public void recGoodClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.recGoods1:
                String goodId1 = "101";
                toGoodDetail(goodId1);
                break;
            case R.id.recGoods2:
                String goodId2 = "102";
                toGoodDetail(goodId2);
                break;
            case R.id.recGoods3:
                String goodId3 = "103";
                toGoodDetail(goodId3);
                break;
        }
    }
}
