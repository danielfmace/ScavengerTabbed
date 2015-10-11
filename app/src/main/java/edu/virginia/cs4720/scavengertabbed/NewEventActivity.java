package edu.virginia.cs4720.scavengertabbed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewEventActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private TextView longitudeTextView;
    private TextView latitudeTextView;
    private Event event;
    private ImageView imageView;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        Intent in = getIntent();
        Bundle b = in.getExtras();

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.commentTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        longitudeTextView = (TextView) findViewById(R.id.longitudeTextView);
        latitudeTextView = (TextView) findViewById(R.id.latitudeTextView);
        imageView = (ImageView) findViewById(R.id.imageViewNE);


        Double latitude = Double.parseDouble((String) b.get("latitude"));
        Double longitude = Double.parseDouble((String) b.get("longitude"));
        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        String name = (String) b.get("title");
        String description = (String) b.get("description");
        String time = (String) b.get("time");
        String date = (String) b.get("date");



        Bitmap bmp = b.getParcelable("imageBitmap");
        if (b.getParcelable("imageBitmap") != null) {

            Drawable blankDraw = new BitmapDrawable(getResources(), bmp);
            //Bitmap blankBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blankimage);

            if(blankDraw.getConstantState().equals(ContextCompat.getDrawable(getApplicationContext(), R.drawable.blankimage).getConstantState())) {
                String blankImagePath = "blankImage";
                imageView.setImageResource(R.drawable.blankimage);
                event = new Event(name, description, time, date, location, blankImagePath, true);
            }
            else {
                imageView.setImageBitmap(bmp);
                fileName = name; //image name will be the name of the event

                File file = new File(getFilesDir(), fileName);
                try {
                    file.createNewFile();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 0, baos);
                    byte [] bytes = baos.toByteArray();
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String filePath = file.getAbsolutePath();

                event = new Event(name, description, time, date, location, filePath, true);
            }
        }
        else {
            String blankImagePath = "blankImage";
            imageView.setImageResource(R.drawable.blankimage);
            event = new Event(name, description, time, date, location, blankImagePath, true);
        }

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

    public void onClickSave(View view) {
        event.save();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
