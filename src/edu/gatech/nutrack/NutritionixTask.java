package edu.gatech.nutrack;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;


public class NutritionixTask extends AsyncTask<String, String, String> {

	private final static String TAG = "***TASK";
	private static String id;
	private static String key;	
	protected AsyncResponse delegate = null;
	
	public NutritionixTask(String id, String key)
	{
		this.id = id;
		this.key = key;
	}
	
	 @Override
	 protected void onPreExecute() {
		 Log.d(TAG, "inside onPreExecute");
	 }
	 
	@Override
	protected String doInBackground(String... params) {
		Log.d(TAG, "inside doInBackground");

		/*
		 https://api.nutritionix.com/v1_1/item?upc=52200004265&appId=c07c871c&appKey=37b48a23511abc7eb3dd5656f089dae0&auto=true
		 */
		String serverUrl = "https://api.nutritionix.com/v1_1/item?upc=%s&appId=%s&appKey=%s&auto=true";
		//serverUrl = String.format(serverUrl, params[0], id, key);
		serverUrl = String.format(serverUrl, "52200004265", id, key);
		
		String result = null;
		
		Log.d(TAG, "server URL: " + serverUrl);
		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response;
	    
		try {
			response = httpclient.execute(new HttpGet(serverUrl));
			StatusLine statusLine = response.getStatusLine();
			
			Log.d(TAG, "received response");
			
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		    	Log.d(TAG, "response OK");
		    	
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        String responseString = out.toString();
		        
			    try{
			    	result = responseString;
			    }
			    catch(Exception e) {
			    	e.printStackTrace();
			    }
			    
		        try {
		        	Log.d(TAG, "created json obj from string");
					
		        	StringBuilder n = new StringBuilder();
		        	JSONObject jobj = new JSONObject(result);
		        	
		        	Log.d(TAG, "Item Name: " + jobj.getString("item_name"));
		        	n.append("Item Name: ");
		        	n.append(formatData(jobj.getString("item_name")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Brand_name: " + jobj.getString("brand_name"));
		        	n.append("Brand name: ");
		        	n.append(formatData(jobj.getString("brand_name")));
		        	n.append("\n");

		        	Log.d(TAG, "Calories: " + jobj.getString("nf_calories"));
		        	n.append("Calories: ");
		        	n.append(formatData(jobj.getString("nf_calories")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Total fat: " + jobj.getString("nf_total_fat"));
		        	n.append("Total fat: ");
		        	n.append(formatData(jobj.getString("nf_total_fat")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Saturated fat: " + jobj.getString("nf_saturated_fat"));
		        	n.append("Saturated fat: ");
		        	n.append(formatData(jobj.getString("nf_saturated_fat")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Trans fat: " + jobj.getString("nf_trans_fatty_acid"));
		        	n.append("Trans fat: ");
		        	n.append(formatData(jobj.getString("nf_trans_fatty_acid")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Protein: " + jobj.getString("nf_protein"));
		        	n.append("Protein: ");
		        	n.append(formatData(jobj.getString("nf_protein")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Total Carbohydrate: " + jobj.getString("nf_total_carbohydrate"));
		        	n.append("Total Carbohydrate: ");
		        	n.append(formatData(jobj.getString("nf_total_carbohydrate")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Dietary Fiber: " + jobj.getString("nf_dietary_fiber"));
		        	n.append("Dietary Fiber: ");
		        	n.append(formatData(jobj.getString("nf_dietary_fiber")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Sugars: " + jobj.getString("nf_sugars"));
		        	n.append("Sugars: ");
		        	n.append(formatData(jobj.getString("nf_sugars")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Sodium: " + jobj.getString("nf_sodium"));
		        	n.append("Sodium: ");
		        	n.append(formatData(jobj.getString("nf_sodium")));
		        	n.append("\n");
		        	
		        	Log.d(TAG, "Cholesterol: " + jobj.getString("nf_cholesterol"));
		        	n.append("Cholesterol: ");
		        	n.append(formatData(jobj.getString("nf_cholesterol")));
		        	n.append("\n");
		        	
		        	result = n.toString();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
		    } else{
		        response.getEntity().getContent().close();
		    	Log.d(TAG, "result has error");
		    	result = "Cannot retrieve nutrition data for this product.";
		        throw new IOException(statusLine.getReasonPhrase());
		    }
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.d(TAG, "result: " + result);
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		Log.d(TAG, "inside onPostExecute");
		delegate.getAsyncResponse(result);
    }
	
	private String getData(JSONObject obj, String key) {
		String s = "N/A";
		try {
			s = obj.getString(key);
			if(s.equals("null"))
				return "N/A";
			else
				s = "N/A";
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	private String formatData(String s) {
		if(s.equals("null"))
			return "N/A";
		return s;
	}
}
