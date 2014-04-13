package edu.gatech.nutrack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Track extends Activity {
	private String array_spinner[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);
		array_spinner=new String[3];
        array_spinner[0]="View Today's Summary";
        array_spinner[1]="View Thes Week's Summary";
        array_spinner[2]="View This Month's Summary";
        
        Spinner s = (Spinner) findViewById(R.id.viewBy);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, array_spinner);
        s.setAdapter(adapter);
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
	        	Intent callScanIntent = new Intent(this, Scan.class);
	    		System.out.println("here");
	    		startActivity(callScanIntent);
	            return true;
	       case R.id.action_reco:
	        	Intent callReco = new Intent(this, Track.class);
	    		System.out.println("here");
	    		startActivity(callReco);
	            return true;
	        case R.id.action_sync:
	        	Intent callSync = new Intent(this, Track.class);
	    		System.out.println("here");
	    		startActivity(callSync);
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
