package edu.gatech.nutrack;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import edu.gatech.nutrack.graph.GraphViewData;

import android.graphics.Color;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.AdapterView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import android.content.SharedPreferences;
import android.widget.TextView;

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
        
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { 
        	    if (i==0){
        	            // Today's summary
        	    	layout.removeAllViews();
        	    	GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	        	      new GraphViewData(1, 2.0d)
        	        	      , new GraphViewData(2, 1.5d)
        	        	      , new GraphViewData(3, 2.5d)
        	        	      , new GraphViewData(4, 1.0d)
        	        	});
        	        	 
        	        	GraphView graphView = new LineGraphView(
        	        	      c // context
        	        	      , "GraphViewDemo" // heading
        	        	);
        	        	graphView.addSeries(exampleSeries); // data
        	        	graphView.getGraphViewStyle().setGridColor(Color.WHITE);
        	        	graphView.setScrollable(true);
        	        	layout.addView(graphView);
        	    }
        	    else if(i==1) {
    	            // Week's summary 
        	    	layout.removeAllViews();
        	    	GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	    			new GraphViewData(1, 1.0d)
      	        	      , new GraphViewData(2, 0.5d)
      	        	      , new GraphViewData(3, 1.0d)
      	        	      , new GraphViewData(4, 2.0d),
      	        	        new GraphViewData(5, 2.5d)
      	        	      , new GraphViewData(6, 3.0d)
      	        	      , new GraphViewData(7, 3.25d)
      	        	      , new GraphViewData(8, 2.25d),
      	        	        new GraphViewData(9, 2.5d)
      	        	      , new GraphViewData(10, 2.75d)
      	        	      , new GraphViewData(11, 2.15d)
      	        	      , new GraphViewData(12, 2.10d),
      	        	        new GraphViewData(13, 2.22d)
      	        	      , new GraphViewData(14, 3.20d)
      	        	      , new GraphViewData(15, 3.50d)
      	        	      , new GraphViewData(16, 3.75d),
      	        	        new GraphViewData(17, 4.00d)
      	        	      , new GraphViewData(18, 2.00d)
      	        	      , new GraphViewData(19, 2.50d)
      	        	      , new GraphViewData(20, 1.00d),
      	        	        new GraphViewData(21, 1.50d)
      	        	      , new GraphViewData(22, 2.00d)
      	        	      , new GraphViewData(23, 2.50d)
      	        	      , new GraphViewData(24, 0.50d)
        	        	});  
        	        	 
        	        	GraphView graphView = new LineGraphView(
        	        	      c // context
        	        	      , "GraphViewDemo" // heading
        	        	);
        	        	graphView.addSeries(exampleSeries); // data
        	        	graphView.getGraphViewStyle().setGridColor(Color.WHITE);
        	        	graphView.setViewPort(2, 40);
        	        	graphView.setScrollable(true);
						graphView.setScalable(true);
						
        	        	layout.addView(graphView);
        	    }
        	    else if(i==2) {
    	            // Month's summary
        	    	layout.removeAllViews();
        	    	GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	        	      new GraphViewData(1, 1.0d)
        	        	      , new GraphViewData(2, 1.0d)
        	        	      , new GraphViewData(3, 1.0d)
        	        	      , new GraphViewData(4, 1.0d)
        	        	});
        	        	 
        	        	GraphView graphView = new LineGraphView(
        	        	      c // context
        	        	      , "GraphViewDemo" // heading
        	        	);
        	        	graphView.addSeries(exampleSeries); // data
        	        	graphView.getGraphViewStyle().setGridColor(Color.WHITE);
        	        	graphView.setScrollable(true);
        	        	layout.addView(graphView);
        	    }
        	    }

        	    public void onNothingSelected(AdapterView<?> adapterView) {
        	        return;
        	    }         	
		});
        
	}

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
