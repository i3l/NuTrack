package edu.gatech.nutrack;

import edu.gatech.nutrack.database.NutritionDataSource;
import edu.gatech.nutrack.model.Nutrition;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class NutritionixActivity extends Activity implements AsyncResponse{

	private final static String TAG = "***NUTRITIONIX";
	private static final String APP_ID = "c07c871c";
	private static final String APP_KEY = "37b48a23511abc7eb3dd5656f089dae0";
	private static final String WAIT_MSG = "Retrieving nutrition data...";
	private TextView tvData;
	private EditText etFoodName, etBarcode;
	private Button bClear, bSend, bScan;
	private Context ctx;
	private String result, upc;
	private NutritionixTask nt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutritionix);

		ctx = this;
		result = WAIT_MSG;
		upc = "";
		
		/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		*/
		
		etFoodName = (EditText) findViewById(R.id.etFoodName);
		etBarcode = (EditText) findViewById(R.id.etBarcode);
		bClear = (Button) findViewById(R.id.bClear);
		bSend = (Button) findViewById(R.id.bSend);
		bScan = (Button) findViewById(R.id.bScan);
		
		bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d(TAG, "clicked clear button");
        		etFoodName.setText("");
        		etBarcode.setText("");
            }
        });
		
		bSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d(TAG, "clicked send button");
       
        		nt = new NutritionixTask(APP_ID, APP_KEY);
        		nt.delegate = (AsyncResponse) ctx;
        		nt.execute(upc);
       
        		final Dialog dialog = new Dialog(ctx);
    			dialog.setContentView(R.layout.dialog_data);
    			dialog.setTitle("Nutrition Data");
     
    			// set the custom dialog components - text, image and button
    			tvData = (TextView) dialog.findViewById(R.id.tvData);
    			tvData.setText(result);
 
    			Button bClose = (Button) dialog.findViewById(R.id.bClose);
    			bClose.setOnClickListener(new OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					dialog.dismiss();
    					etFoodName.setText("");
    	        		etBarcode.setText("");
    	        		result = WAIT_MSG;
    				}
    			});
     
    			dialog.show();
            }
        });
		
		bScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d(TAG, "clicked scan button");     
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nutritionix, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_nutritionix,
					container, false);
			return rootView;
		}
	}
	*/
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d(TAG, "return from barcode scanning activity with result");

	}
	
	/*
	public void getAsyncResponse(String s) {
		Log.d(TAG, "inside getAsyncResponse");
		result = s;
		tvData.setText(result);
	}
	*/
	
	public void getAsyncResponse(Nutrition n) {
		Log.d(TAG, "inside getAsyncResponse");
		tvData.setText(n.getSummary());
		
		// add nutrition object from async task to db for caching
		NutritionDataSource nds = new NutritionDataSource(ctx);
		nds.open();
		nds.addNutrition(n);
		nds.close();
	}
}
