package io.github.idoqo.viacrucis;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.github.idoqo.viacrucis.adapter.StationsAdapter;

public class StationsActivity extends AppCompatActivity {

    ViewPager pager;
    StationsAdapter stationsAdapter;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        bottomNav = findViewById(R.id.navigation);

        stationsAdapter = new StationsAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.pager_content);
        pager.setAdapter(stationsAdapter);

        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_nav, menu);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_next:
                        pager.setCurrentItem(pager.getCurrentItem() + 1);
                        return true;
                    case R.id.nav_prev:
                        pager.setCurrentItem(pager.getCurrentItem() - 1);
                        return true;
                    default:
                        return true;
                }
            }
        };
    }
}
