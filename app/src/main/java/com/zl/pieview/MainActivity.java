package com.zl.pieview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PieView mPieView = findViewById(R.id.pieView);
        List<PieBean> pieBeans = new ArrayList<>();
        PieBean pieBean1 = new PieBean(Color.parseColor("#26d7bc"),0.25f);
        PieBean pieBean2 = new PieBean(Color.parseColor("#24bdee"),0.3f);
        PieBean pieBean3 = new PieBean(Color.parseColor("#ffdb83"),0.2f);
        PieBean pieBean4 = new PieBean(Color.parseColor("#ff8181"),0.25f);
        pieBeans.add(pieBean1);
        pieBeans.add(pieBean2);
        pieBeans.add(pieBean3);
        pieBeans.add(pieBean4);
        mPieView.setPieBeans(pieBeans);

    }
}
