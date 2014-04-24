package edu.gatech.nutrack;

import edu.gatech.nutrack.scan.*;
import edu.gatech.nutrack.database.NutritionDataSource;
import edu.gatech.nutrack.model.Nutrition;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
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
	private IntentIntegrator intentIntegrator;
	
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
		
		intentIntegrator = new IntentIntegrator(this); 
		
		//etFoodName = (EditText) findViewById(R.id.etFoodName);
		etBarcode = (EditText) findViewById(R.id.etBarcode);
		
		bClear = (Button) findViewById(R.id.bClear);
		bSend = (Button) findViewById(R.id.bSend);
		bScan = (Button) findViewById(R.id.bScan);
		
		bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d(TAG, "clicked clear button");
        		//etFoodName.setText("");
        		etBarcode.setText("");
            }
        });
		
		bSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d(TAG, "clicked send button");
       
            	Nutrition nu = getDataFromCache(upc);
            	if(nu != null) {
            		Log.d(TAG, "this upc is in local db");
            		result = nu.getSummary();
            	} else {
            		Log.d(TAG, "this upc is not in local db");
            		nt = new NutritionixTask(APP_ID, APP_KEY);
            		nt.delegate = (AsyncResponse) ctx;
            		nt.execute(upc);
            	}
            
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
            
           @Override
           public void onClick(View v) {
        	   /*
               Intent intent = new Intent("com.google.zxing.client.android.SCAN");
               intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
               startActivityForResult(intent, 0);
               */
        	   
        	   intentIntegrator.initiateScan(IntentIntegrator.ALL_CODE_TYPES);
           }
       });
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
        case R.id.action_nutrition_info:
        	Intent callNutritionInfo = new Intent(this, NutritionInfo.class);
    		System.out.println("here");
    		startActivity(callNutritionInfo);
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
	    if (requestCode == IntentIntegrator.REQUEST_CODE) {
	    	Log.d(TAG, "barcode scanning request code matched: " + requestCode);
	        if (resultCode == RESULT_OK) {
	            upc = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            etBarcode.setText(upc);
	            Log.d(TAG, "barcode result: " + upc + " - format: " + format);
	            // Handle successful scan
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    } else {
	    	Log.d(TAG, "barcode scanning request code mismatched: " + requestCode);
	    }
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
	
	public Nutrition getDataFromCache(String u) {
		NutritionDataSource nds = new NutritionDataSource(ctx);
		nds.open();
		Nutrition n = nds.getNutritionByUpc(u);
		nds.close();
		return n;
	}
}
