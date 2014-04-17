package edu.gatech.nutrack.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NutritionDatabaseHelper extends SQLiteOpenHelper{
	   public static final int DATABASE_VERSION = 1;
	    public static final String DATABASE_NAME = "Nutrition.db";
	    
	    public static final String SQL_CREATE_NUTRITION_TABLE = 
	    		"CREATE TABLE " +  NutritionDatabaseContract.TABLE_NAME + " (" + 
	    		NutritionDatabaseContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
	    		NutritionDatabaseContract.COLUMN_NAME_FOOD_NAME + " TEXT NOT NULL, " +  
	    		NutritionDatabaseContract.COLUMN_NAME_CALORIES + " INTEGER NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_TOTAL_FAT + " INTEGER NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_SAT_FAT + " INTEGR NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_TRANS_FAT + " INTEGER NOT NULL, " +  
	    		NutritionDatabaseContract.COLUMN_NAME_PROTEIN + " INTEGER NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_TOTAL_CARB + " INTEGER NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_DIET_FIBER + " INTEGER NOT NULL, " +  
	    		NutritionDatabaseContract.COLUMN_NAME_SUGAR + " INTEGER NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_SODIUM + " INTEGER NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_CHOLESTEROL + " INTEGER NOT NULL, " +  
	    		NutritionDatabaseContract.COLUMN_NAME_SUMMARY + " TEXT NOT NULL, " + 
	    		NutritionDatabaseContract.COLUMN_NAME_UPC + " TEXT NOT NULL)";
	    
	    public static final String SQL_DROP_NUTRITION_TABLE = 
	    		 "DROP TABLE IF EXISTS " + NutritionDatabaseContract.TABLE_NAME;
	    
	    public NutritionDatabaseHelper(Context context) 
	    {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	    
	    public void onCreate(SQLiteDatabase db) 
	    {
	        db.execSQL(SQL_CREATE_NUTRITION_TABLE);
	    }
	    
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	    {
	        db.execSQL(SQL_DROP_NUTRITION_TABLE);
	        onCreate(db);
	    }
	    
	    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	    {
	        onUpgrade(db, oldVersion, newVersion);
	    }
}
