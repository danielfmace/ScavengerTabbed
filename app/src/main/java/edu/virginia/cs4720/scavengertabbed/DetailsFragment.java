package edu.virginia.cs4720.scavengertabbed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView name;
    private TextView description;
    private TextView latitude;
    private TextView longitude;
    private TextView date;
    private TextView time;

    private Event event;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        EventDetailActivity activity = (EventDetailActivity) getActivity();
        event = activity.getEvent();

        name = (TextView) view.findViewById(R.id.nameTextView);
        description = (TextView) view.findViewById(R.id.commentTextView);
        latitude = (TextView) view.findViewById(R.id.latitudeTextView);
        time = (TextView) view.findViewById(R.id.timeTextView);
        longitude = (TextView) view.findViewById(R.id.longitudeTextView);

        name.setText("Name: " + event.getName());
        description.setText("Description: " + event.getDescription());
        time.setText("Date and Time: " + event.getDate());
        latitude.setText("Latitude: " + event.getLatitude());
        longitude.setText("Longitude: " + event.getLongitude());

        return view;

    }


}
