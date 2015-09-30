package edu.virginia.cs4720.scavengertabbed;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MyEventsFragment extends Fragment{
    Location location;

    protected ListView mListView;
    protected ArrayAdapter<Event> mAdapter;
    protected ArrayList<Event> myEvents;

    public MyEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity main = (MainActivity) getActivity();
        myEvents = main.getMyEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_events, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        mListView = (ListView) view.findViewById(R.id.listView);

        MainActivity main = (MainActivity) getActivity();
        myEvents = main.getMyEvents();

        mAdapter = new ArrayAdapter<Event>(getActivity(), android.R.layout.simple_list_item_1, myEvents);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Event e = (Event) adapter.getItemAtPosition(position);
                long id = e.getId();
                Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }

}