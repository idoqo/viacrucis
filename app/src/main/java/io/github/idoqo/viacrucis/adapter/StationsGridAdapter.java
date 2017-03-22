package io.github.idoqo.viacrucis.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.github.idoqo.viacrucis.R;
import io.github.idoqo.viacrucis.model.Station;


public class StationsGridAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Station> stations;

    public StationsGridAdapter(Context ctx, ArrayList<Station> sts) {
        this.context = ctx;
        this.stations = sts;
    }

    public int getCount() {
        return stations.size();
    }

    public long getItemId(int position) {
        return 0;
    }

    public Object getItem(int position) {
        return stations.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Station station = stations.get(position);
        if (convertView == null) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.station_grid_item, parent, false);
        }
        final ImageView stationImage = (ImageView) convertView.findViewById(R.id.station_image);
        final TextView stationTitle = (TextView) convertView.findViewById(R.id.station_title);
        final TextView stationNumeral = (TextView) convertView.findViewById(R.id.station_numeral);

        //stationImage.setImageResource();
        stationTitle.setText(station.getTitle());
        if (station.getRomanNumeral() != null) {
            stationNumeral.setText(station.getRomanNumeral());
        }
        return convertView;
    }
}
