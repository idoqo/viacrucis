package io.github.idoqo.viacrucis;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import io.github.idoqo.viacrucis.adapter.StationsAdapter;

public class StationsActivity extends AppCompatActivity {

    ViewPager pager;
    StationsAdapter stationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        stationsAdapter = new StationsAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager_content);
        pager.setAdapter(stationsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_nav, menu);
        return true;
    }
}
