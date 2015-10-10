package edu.virginia.cs4720.scavengertabbed;

import android.location.Location;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by danielfmace on 9/16/15.
 */
public class Event extends SugarRecord<Event> {

    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private Boolean mine;

    private Date date;

    public Event(String name, String description, String time, String date, Location location, Boolean mine) {
        this.name = name;
        this.description = description;
        String fromDate = date + " " + time;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        java.util.Date dtt = null;
        try {
            dtt = df.parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.date = dtt;
        this.mine = mine;
    }

    public Event() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMine() {
        return mine;
    }

    public void setMine(Boolean mine) {
        this.mine = mine;
    }


    @Override
    public String toString() {
        return this.name + "\n" + this.description + "\n" + this.date + "\n" + this.getLongitude() + " " + this.getLatitude();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

