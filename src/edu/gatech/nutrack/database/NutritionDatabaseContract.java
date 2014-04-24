package edu.gatech.nutrack.database;

import android.provider.BaseColumns;

public abstract class NutritionDatabaseContract implements BaseColumns
{
    public static final String TABLE_NAME = "nutrition";
    public static final String COLUMN_NAME_FOOD_NAME = "food_name";
    public static final String COLUMN_NAME_CALORIES = "calories";
    public static final String COLUMN_NAME_TOTAL_FAT = "total_fat";
    public static final String COLUMN_NAME_SAT_FAT = "sat_fat";
    public static final String COLUMN_NAME_TRANS_FAT = "trans_fat";
    public static final String COLUMN_NAME_PROTEIN = "protein";
    public static final String COLUMN_NAME_TOTAL_CARB = "total_carb";
    public static final String COLUMN_NAME_DIET_FIBER = "diet_fiber";
    public static final String COLUMN_NAME_SUGAR = "sugar";
    public static final String COLUMN_NAME_SODIUM = "sodium";
    public static final String COLUMN_NAME_CHOLESTEROL = "cholesterol";
    public static final String COLUMN_NAME_SUMMARY = "summary";
    public static final String COLUMN_NAME_UPC = "upc";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_TIME = "time";
    
    private NutritionDatabaseContract()
    {
    	
    }
}