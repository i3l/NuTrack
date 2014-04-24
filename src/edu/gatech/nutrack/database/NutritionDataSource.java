package edu.gatech.nutrack.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import edu.gatech.nutrack.model.Nutrition;

public class NutritionDataSource {
	private SQLiteDatabase db;
	private NutritionDatabaseHelper dbHelper;
	private String[] columns = {
			NutritionDatabaseContract.COLUMN_NAME_FOOD_NAME,
			NutritionDatabaseContract.COLUMN_NAME_CALORIES,
			NutritionDatabaseContract.COLUMN_NAME_TOTAL_FAT,
			NutritionDatabaseContract.COLUMN_NAME_SAT_FAT,
			NutritionDatabaseContract.COLUMN_NAME_TRANS_FAT,
			NutritionDatabaseContract.COLUMN_NAME_PROTEIN,
			NutritionDatabaseContract.COLUMN_NAME_TOTAL_CARB,
			NutritionDatabaseContract.COLUMN_NAME_DIET_FIBER,
			NutritionDatabaseContract.COLUMN_NAME_SUGAR,
			NutritionDatabaseContract.COLUMN_NAME_SODIUM,
			NutritionDatabaseContract.COLUMN_NAME_CHOLESTEROL,
			NutritionDatabaseContract.COLUMN_NAME_SUMMARY,
			NutritionDatabaseContract.COLUMN_NAME_UPC,
			NutritionDatabaseContract.COLUMN_NAME_USERNAME,
			NutritionDatabaseContract.COLUMN_NAME_TIME};
	
	public NutritionDataSource(Context context)
	{
		dbHelper = new NutritionDatabaseHelper(context);
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
		db.execSQL(NutritionDatabaseHelper.SQL_DROP_NUTRITION_TABLE);
	}

	public void create() {
		db.execSQL(NutritionDatabaseHelper.SQL_CREATE_NUTRITION_TABLE);
	}
	
	public void addNutrition(String foodName, int calories, int totalFat, int satFat,
			int transFat, int protein, int totalCarb, int dietFiber,
			int sugars, int sodium, int cholesterol, String summary, String upc, String user) {
		
		ContentValues row = new ContentValues();
		row.put(columns[0], foodName);
		row.put(columns[1], calories);
		row.put(columns[2], totalFat);
		row.put(columns[3], satFat);
		row.put(columns[4], transFat);
		row.put(columns[5], protein);
		row.put(columns[6], totalCarb);
		row.put(columns[7], dietFiber);
		row.put(columns[8], sugars);
		row.put(columns[9], sodium);
		row.put(columns[10], cholesterol);
		row.put(columns[11], summary);
		row.put(columns[12], upc);
		row.put(columns[13], user);

		db.insert(NutritionDatabaseContract.TABLE_NAME, null, row);
	}
	
	public void addNutrition(Nutrition n) {
		addNutrition(n.getFoodName(),
				n.getCalories(),
				n.getTotalFat(),
				n.getSatFat(),
				n.getTransFat(),
				n.getProtein(),
				n.getTotalCarb(),
				n.getDietFiber(),
				n.getSugars(),
				n.getSodium(),
				n.getCholesterol(),
				n.getSummary(),
				n.getUpc(), 
				n.getUser());
	}
	
	public void deleteNutrition(Nutrition n)
	{
		db.delete(NutritionDatabaseContract.TABLE_NAME, 
				columns[0] + "='" + n.getFoodName() + "' AND " + 
				columns[1] + "=" + n.getCalories() + " AND " + 
				columns[2] + "=" + n.getTotalFat() + " AND " + 
				columns[3] + "=" + n.getSatFat() + " AND " + 
				columns[4] + "=" + n.getTransFat() + " AND " + 
				columns[5] + "=" + n.getProtein() + " AND " + 
				columns[6] + "=" + n.getTotalCarb() + " AND " + 
				columns[7] + "=" + n.getDietFiber() + " AND " + 
				columns[8] + "=" + n.getSugars() + " AND " + 
				columns[9] + "=" + n.getSodium() + " AND " + 
				columns[10] + "=" + n.getCholesterol() + " AND " + 
				columns[11] + "='" + n.getSummary() + "' AND " +
				columns[12] + "='" + n.getUpc() + "' AND " + 
				columns[13] + "='" + n.getUser() + "' AND " + 
				columns[14] + "='" + n.getTime() + "'",
				null);
	}
	
	public Nutrition getNutrition(String foodName, int calories, int totalFat, int satFat,
			int transFat, int protein, int totalCarb, int dietFiber,
			int sugars, int sodium, int cholesterol, String summary, String upc,
			String user, String time) {
		
		Nutrition n = null;
		
		Cursor cur = db.query(NutritionDatabaseContract.TABLE_NAME, 
				columns,
				columns[0] + "='" + foodName + "' AND " + 
				columns[1] + "=" + calories + " AND " + 
				columns[2] + "=" + totalFat + " AND " + 
				columns[3] + "=" + satFat + " AND " + 
				columns[4] + "=" + transFat + " AND " + 
				columns[5] + "=" + protein + " AND " + 
				columns[6] + "=" + totalCarb + " AND " + 
				columns[7] + "=" + dietFiber + " AND " + 
				columns[8] + "=" + sugars + " AND " + 
				columns[9] + "=" + sodium + " AND " + 
				columns[10] + "=" + cholesterol + " AND " + 
				columns[11] + "='" + summary + "' AND " +
				columns[12] + "='" + upc + "' AND " +  
				columns[13] + "='" + user + "' AND " + 
				columns[14] + "='" + time + "'",
				null, null, null, null);
		
		if(cur != null && cur.getCount() > 0)
		{
			cur.moveToFirst();
			n = cursorToNutrition(cur);	
		}
		
		cur.close();
		return n;
	}
	
	public Nutrition getNutritionByUpc(String upc) {
		
		Nutrition n = null;
		
		Cursor cur = db.query(NutritionDatabaseContract.TABLE_NAME, 
				columns,
				columns[12] + "='" + upc + "'",
				null, null, null, null);
		
		if(cur != null && cur.getCount() > 0)
		{
			cur.moveToFirst();
			n = cursorToNutrition(cur);	
		}
		
		cur.close();
		return n;
	}
	
	public List<Nutrition> getAllNutritions()
	{
		List<Nutrition> list = null;
		
		Cursor cur = db.query(NutritionDatabaseContract.TABLE_NAME, 
				columns, null, null, null, null, null);
		
		if(cur != null && cur.getCount() > 0)
		{
			list = new ArrayList<Nutrition>();
			cur.moveToFirst();
			while(!cur.isAfterLast())
			{
				list.add(cursorToNutrition(cur));
				cur.moveToNext();
			}
		}
		
		cur.close();
		return list;
	}
	
	public Nutrition cursorToNutrition(Cursor cur)  
	{
		return new Nutrition(cur.getString(0), 
				cur.getInt(1),
				cur.getInt(2),
				cur.getInt(3),
				cur.getInt(4),
				cur.getInt(5),
				cur.getInt(6),
				cur.getInt(7),
				cur.getInt(8),
				cur.getInt(9),
				cur.getInt(10),
				cur.getString(11),
				cur.getString(12),
				cur.getString(13),
				cur.getString(14));
	}
}
