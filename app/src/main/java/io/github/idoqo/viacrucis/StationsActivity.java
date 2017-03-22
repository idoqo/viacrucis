package io.github.idoqo.viacrucis;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;

import io.github.idoqo.viacrucis.adapter.StationsAdapter;
import io.github.idoqo.viacrucis.helpers.Utils.Util;
import io.github.idoqo.viacrucis.model.Station;

public class StationsActivity extends FragmentActivity {

    ViewPager pager;
    StationsAdapter stationsAdapter;

    BottomNavigationView navigation;

    private ArrayList<Station> stations = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        StationsLoaderTask loaderTask = new StationsLoaderTask();
        loaderTask.execute();

        stationsAdapter = new StationsAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager_content);
        pager.setAdapter(stationsAdapter);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    private class StationsLoaderTask extends AsyncTask<Void, Void, ArrayList<Station>> {
        public ArrayList<Station> doInBackground(Void... params) {
            ArrayList<Station> stations = new ArrayList<>();
            String filename = "stations.json";
            String jsonString = Util.loadJsonFromAsset(StationsActivity.this, filename);
            if (jsonString == null) {
                Snackbar.make(navigation, "Failed to load data", Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode response = mapper.readTree(jsonString);
                    JsonNode stationsContainer = response.path("stations");
                    Iterator<JsonNode> iterator = response.elements();
                    while (iterator.hasNext()) {
                        Station station = mapper.readValue(iterator.next().traverse(), Station.class);
                        stations.add(station);
                    }
                } catch (Exception e) {
                    Log.e("stationsActivity", e.getMessage());
                }
            }
            return stations;
        }

        public void onPostExecute(ArrayList<Station> data) {
            super.onPostExecute(data);
            StationsActivity.this.stations = data;
        }
    }

}
