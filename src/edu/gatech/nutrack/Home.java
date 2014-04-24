package edu.gatech.nutrack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	public void callScan(View view) {
		Intent callScanIntent = new Intent(this, NutritionixActivity.class);
		startActivity(callScanIntent);
	}
	
	public void callTrack(View view) {
		Intent callTrackIntent = new Intent(this, Track.class);
		
		startActivity(callTrackIntent);
	}
	public void callSync(View view) {
		Intent callSyncIntent = new Intent(this, Sync.class);
		
		startActivity(callSyncIntent);
	}
	
	public void callReco(View view) {
		Intent callRecoIntent = new Intent(this, Reco.class);
		
		startActivity(callRecoIntent);
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
	    		
	    		startActivity(callScanIntent);
	            return true;
	        case R.id.action_track:
	        	Intent callTrackIntent = new Intent(this, Track.class);
	    		
	    		startActivity(callTrackIntent);
	            return true;
	        case R.id.action_reco:
	        	Intent callReco = new Intent(this, Track.class);
	    		
	    		startActivity(callReco);
	            return true;
	        case R.id.action_nutrition_info:
	        	Intent callNutritionInfo = new Intent(this, Track.class);
	    		
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
