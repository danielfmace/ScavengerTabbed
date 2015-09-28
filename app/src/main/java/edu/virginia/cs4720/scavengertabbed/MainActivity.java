package edu.virginia.cs4720.scavengertabbed;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

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
    CheckBox locationCheckBox;

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

    ArrayList<Event> myEvents;

    LocationManager locationManager;
    LocationListener locationListener;

    public ArrayList<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(ArrayList<Event> myEvents) {
        this.myEvents = myEvents;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        events = new ArrayList<>(Event.findWithQuery(Event.class, "SELECT * from Event"));

        if (events.isEmpty()) {
            Event one = new Event("Info Session", "Capital One Info Session w/ bagels", "12:00", "09/14/2015", current);
            one.setMine(true);
            Event two = new Event("Info Session", "Capital One Info Session w/ bagels", "12:00", "09/14/2015", current);
            Event three = new Event("Tech Talk", "Microsoft Tech Talk w/ pizza", "7:00", "09/17/2015", current);
            Event four = new Event("Meet and Greet", "Free bags and pizza", "6:00", "09/20/2015", current);

            one.save();
            two.save();
            three.save();
            four.save();

            events = new ArrayList<>(Event.findWithQuery(Event.class, "SELECT * from Event"));
        }

        myEvents = new ArrayList<>(Event.findWithQuery(Event.class, "SELECT * from Event WHERE mine = ?", "1"));


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
    }

    public void onClickCurrentLocation(View view) {
        locationCheckBox = (CheckBox) findViewById(R.id.locationCheckBox);
        latitudeEditText = (EditText) findViewById(R.id.latitudeEditText);
        longitudeEditText = (EditText) findViewById(R.id.longitudeEditText);
        if (locationCheckBox.isChecked()) {
            latitudeEditText.setText(latitude.toString());
            longitudeEditText.setText(longitude.toString());
            latitudeEditText.setEnabled(false);
            longitudeEditText.setEnabled(false);
        }
        else {
            latitudeEditText.setText("");
            longitudeEditText.setText("");
            latitudeEditText.setEnabled(true);
            longitudeEditText.setEnabled(true);

        }

    }

    public void onClickDate(View view) {
        DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, R.style.DialogTheme, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    public void onClickTime(View view) {
        final EditText timeEditText = (EditText) findViewById(R.id.timeEditText);
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(MainActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String helper = ":";
                if (selectedMinute < 10) {
                    helper = helper + "0";
                }
                timeEditText.setText( selectedHour + helper + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy";
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
