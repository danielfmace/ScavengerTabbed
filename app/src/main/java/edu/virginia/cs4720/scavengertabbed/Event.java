package edu.virginia.cs4720.scavengertabbed;

import android.location.Location;

import java.util.ArrayList;


/**
 * Created by danielfmace on 9/16/15.
 */
public class Event {

    private String name;
    private String description;
    private String time;
    private String date;
    private Location location;

    private ArrayList<Comment> comments;

    public Event(String name, String description, String time, String date, Location location) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.date = date;
        this.location = location;
        this.comments = new ArrayList<Comment>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.description + "\n" + this.date + " " + this.time + "\n" + this.location.getLongitude() + " " + this.location.getLatitude();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

}

