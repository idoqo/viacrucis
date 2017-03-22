package io.github.idoqo.viacrucis;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;

import io.github.idoqo.viacrucis.helpers.Utils.Util;
import io.github.idoqo.viacrucis.model.Station;

public class StationFragment extends Fragment{
    public static final String POSITION = "position";

    private ArrayList<Station> stations = new ArrayList<>();

    private View mainView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StationsLoaderTask loaderTask = new StationsLoaderTask();
        loaderTask.execute();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int position = args.getInt(POSITION);
        //1 is opening prayer, 16 is closing prayer...if this makes sense is story for another day
        if (position == 1 || position == 16) {
            mainView = inflater.inflate(R.layout.station_layout, container, false);
        } else {
            mainView = inflater.inflate(R.layout.station_layout, container, false);
        }
        return mainView;
    }

    private void addFonts(View view) {
        TextView titleView = (TextView) view.findViewById(R.id.station_title);
        TextView readingTitleView =  (TextView) view.findViewById(R.id.section_title_reading);
        TextView adorationTitleView = (TextView) view.findViewById(R.id.section_title_adoration);
        TextView meditationTitleView = (TextView) view.findViewById(R.id.section_title_meditation);
        TextView prayerTitleView = (TextView) view.findViewById(R.id.section_title_prayer);
        TextView hymnTitleView = (TextView) view.findViewById(R.id.section_title_hymn);

        //AssetManager manager = getActivity().getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OldLondon.ttf");
        titleView.setTypeface(typeface);
        readingTitleView.setTypeface(typeface);
        adorationTitleView.setTypeface(typeface);
        meditationTitleView.setTypeface(typeface);
        prayerTitleView.setTypeface(typeface);
        hymnTitleView.setTypeface(typeface);
    }



    private class StationsLoaderTask extends AsyncTask<Void, Void, ArrayList<Station>> {
        public ArrayList<Station> doInBackground(Void... params) {
            ArrayList<Station> stations = new ArrayList<>();
            String filename = "stations.json";
            String jsonString = Util.loadJsonFromAsset(getContext(), filename);
            if (jsonString == null) {
                Snackbar.make(mainView, "Failed to load data", Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode response = mapper.readTree(jsonString);
                    JsonNode stationsContainer = response.path("stations");
                    Iterator<JsonNode> iterator = stationsContainer.elements();
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
            stations = data;

            Log.e("counter", Integer.toString(stations.size()));

            addFonts(mainView);

            Bundle args = getArguments();
            int position = args.getInt(POSITION);
            Station station = stations.get(position);
            //0 = first index = opening prayer
            //15 = last index = closing prayer
            if (position == 0 || position == 15) {
                renderPrayer(station, mainView);
            } else {
                renderStation(station, mainView);
            }
        }
    }

    private void renderPrayer(Station prayer, View view) {
        TextView numeralView = (TextView) view.findViewById(R.id.roman_numeral);
        TextView titleView = (TextView) view.findViewById(R.id.station_title);
        TextView scriptureView = (TextView) view.findViewById(R.id.scripture);
        TextView readingView =  (TextView) view.findViewById(R.id.reading);
        TextView prayerView = (TextView) view.findViewById(R.id.prayer);
        TextView hymnView = (TextView) view.findViewById(R.id.hymn);

        titleView.setText(prayer.getTitle());
        readingView.setText(prayer.getReading());
        prayerView.setText(prayer.getPrayer());
        hymnView.setText(prayer.getHymn());
        scriptureView.setText(prayer.getScripture());
    }

    private void renderStation(Station station, View view) {
        TextView numeralView = (TextView) view.findViewById(R.id.roman_numeral);
        TextView titleView = (TextView) view.findViewById(R.id.station_title);
        TextView scriptureView = (TextView) view.findViewById(R.id.scripture);
        TextView readingView =  (TextView) view.findViewById(R.id.reading);
        TextView meditationScriptureView = (TextView) view.findViewById(R.id.meditation_scripture);
        TextView meditationReadingView = (TextView) view.findViewById(R.id.meditation_reading);
        TextView prayerView = (TextView) view.findViewById(R.id.prayer);
        TextView hymnView = (TextView) view.findViewById(R.id.hymn);

        numeralView.setText(station.getRomanNumeral());
        titleView.setText(station.getTitle());
        readingView.setText(station.getReading());
        meditationScriptureView.setText(station.getMeditation().getScripture());
        meditationReadingView.setText(station.getMeditation().getReading());
        prayerView.setText(station.getPrayer());
        hymnView.setText(station.getHymn());
        scriptureView.setText(station.getScripture());
    }
}
