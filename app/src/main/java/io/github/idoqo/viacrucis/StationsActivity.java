package io.github.idoqo.viacrucis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import io.github.idoqo.viacrucis.adapter.StationsAdapter;

public class StationsActivity extends FragmentActivity {

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

}
