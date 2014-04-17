package edu.gatech.nutrack.model;

import android.content.Context;
import edu.gatech.nutrack.database.UserDataSource;

public class Authenticator {
	private Context context;
	
	public Authenticator(Context context) {
		this.context = context;
	}
	
	public boolean isPasswordValid(String input) {
		UserDataSource uds = new UserDataSource(context);
		uds.open();
		
		uds.close();
	}
}
