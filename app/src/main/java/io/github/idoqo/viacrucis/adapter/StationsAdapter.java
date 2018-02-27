package io.github.idoqo.viacrucis.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import io.github.idoqo.viacrucis.StationFragment;
import io.github.idoqo.viacrucis.model.Station;


public class StationsAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Station> stations;


    public StationsAdapter(FragmentManager fm) {
        super(fm);
    }

    public StationsAdapter(FragmentManager fm, ArrayList<Station> st) {
        super(fm);
        this.stations = st;
    }

    public Fragment getItem(int position) {
        Fragment fragment = new StationFragment();
        Bundle args = new Bundle();
        //Station station = Stations.get(position);
        args.putInt(StationFragment.POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public int getCount() {
        return 17;
    }

    public CharSequence getPageTitle(int position) {
        return "Page "+position;
    }
}
