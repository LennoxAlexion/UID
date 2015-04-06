package aaap.uid_project.Utils;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLitePeopleOfflineHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLitePeopleOfflineHandler.class
			.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "PeopleDetails";

	// Login table name
	private static final String TABLE_NAME = "Offline_Details";

	// Offline People Table Columns names
	private static final String KEY_ADDED_BY_USERNAME = "added_by_username";
	private static final String KEY_ADDED_BY_ROLE = "added_by_role";
	private static final String KEY_UID = "uid";
	private static final String KEY_FIRSTNAME = "first_name";
	private static final String KEY_LASTNAME = "last_name";
	private static final String KEY_BLOODGROUP = "blood_group";
	private static final String KEY_ADDRESS = "address";

	public SQLitePeopleOfflineHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_OFFLINE_DETAILS = "CREATE TABLE " + TABLE_NAME + "("
				+ KEY_UID + " TEXT PRIMARY KEY," + KEY_FIRSTNAME + " TEXT,"
				+ KEY_LASTNAME + " TEXT," + KEY_BLOODGROUP + " TEXT,"
				+ KEY_ADDRESS + " TEXT," + KEY_ADDED_BY_USERNAME + " TEXT,"
				+ KEY_ADDED_BY_ROLE + " TEXT" + ")";
		db.execSQL(CREATE_OFFLINE_DETAILS);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String uid, String first_name, String last_name,
			String blood_group, String address, String added_by_username,
			String added_by_role) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FIRSTNAME, first_name); // firstName
		values.put(KEY_LASTNAME, last_name); // last name
		values.put(KEY_UID, uid); // uid
		values.put(KEY_BLOODGROUP, blood_group); // bloodgroup
		values.put(KEY_ADDRESS, address);// address
		values.put(KEY_ADDED_BY_USERNAME, added_by_username); // uid
		values.put(KEY_ADDED_BY_ROLE, added_by_role); // uid

		// Inserting Row
		long id = db.insert(TABLE_NAME, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New Person details inserted into sqlite: " + id);
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> people = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_NAME;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to last row
		cursor.moveToLast();
		if (cursor.getCount() > 0) {
			people.put(KEY_UID,
					cursor.getString(cursor.getColumnIndex("KEY_UID")));
			people.put(KEY_FIRSTNAME,
					cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
			people.put(KEY_LASTNAME,
					cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
			people.put(KEY_BLOODGROUP,
					cursor.getString(cursor.getColumnIndex(KEY_BLOODGROUP)));
			people.put(KEY_ADDRESS,
					cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));

			people.put(KEY_ADDED_BY_ROLE,
					cursor.getString(cursor.getColumnIndex(KEY_ADDED_BY_ROLE)));
			people.put(KEY_ADDED_BY_USERNAME, cursor.getString(cursor
					.getColumnIndex(KEY_ADDED_BY_USERNAME)));
		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching people from Sqlite: " + people.toString());

		return people;
	}

	/**
	 * Getting user login status return true if rows are there in table
	 * */
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_NAME, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}