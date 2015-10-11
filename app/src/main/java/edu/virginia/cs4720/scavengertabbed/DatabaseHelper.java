package edu.virginia.cs4720.scavengertabbed;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

/**
 * Created by danielfmace on 9/24/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    private static final String LOG = "DatabaseHelper";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "eventsManager";

    // Table Names
    private static final String TABLE_EVENT = "events";
    private static final String TABLE_COMMENT = "comments";
    private static final String TABLE_EVENT_COMMENT = "events_comments";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE_TIME = "date_time";
    private static final String KEY_DESCRIPTION = "description";

    // Events Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_IMAGEPATH = "imagePath";
    private static final String KEY_MINE = "mine";

    // Comments Table = column names
    private static final String KEY_AUTHOR = "author";

    // Events_Comments Table - column names
    private static final String KEY_EVENT_ID = "event_id";
    private static final String KEY_COMMENT_ID = "comment_id";

    // Table Create Statements
    // Event table create statement
    private static final String CREATE_TABLE_EVENT = "CREATE TABLE"
            + TABLE_EVENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_DATE_TIME + " DATETIME,"
            + KEY_LATITUDE + " REAL," + KEY_LONGITUDE + " REAL," + KEY_IMAGEPATH + " TEXT,"
            + KEY_MINE + " BOOLEAN" + ")";

    // Comment table create statement
    private static final String CREATE_TABLE_COMMENT = "CREATE TABLE"
            + TABLE_COMMENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_AUTHOR + " TEXT," + KEY_DATE_TIME + "DATETIME,"
            + KEY_DESCRIPTION + " TEXT" + ")";

    // Event_Comment table create statement
    private static final String CREATE_TABLE_EVENT_COMMENT = "CREATE_TABLE"
            + TABLE_EVENT_COMMENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_EVENT_ID + " INTEGER," + KEY_COMMENT_ID + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_COMMENT);
        db.execSQL(CREATE_TABLE_EVENT_COMMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_COMMENT);

        onCreate(db);
    }

    public long createEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.getName());
        values.put(KEY_DESCRIPTION, event.getDescription());
        // values.put(KEY_DATE_TIME, event.getDate());
        values.put(KEY_LATITUDE, event.getLatitude());
        values.put(KEY_LONGITUDE, event.getLongitude());
        values.put(KEY_IMAGEPATH, event.getImagePath());
        values.put(KEY_MINE, event.getMine());

        long event_id = db.insert(TABLE_EVENT, null, values);
        return event_id;
    }

/*    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event e = new Event();
                e.setName(c.getString((c.getColumnIndex(KEY_NAME))));
                e.setDescription((c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
                e.setDate(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }*/

}
