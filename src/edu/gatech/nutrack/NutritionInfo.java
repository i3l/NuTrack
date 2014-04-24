package edu.gatech.nutrack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import edu.gatech.nutrack.database.NutritionDataSource;
import edu.gatech.nutrack.model.Group;
import edu.gatech.nutrack.model.MyExpandableListAdapter;
import edu.gatech.nutrack.model.Nutrition;

public class NutritionInfo extends Activity {
	SparseArray<Group> groups = new SparseArray<Group>();
	private ListView listView;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutrition_info);
		createData();
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView1);
	    MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
	        groups);
	    listView.setAdapter(adapter);
		/*context = this;
		listView = (ListView)findViewById(R.id.listView1);
		List<NutritionData> dateList = new ArrayList<NutritionData>();
		Nutrition food_1 = new Nutrition();
		food_1.setFoodName("Food 1");
		Nutrition food_2 = new Nutrition();
		food_1.setFoodName("Food 2");
		Nutrition food_3 = new Nutrition();
		food_1.setFoodName("Food 3");
		List<Nutrition> nutrition_list_1 = new ArrayList<Nutrition>();
		nutrition_list_1.add(food_1);
		nutrition_list_1.add(food_2);
		nutrition_list_1.add(food_3);
		NutritionData nutrition_1 = new NutritionData();
		nutrition_1.date = "3/17/2014";
		nutrition_1.nutrition = nutrition_list_1;
		List<String> dateList = new ArrayList<String>();
		dateList.add("4/2/2014");
		dateList.add("4/9/2014");
		dateList.add("4/14/2014");
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (
				this,
				android.R.layout.simple_list_item_1,
				dateList);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				switch(position) {
				case 0:
					Intent intent = new Intent();
				}
				
			}
			
		});*/
	}
	
	private void createData() {
		List<Nutrition> nutritionList = new ArrayList<Nutrition>();
		Nutrition lowfatmilk = new Nutrition();
		lowfatmilk.setFoodName("Milk, lowfat, 1% milkfat");
		lowfatmilk.setCalories(102.48);
		lowfatmilk.setTotalFat(2.3668);
		lowfatmilk.setSatFat(1.5445);
		lowfatmilk.setProtein(8.2228);
		lowfatmilk.setSugars(12.688);
		lowfatmilk.setSodium(107.36);
		lowfatmilk.setCholesterol(12.2);

		Nutrition croiss = new Nutrition();
		croiss.setFoodName("Croissant, with egg, cheese, and sausage");
		croiss.setCalories(523.2);
		croiss.setTotalFat(38.16);
		croiss.setSatFat(18.2256);
		croiss.setProtein(20.304);
		croiss.setSugars(24.72);
		croiss.setSodium(1115.2);
		croiss.setCholesterol(216);

		Nutrition oj = new Nutrition();
		oj.setFoodName("Orange juice, raw");
		oj.setCalories(111.6);
		oj.setTotalFat(0.496);
		oj.setSatFat(0.0595);
		oj.setProtein(1.736);
		oj.setSugars(20.832);
		oj.setSodium(2.48);
		oj.setCholesterol(0);

		Nutrition clifbar = new Nutrition();
		clifbar.setFoodName("CLIF BAR, mixed flavors");
		clifbar.setCalories(235.28);
		clifbar.setTotalFat(3.9984);
		clifbar.setSatFat(1.0003);
		clifbar.setProtein(10.0028);
		clifbar.setSugars(21.5016);
		clifbar.setSodium(132.6);
		clifbar.setCholesterol(0);

		Nutrition silkchocolate = new Nutrition();
		silkchocolate.setFoodName("SILK Chocolate, soymilk");
		silkchocolate.setCalories(140.94);
		silkchocolate.setTotalFat(3.4992);
		silkchocolate.setSatFat(0.5006);
		silkchocolate.setProtein(5.0058);
		silkchocolate.setSugars(19.0026);
		silkchocolate.setSodium(99.63);
		silkchocolate.setCholesterol(0);

		nutritionList.add(lowfatmilk);
		nutritionList.add(croiss);
		nutritionList.add(oj);
		nutritionList.add(clifbar);
		nutritionList.add(silkchocolate);
		
	    for (int i = 0; i < 5; i++) {
	        Group group = new Group(nutritionList.get(i).getFoodName());
	        group.children.add("Calories: " + String.valueOf(nutritionList.get(i).getCalories()) + " cal");
	        group.children.add("Total Fat: " + String.valueOf(nutritionList.get(i).getTotalFat()) + " g");
	        group.children.add("SatFat: " + String.valueOf(nutritionList.get(i).getSatFat()) + " g");
	        group.children.add("Protein: " + String.valueOf(nutritionList.get(i).getProtein()) + " g");
	        group.children.add("Sugars: " + String.valueOf(nutritionList.get(i).getSugars()) + " g");
	        group.children.add("Sodium: " + String.valueOf(nutritionList.get(i).getSodium()) + " mg");
	        group.children.add("Cholesterol: " + String.valueOf(nutritionList.get(i).getCholesterol()) + " mg");
	        groups.append(i, group);
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reco, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
    	case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
        case R.id.action_scan:
        	Intent callScanIntent = new Intent(this, NutritionixActivity.class);
    		System.out.println("here");
    		startActivity(callScanIntent);
            return true;
        case R.id.action_track:
        	Intent callTrackIntent = new Intent(this, Track.class);
    		System.out.println("here");
    		startActivity(callTrackIntent);
            return true;
        case R.id.action_reco:
        	Intent callReco = new Intent(this, Track.class);
    		System.out.println("here");
    		startActivity(callReco);
            return true;
        default:
            return super.onOptionsItemSelected(item);
	    }
		
	}

}
