package edu.virginia.cs4720.scavengertabbed;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by danielfmace on 9/23/15.
 */
public class Comment extends SugarRecord<Comment> {

    private Date date;
    private String description;
    private String author;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private long eventId;

    public Comment() {}

    public Comment(Date date, String description, String author, long eventId) {
        this.date = date;
        this.description = description;
        this.author = author;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return this.description + "\n" + "Author: " + this.author + "\nDate: " + this.date;
    }
}
