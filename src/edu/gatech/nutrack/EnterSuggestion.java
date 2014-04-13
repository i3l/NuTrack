package edu.gatech.nutrack;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EnterSuggestion extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_suggestion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_suggestion, menu);
		return true;
	}

	public void callViewPatient(View view) {
		Intent callViewPatientIntent = new Intent(this, ViewPatient.class);
		System.out.println("here");
		startActivity(callViewPatientIntent);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.action_home_physician:
	    		Intent callHomeIntent = new Intent(this, HomePhysician.class);
				startActivity(callHomeIntent);
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

	
	private void showAlertDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		// Add the buttons
		builder.setMessage(msg);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
		});

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.setCancelable(false);
		dialog.show();
	}
	
}
