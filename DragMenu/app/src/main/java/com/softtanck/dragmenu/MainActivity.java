package com.softtanck.dragmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.softtanck.dragmenu.view.DragMenu;


public class MainActivity extends Activity implements DragMenu.OnDragMenuStateChangeListener {

    private DragMenu dragMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dragMenu = (DragMenu) findViewById(R.id.dgm);
        dragMenu.setOnDragMenuListener(this);
    }

    @Override
    public void OnDragOpen() {
        Log.d("Tanck", "open");
    }

    @Override
    public void OnDragClose() {
        Log.d("Tanck", "close");
    }

    @Override
    public void OnDrag(int positon) {
        Log.d("Tanck", "OnDrag:" + positon);
    }
}
