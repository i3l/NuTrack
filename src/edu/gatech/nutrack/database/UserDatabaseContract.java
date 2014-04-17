package edu.gatech.nutrack.database;

import android.provider.BaseColumns;

public abstract class UserDatabaseContract implements BaseColumns
{
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_PASSWORD = "password";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_TYPE = "type";
    
    private UserDatabaseContract()
    {
    	
    }
}