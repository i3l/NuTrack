package edu.gatech.nutrack.database;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.nutrack.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDataSource 
{
	private SQLiteDatabase db;
	private UserDatabaseHelper dbHelper;
	private String[] columns = {UserDatabaseContract.COLUMN_NAME_USERNAME,
			UserDatabaseContract.COLUMN_NAME_PASSWORD,
			UserDatabaseContract.COLUMN_NAME_EMAIL,
			UserDatabaseContract.COLUMN_NAME_TYPE};
	
	public UserDataSource(Context context)
	{
		dbHelper = new UserDatabaseHelper(context);
	}
	
	public void open() throws SQLException
	{
		db = dbHelper.getWritableDatabase();
	}
	
	public void close() 
	{
		dbHelper.close();
	}

	public void drop() {
		db.execSQL(UserDatabaseHelper.SQL_DROP_USER_TABLE);
	}

	public void create() {
		db.execSQL(UserDatabaseHelper.SQL_CREATE_USER_TABLE);
	}
	
	public void addUser(String username, String password, String email, int type) 
	{
		ContentValues row = new ContentValues();
		row.put(columns[0], username);
		row.put(columns[1], password);
		row.put(columns[2], email);
		row.put(columns[3], type);
		db.insert(UserDatabaseContract.TABLE_NAME, null, row);
	}
	
	public void addUser(User u) {
		addUser(u.getUsername(), u.getPassword(), u.getEmail(), u.getType());
	}
	
	public void deleteUser(User u)
	{
		db.delete(UserDatabaseContract.TABLE_NAME, 
				UserDatabaseContract.COLUMN_NAME_USERNAME + "='" + u.getUsername() + "' AND " + 
				UserDatabaseContract.COLUMN_NAME_PASSWORD + "='" + u.getPassword() + "' AND " + 
				UserDatabaseContract.COLUMN_NAME_EMAIL + "='" + u.getEmail() + "' AND " + 
				UserDatabaseContract.COLUMN_NAME_TYPE + "=" + u.getType(),
				null);
	}
	
	public User getUser(String username, String password, String email, int type) 
	{
		User u = null;
		
		Cursor cur = db.query(UserDatabaseContract.TABLE_NAME, 
				columns,
				UserDatabaseContract.COLUMN_NAME_USERNAME + "='" + username + "' AND " + 
				UserDatabaseContract.COLUMN_NAME_PASSWORD + "='" + password + "' AND " + 
				UserDatabaseContract.COLUMN_NAME_EMAIL + "='" + email + "' AND " + 
				UserDatabaseContract.COLUMN_NAME_TYPE + "=" + type, 
				null, null, null, null);
		
		if(cur != null && cur.getCount() > 0)
		{
			cur.moveToFirst();
			u = cursorToUser(cur);	
		}
		
		cur.close();
		return u;
	}
	
	public List<User> getAllUsers()
	{
		List<User> list = null;
		
		Cursor cur = db.query(UserDatabaseContract.TABLE_NAME, 
				columns, null, null, null, null, null);
		
		if(cur != null && cur.getCount() > 0)
		{
			list = new ArrayList<User>();
			cur.moveToFirst();
			while(!cur.isAfterLast())
			{
				list.add(cursorToUser(cur));
				cur.moveToNext();
			}
		}
		
		cur.close();
		return list;
	}
	
	public User cursorToUser(Cursor cur)  
	{
		return new User(cur.getString(0), 
				cur.getString(1),
				cur.getString(2),
				cur.getInt(3));
	}
}
