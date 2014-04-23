package edu.gatech.nutrack;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import edu.gatech.nutrack.database.NutritionDataSource;
import edu.gatech.nutrack.database.NutritionDatabaseContract;
import edu.gatech.nutrack.model.Nutrition;

public class NutritionInfo extends Activity {

	private ListView listView;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutrition_info);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		listView = (ListView)findViewById(R.id.listView1);
		NutritionDataSource dbSource = new NutritionDataSource(context);
		List<Nutrition> nutritionList = dbSource.getAllNutritions();
		if (0 == nutritionList.size()) {
			TextView emptyTextView = new TextView(context);
			emptyTextView.setText("No food information in the database.");
			listView.addView(emptyTextView);
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_nutrition_info,
					container, false);
			return rootView;
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
