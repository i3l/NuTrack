package edu.gatech.nutrack;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Scan extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.scan:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
	    	case R.id.home:
	    		Intent callHomeIntent = new Intent(this, Home.class);
	    		System.out.println("here");
	    		startActivity(callHomeIntent);
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
