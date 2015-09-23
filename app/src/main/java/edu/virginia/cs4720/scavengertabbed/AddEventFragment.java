package edu.virginia.cs4720.scavengertabbed;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AddEventFragment extends Fragment {

    Location location;
    Double latitude;
    Double longitude;
    View v;

    public AddEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this frag

        MainActivity mainActivity = (MainActivity) getActivity();
        location = mainActivity.current;
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        v = inflater.inflate(R.layout.fragment_add_event, container, false);
        return v;
    }

}

