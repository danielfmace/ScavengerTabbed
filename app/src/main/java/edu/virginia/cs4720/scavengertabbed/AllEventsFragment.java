package edu.virginia.cs4720.scavengertabbed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class AllEventsFragment extends Fragment{
    protected ListView mListView;
    protected ArrayAdapter<Event> mAdapter;
    protected ArrayList<Event> events;

    public AllEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity main = (MainActivity) getActivity();
        events = main.getEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_events, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        mAdapter = new ArrayAdapter<Event>(getActivity(), android.R.layout.simple_list_item_1, events);
        mListView.setAdapter(mAdapter);

    }


}
