package com.napster.nitrourkelastudentportal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;  
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class MainActivity extends ActionBarActivity {
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels/dm.xdpi,2);
        double y = Math.pow(dm.heightPixels/dm.ydpi,2);
        final double screenInches = Math.sqrt(x+y);*/
        
        button1=(Button)findViewById(R.id.button1);
        
        button1.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Connecting...Please Wait", Toast.LENGTH_SHORT).show();
				String result = null;
	        	InputStream is = null;
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				EditText username = (EditText)findViewById(R.id.editText1);
				String user = username.getText().toString();
				EditText password = (EditText)findViewById(R.id.editText2);
			    String pass = password.getText().toString();
        	   	nameValuePairs.add(new BasicNameValuePair("f1",user));
        	   	nameValuePairs.add(new BasicNameValuePair("f2",pass));
        	   	
        	   	StrictMode.setThreadPolicy(policy);
				//Toast.makeText(getBaseContext(),"Screen Size is : "+screenInches,Toast.LENGTH_SHORT).show();
        	   	Toast.makeText(getApplicationContext(), "Connecting...Please Wait", Toast.LENGTH_SHORT).show();
        	   	if(isNetworkAvailable())
        	   	{
                	try
                	{
	        	        HttpClient httpclient = new DefaultHttpClient();
	        	        HttpPost httppost = new HttpPost("http://nitrsp.byethost9.com/check.php");
	        	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        	        HttpResponse response = httpclient.execute(httppost); 
	        	        HttpEntity entity = response.getEntity();
	        	        is = entity.getContent();

	        	        Toast.makeText(getApplicationContext(), "Connected...Now Loading ", Toast.LENGTH_SHORT).show();
	        	     //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
                	 }
                     catch(Exception e)
                	 {
	        	         Log.e("log_tag", "Error in http connection "+e.toString());
	        	         Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

                	 }
	        	     //convert response to string
                	 try
                	 {
	        	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        	        StringBuilder sb = new StringBuilder();
	        	        String line = null;
	        	        while ((line = reader.readLine()) != null) 
	        	        {
	        	                sb.append(line + "\n");
	        	              //  Toast.makeText(getApplicationContext(), "Input Reading pass", Toast.LENGTH_SHORT).show();
	        	        }
	        	        is.close();

	        	        result=sb.toString();
                	  }
                	  catch(Exception e)
                	  {
                		  Log.e("log_tag", "Error converting result "+e.toString());
                		  Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();
                	  }

                	 //parse json data
                	  try
                	  {
                		  JSONObject object = new JSONObject(result);
                		  String ch=object.getString("re");
                		  if(ch.equals("success"))
                		  {
      	 	        		  	//long q=object.getLong("f1");
          	                  	String roll= object.getString("roll");
          	                  	String fname= object.getString("fname");
          	                  	String lname= object.getString("lname");
          	                  	String dob= object.getString("dob");
          	                  	String sex= object.getString("sex");
          	                  	String program= object.getString("program");
          	                  	String mno= object.getString("mno");
          	          			String father= object.getString("father");
          	          			String mother= object.getString("mother");
          	          			String dept= object.getString("dept");
          	                  
          	          			Toast.makeText(getApplicationContext(), "Welcome"+roll+" "+fname+" "+lname, Toast.LENGTH_SHORT).show();
          	          			Intent i = new Intent(getApplicationContext(), welcome.class);
          	                	Bundle basket= new Bundle();
          	              		basket.putString("roll", roll);
          	              		basket.putString("fname", fname);
          	              		basket.putString("lname", lname);
          	              		basket.putString("dob", dob);
          	              		basket.putString("sex", sex);
          	              		basket.putString("program", program);
          	            		basket.putString("mno", mno);
          	          			basket.putString("father", father);
          	        			basket.putString("mother", mother);
          	      				basket.putString("dept", dept);
          	              		i.putExtras(basket);
          	              		startActivity(i);
                		  }
                		  else
                		  {  
                			  Toast.makeText(getApplicationContext(), "Incorrect Username or Password !!!", Toast.LENGTH_SHORT).show();
                		  }
                	   }
                	   catch(JSONException e)
                	   {
                		   Log.e("log_tag", "Error parsing data "+e.toString());
                		   Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
                	   }
        	   		}
        	   		else
        	   		{
        	   			Toast.makeText(getBaseContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
        	   		}
			}
		});    
    }
    
    public boolean isNetworkAvailable(){
        HttpGet httpGet = new HttpGet("http://www.google.com");
        HttpParams httpParameters = new BasicHttpParams();
        // Set the timeout in milliseconds until a connection is established.
        // The default value is zero, that means the timeout is not used.
        int timeoutConnection = 3000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        // Set the default socket timeout (SO_TIMEOUT)
        // in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = 5000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
        try{
        	Toast.makeText(getBaseContext(),"Checking network connection...",Toast.LENGTH_SHORT).show();
            httpClient.execute(httpGet);
            Toast.makeText(getBaseContext(),"Connection OK",Toast.LENGTH_SHORT).show();
            return true;
        }
        catch(ClientProtocolException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
