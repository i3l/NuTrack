package edu.gatech.nutrack;


import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import edu.gatech.nutrack.database.NutritionDataSource;
import edu.gatech.nutrack.graph.GraphViewData;
import edu.gatech.nutrack.model.Nutrition;

public class Track extends Activity {
	private String array_spinner[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);

		SharedPreferences settings = this.getSharedPreferences("Users",Activity.MODE_WORLD_READABLE);
		String username = settings.getString("username", "User");
		
		TextView wordToGreetUser = (TextView) findViewById(R.id.Hello);
		wordToGreetUser.setText("Hello "+username);
		
		array_spinner=new String[3];
        array_spinner[0]="View Today's Summary";
        array_spinner[1]="View Thes Week's Summary";
        array_spinner[2]="View This Month's Summary";
        
        Spinner s = (Spinner) findViewById(R.id.viewBy);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, array_spinner);
        s.setAdapter(adapter);
        final Context c = this;
        final LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
        final Calendar cal = Calendar.getInstance();
        
        //populateDummyData(c, cal, username);
        
        
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { 
        	    if (i==0){
        	            // Today's summary
        	    	layout.removeAllViews();
        	    	GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	        	      new GraphViewData(1, 0.0d)
        	        	      , new GraphViewData(2, 0.0d)
        	        	      , new GraphViewData(3, 0.0d)
        	        	      , new GraphViewData(4, 0.0d)
        	        	      , new GraphViewData(5, 0.0d)
        	        	      , new GraphViewData(6, 0.0d)
        	        	      , new GraphViewData(7, 0.0d)
        	        	      , new GraphViewData(8, 100.5d)
        	        	      , new GraphViewData(9, 0.0d)
        	        	      , new GraphViewData(10, 0.0d)
        	        	      , new GraphViewData(11, 80.5d)
        	        	      , new GraphViewData(12, 0.0d)
        	        	      , new GraphViewData(13, 101.0d)
        	        	      , new GraphViewData(14, 0.0d)
        	        	      , new GraphViewData(15, 0.0d)
        	        	      , new GraphViewData(16, 0.0d)
        	        	      , new GraphViewData(17, 100.5d)
        	        	      /*, new GraphViewData(18, 0.0d)
        	        	      , new GraphViewData(19, 0.0d)
        	        	      , new GraphViewData(20, 0.0d)
        	        	      , new GraphViewData(21, 0.0d)
        	        	      , new GraphViewData(22, 0.0d)
        	        	      , new GraphViewData(23, 0.0d)
        	        	      , new GraphViewData(24, 0.0d)*/
        	        	});
        	        	 
        	        	GraphView graphView = new LineGraphView(
        	        	      c // context
        	        	      , "GraphViewDemo" // heading
        	        	);
        	        	graphView.addSeries(exampleSeries); // data
        	        	graphView.getGraphViewStyle().setGridColor(Color.WHITE);
        	        	graphView.setScrollable(true);
        	        	graphView.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
								, "11", "12", "1", "2", "3", "4", "5"});
        	           	layout.addView(graphView);
        	    }
        	    else if(i==1) {
    	            // Week's summary 
        	    	layout.removeAllViews();
        	    	NutritionDataSource nds = new NutritionDataSource(c);
        	    	int mYear = cal.get(Calendar.YEAR);
        		    int mMonth = cal.get(Calendar.MONTH);
        		    int mDay = cal.get(Calendar.DAY_OF_MONTH);
        		    int[] daysInWeek = new int[7];
        		    //int yday=Calendar.DATE-1;
        		    int todaysMon = mMonth;
        		    int todayYear = mYear;
        		    int[] calInWeek = new int[7];
        		    String dateToBeSearched;
        		    ArrayList<Nutrition> narr = new ArrayList<Nutrition>();
        		    
        		    for(int dayInWeek=0; dayInWeek<7; dayInWeek++){
        	    		daysInWeek[dayInWeek]=Calendar.DATE-dayInWeek;
        	    		if(dayInWeek > 0 && daysInWeek[dayInWeek] < daysInWeek[dayInWeek-1]){
        	    			int oldTM = todaysMon;
        	    			todaysMon = Calendar.MONTH-1;
        	    			if(oldTM < todaysMon){
        	    				todayYear = Calendar.YEAR - 1;
        	    			}
        	    		}
        	    		dateToBeSearched = daysInWeek[dayInWeek]+"-"+todaysMon+"-"+todayYear;
        	    		System.out.println(dateToBeSearched);
        	    		/*nds.open();
        	    		narr = nds.getNutritionByDate(dateToBeSearched);
        	    		nds.close();
        	    		*/int todaysCal = 0;
        	    		if(narr != null && narr.size() > 0){
        	    			for(Nutrition n : narr){
        	    				todaysCal = todaysCal+n.getCalories();
        	    			}
        	    		} 
        	    		calInWeek[dayInWeek] = todaysCal;
        	    	}
        	    	
        	    	
        	    	
        	    	/*GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	    			new GraphViewData(daysInWeek[0], (double)calInWeek[0])
      	        	      , new GraphViewData(daysInWeek[1], (double)calInWeek[1])
      	        	      , new GraphViewData(daysInWeek[2], (double)calInWeek[2])
      	        	      , new GraphViewData(daysInWeek[3], (double)calInWeek[3]),
      	        	        new GraphViewData(daysInWeek[4], (double)calInWeek[4])
      	        	      , new GraphViewData(daysInWeek[5], (double)calInWeek[5])
      	        	      , new GraphViewData(daysInWeek[6], (double)calInWeek[6])
        	        	});*/  
        		    	
        		    	GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        		    			new GraphViewData(1, 200.00d)
        		    			, new GraphViewData(2, 460.00d)
        		    			, new GraphViewData(3, 470.5d)
        		    			, new GraphViewData(4, 210.20d)
        		    			, new GraphViewData(5, 235.00d)
        		    			, new GraphViewData(6, 190.00d)
        		    			, new GraphViewData(7, 301.50d)
        	        	});
        		    
        	        	GraphView graphView = new LineGraphView(
        	        	      c // context
        	        	      , "GraphViewDemo" // heading
        	        	);
        	        	graphView.addSeries(exampleSeries); // data
        	        	graphView.getGraphViewStyle().setGridColor(Color.WHITE);
        	        	//graphView.setViewPort(2, 40);
        	        	graphView.setScrollable(true);
						graphView.setScalable(true);
						graphView.setHorizontalLabels(new String[] {"Fr", "Sa", "Su", "Mo", "Tu", "We", "Th"});
        	        	layout.addView(graphView);
        	    }
        	    else if(i==2) {
    	            // Month's summary
        	    	layout.removeAllViews();
        	    	NutritionDataSource nds = new NutritionDataSource(c);
        	    	int mYear = cal.get(Calendar.YEAR);
        		    int mMonth = cal.get(Calendar.MONTH);
        		    int mDay = cal.get(Calendar.DAY_OF_MONTH);
        		    int[] daysInMonth = new int[30];
        		    //int yday=Calendar.DATE-1;
        		    int todaysMon = mMonth;
        		    int todayYear = mYear;
        	    	for(int dayInMon=0; dayInMon<30; dayInMon++){
        	    		daysInMonth[dayInMon]=Calendar.DATE-dayInMon;
        	    		if(dayInMon > 0 && daysInMonth[dayInMon] < daysInMonth[dayInMon-1]){
        	    			int oldTM = todaysMon;
        	    			todaysMon = Calendar.MONTH-1;
        	    			if(oldTM < todaysMon){
        	    				todayYear = Calendar.YEAR - 1;
        	    			}
        	    		}
        	    		
        	    	}
        	    	GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	    			new GraphViewData(1, 125.0d)
      	        	      , new GraphViewData(2, 100.5d)
      	        	      , new GraphViewData(3, 145.0d)
      	        	      , new GraphViewData(4, 200.0d),
      	        	        new GraphViewData(5, 210.5d)
      	        	      , new GraphViewData(6, 215.0d)
      	        	      , new GraphViewData(7, 192.25d)
      	        	      , new GraphViewData(8, 225.25d),
      	        	        new GraphViewData(9, 228.5d)
      	        	      , new GraphViewData(10, 275.75d)
      	        	      , new GraphViewData(11, 250.15d)
      	        	      , new GraphViewData(12, 245.10d),
      	        	        new GraphViewData(13, 243.22d)
      	        	      , new GraphViewData(14, 253.20d)
      	        	      , new GraphViewData(15, 265.50d)
      	        	      , new GraphViewData(16, 302.75d),
      	        	        new GraphViewData(17, 256.00d)
      	        	      , new GraphViewData(18, 282.00d)
      	        	      , new GraphViewData(19, 275.50d)
      	        	      , new GraphViewData(20, 198.00d),
      	        	        new GraphViewData(21, 192.50d)
      	        	      , new GraphViewData(22, 205.00d)
      	        	      , new GraphViewData(23, 211.50d)
      	        	      , new GraphViewData(24, 301.50d)
        	    			/*, new GraphViewData(25, 220.50d)
        	    			, new GraphViewData(daysInMonth[25], 234.50d)
        	    			, new GraphViewData(daysInMonth[26], 192.50d)
        	    			, new GraphViewData(daysInMonth[27], 187.50d)
        	    			, new GraphViewData(daysInMonth[28], 198.50d)
        	    			, new GraphViewData(daysInMonth[29], 199.50d)*/
        	        	});  
        	        	 
        	        	GraphView graphView = new LineGraphView(
        	        	      c // context
        	        	      , "GraphViewDemo" // heading
        	        	);
        	        	graphView.addSeries(exampleSeries); // data
        	        	graphView.getGraphViewStyle().setGridColor(Color.WHITE);
        	        	//graphView.setViewPort(2, 40);
        	        	graphView.setScrollable(true);
						graphView.setScalable(true);
						graphView.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
								, "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"});
        	        	layout.addView(graphView);
        	    }
        	    }

        	    public void onNothingSelected(AdapterView<?> adapterView) {
        	        return;
        	    }         	
		});
        
	}

	/*private void populateDummyData(Context context, Calendar cal, String username) {
		// TODO Auto-generated method stub
		NutritionDataSource nds = new NutritionDataSource(context);
		
	    int mYear = cal.get(Calendar.YEAR);
	    int mMonth = cal.get(Calendar.MONTH);
	    int mDay = cal.get(Calendar.DAY_OF_MONTH);
	    String today = mDay+"-"+mMonth+"-"+mYear;
		Nutrition n = new Nutrition("Chocolate Cake",220, 95, 0, 0, 10, 20, 30, 25, 48, 125, "A single piece","1111",username,today);
		nds.open();
		nds.addNutrition(n);
		nds.close();
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.track, menu);
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
	       case R.id.action_reco:
	        	Intent callReco = new Intent(this, Track.class);
	    		System.out.println("here");
	    		startActivity(callReco);
	            return true;
	        case R.id.action_nutrition_info:
	        	Intent callNutritionInfo = new Intent(this, Track.class);
	    		System.out.println("here");
	    		startActivity(callNutritionInfo);
	            return true;
	        case R.id.action_log_out:
	        	callLogout(item.getActionView());
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
		
	}
	
	// log out. Every activity should have this method!
		public void callLogout(View view) {
			Intent callLoginIntent = new Intent(this, Login.class);
			startActivity(callLoginIntent);
		}

}


/*
 * GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	    			new GraphViewData(daysInMonth[0], 125.0d)
      	        	      , new GraphViewData(daysInMonth[1], 100.5d)
      	        	      , new GraphViewData(daysInMonth[2], 145.0d)
      	        	      , new GraphViewData(daysInMonth[3], 200.0d),
      	        	        new GraphViewData(daysInMonth[4], 210.5d)
      	        	      , new GraphViewData(daysInMonth[5], 215.0d)
      	        	      , new GraphViewData(daysInMonth[6], 192.25d)
      	        	      , new GraphViewData(daysInMonth[7], 225.25d),
      	        	        new GraphViewData(daysInMonth[8], 228.5d)
      	        	      , new GraphViewData(daysInMonth[9], 275.75d)
      	        	      , new GraphViewData(daysInMonth[10], 250.15d)
      	        	      , new GraphViewData(daysInMonth[11], 245.10d),
      	        	        new GraphViewData(daysInMonth[12], 243.22d)
      	        	      , new GraphViewData(daysInMonth[13], 253.20d)
      	        	      , new GraphViewData(daysInMonth[14], 265.50d)
      	        	      , new GraphViewData(daysInMonth[15], 302.75d),
      	        	        new GraphViewData(daysInMonth[16], 256.00d)
      	        	      , new GraphViewData(daysInMonth[17], 282.00d)
      	        	      , new GraphViewData(daysInMonth[18], 275.50d)
      	        	      , new GraphViewData(daysInMonth[19], 198.00d),
      	        	        new GraphViewData(daysInMonth[20], 192.50d)
      	        	      , new GraphViewData(daysInMonth[21], 205.00d)
      	        	      , new GraphViewData(daysInMonth[22], 211.50d)
      	        	      , new GraphViewData(daysInMonth[23], 201.50d)
        	    			, new GraphViewData(daysInMonth[24], 220.50d)
        	    			/*, new GraphViewData(daysInMonth[25], 234.50d)
        	    			, new GraphViewData(daysInMonth[26], 192.50d)
        	    			, new GraphViewData(daysInMonth[27], 187.50d)
        	    			, new GraphViewData(daysInMonth[28], 198.50d)
        	    			, new GraphViewData(daysInMonth[29], 199.50d)
        	        	});
  */
 