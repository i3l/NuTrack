package edu.gatech.nutrack;

import edu.gatech.nutrack.database.UserDataSource;
import edu.gatech.nutrack.model.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SignUp extends Activity {
	private static final String TAG = "***SIGNUP";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	public void callSignUp(View view) {
		TextView un = (TextView) findViewById(R.id.userNameText);
		TextView pwd = (TextView) findViewById(R.id.passwordText);
		TextView pwdconf = (TextView) findViewById(R.id.passwordConfText);
		TextView email = (TextView) findViewById(R.id.emailText);
		
		if(pwd.getText().toString().equals(pwdconf.getText().toString())) {
			User u = new User(un.getText().toString(),
					pwd.getText().toString(),
					email.getText().toString(),
					0);  // change user type by radio button
			UserDataSource uds = new UserDataSource(this);
			uds.open();
			uds.addUser(u);
			uds.close();
			
			Intent callSignUpIntent = new Intent(this, Home.class);
			startActivity(callSignUpIntent);
		} else {
			Log.d(TAG, "cannot sign up because password confirmation failed");
			finish();
		}
	}
}
