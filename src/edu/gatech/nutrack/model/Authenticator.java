package edu.gatech.nutrack.model;

import android.content.Context;
import edu.gatech.nutrack.database.UserDataSource;

public class Authenticator {
	private Context context;
	
	public Authenticator(Context context) {
		this.context = context;
	}
	
	public boolean isUserValid(User u) {
		UserDataSource uds = new UserDataSource(context);
		uds.open();
		User good = uds.getUser(u.getUsername(), u.getPassword(), u.getEmail(), u.getType());
		uds.close();
		return good == null;
	}
}
