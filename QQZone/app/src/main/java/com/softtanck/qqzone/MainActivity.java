package com.softtanck.qqzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.softtanck.qqzone.adapter.ZoneAdapter;
import com.softtanck.qqzone.bean.NormalMsg;
import com.softtanck.qqzone.bean.ZoneBaseMsg;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ZoneAdapter adapter;

    private List<ZoneBaseMsg> msgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            msgs.add(new NormalMsg());
        }
        adapter = new ZoneAdapter(msgs, MainActivity.this);
        listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }
}
