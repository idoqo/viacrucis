package io.github.idoqo.viacrucis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.idoqo.viacrucis.R;
import io.github.idoqo.viacrucis.model.Station;


public class StationsRecyclerAdapter extends RecyclerView.Adapter<StationsRecyclerAdapter.StationsViewHolder> {
    private Context context;
    private ArrayList<Station> stations;

    public int getItemCount() {
        return (stations == null) ? 0 : stations.size();
    }

    public void onBindViewHolder(StationsViewHolder holder, int position) {

    }

    public StationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.station_list_item, parent, false);
        return new StationsViewHolder(rootView);
    }


    static class StationsViewHolder extends RecyclerView.ViewHolder {
        private ImageView stationImage;
        private TextView stationTitle;

        public StationsViewHolder(View itemView) {
            super(itemView);
            stationImage = (ImageView) itemView.findViewById(R.id.station_image);
            stationTitle = (TextView) itemView.findViewById(R.id.station_title);
        }
    }
}
