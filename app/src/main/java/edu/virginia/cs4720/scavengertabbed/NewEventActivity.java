package edu.virginia.cs4720.scavengertabbed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NewEventActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private TextView longitudeTextView;
    private TextView latitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        Intent in = getIntent();
        Bundle b = in.getExtras();

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        longitudeTextView = (TextView) findViewById(R.id.longitudeTextView);
        latitudeTextView = (TextView) findViewById(R.id.latitudeTextView);

        if (b.get("title") != null) {
            nameTextView.setText("Title: " + b.get("title"));
        }
        if (b.get("description") != null) {
            descriptionTextView.setText("Description: " + b.get("description"));
        }
        if (b.get("date") != null) {
            dateTextView.setText("Date: " + b.get("date"));
        }
        if (b.get("time") != null) {
            timeTextView.setText("Time: " + b.get("time"));
        }
        if (b.get("latitude") != null) {
            latitudeTextView.setText("Latitude: " + b.get("latitude"));
        }
        if (b.get("longitude") != null) {
            longitudeTextView.setText("Longitude: " + b.get("longitude"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
