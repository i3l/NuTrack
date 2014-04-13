package edu.gatech.nutrack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ViewPatient extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_patient);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_patient, menu);
		return true;
	}
	
	public void callSuggest(View view) {
		Intent callSuggestIntent = new Intent(this, EnterSuggestion.class);
		System.out.println("here");
		startActivity(callSuggestIntent);
	}

}
