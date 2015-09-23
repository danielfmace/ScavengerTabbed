package edu.virginia.cs4720.scavengertabbed;

import java.sql.Time;
import java.util.Date;

/**
 * Created by danielfmace on 9/23/15.
 */
public class Comment {

    private Date date;
    private Time time;
    private String description;
    private String author;


    public Comment(Date date, Time time, String description, String author) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.author = author;
    }
}
