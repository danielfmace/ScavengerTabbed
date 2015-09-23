package edu.virginia.cs4720.scavengertabbed;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    Double latitude;
    Double longitude;

    Calendar myCalendar;
    EditText dateEditText;
    EditText latitudeEditText;
    EditText longitudeEditText;
    DatePickerDialog.OnDateSetListener date;

    public Location getCurrent() {
        return current;
    }

    public void setCurrent(Location current) {
        this.current = current;
    }

    Location current;

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    ArrayList<Event> events;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                doServiceWork(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        current = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        latitude = current.getLatitude();
        longitude = current.getLongitude();

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        events = new ArrayList<>();
        Event one = new Event("Info Session", "Capital One Info Session w/ bagels", "12:00 PM", "09/14/15", current);
        Event two = new Event("Info Session", "Capital One Info Session w/ bagels", "12:00 PM", "09/14/15", current);

        events.add(one);
        events.add(two);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllEventsFragment(), "All Events");
        adapter.addFragment(new MyEventsFragment(), "My Events");
        adapter.addFragment(new AddEventFragment(), "Add an Event");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    public void startService(View view) {
        Intent intent = new Intent(this, GPSService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, GPSService.class);
        stopService(intent);
    }

    private void doServiceWork(Location location) {
        current = location;
        //String currentDateTimeString = DateFormat.format("MM/dd/yy h:mm:ssaa", new Date()).toString();
        //Toast.makeText(this, "Location: " + location.toString() + " / " + currentDateTimeString, Toast.LENGTH_LONG).show();
    }

    public void onClickCurrentLocation(View view) {
        latitudeEditText = (EditText) findViewById(R.id.latitudeEditText);
        latitudeEditText.setText(latitude.toString());
        longitudeEditText = (EditText) findViewById(R.id.longitudeEditText);
        longitudeEditText.setText(longitude.toString());
    }

    public void onClickDate(View view) {
        new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {



        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        dateEditText.setText(sdf.format(myCalendar.getTime()));
    }

    public void addNewEvent(View view) {
        EditText title = (EditText) findViewById(R.id.titleEditText);
        EditText date = (EditText) findViewById(R.id.dateEditText);
        EditText time = (EditText) findViewById(R.id.timeEditText);
        EditText latitude = (EditText) findViewById(R.id.latitudeEditText);
        EditText longitude = (EditText) findViewById(R.id.longitudeEditText);
        EditText description = (EditText) findViewById(R.id.descriptionEditText);

        Intent intent = new Intent(this, NewEventActivity.class);
        intent.putExtra("title", title.getText().toString());
        intent.putExtra("date", date.getText().toString());
        intent.putExtra("time", time.getText().toString());
        intent.putExtra("description", description.getText().toString());
        intent.putExtra("latitude", latitude.getText().toString());
        intent.putExtra("longitude", longitude.getText().toString());

        startActivity(intent);
    }
}
