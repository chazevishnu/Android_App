package com.napster.nitrourkelastudentportal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;  
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class registration extends ActionBarActivity {
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        
        Bundle gt=getIntent().getExtras();
		String fname=gt.getString("fname");
		String lname=gt.getString("lname");
		String roll=gt.getString("roll");
		String program=gt.getString("program");
		String dept=gt.getString("dept");

				String result = null;
	        	InputStream is = null;
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				String user = roll;
        	   	nameValuePairs.add(new BasicNameValuePair("f1",roll));
        	   	
        	   	StrictMode.setThreadPolicy(policy);
				//Toast.makeText(getBaseContext(),"Screen Size is : "+screenInches,Toast.LENGTH_SHORT).show();
        	   	Toast.makeText(getApplicationContext(), "Connecting...Please Wait", Toast.LENGTH_SHORT).show();
        	   	if(isNetworkAvailable())
        	   	{
                	try
                	{
	        	        HttpClient httpclient = new DefaultHttpClient();
	        	        HttpPost httppost = new HttpPost("http://nitrsp.byethost9.com/registration.php");
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
	        	                Toast.makeText(getApplicationContext(), "Input Reading pass", Toast.LENGTH_SHORT).show();
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
          	                  	/*String roll= object.getString("roll");
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
          	              		startActivity(i);*/
	                  			//layout.removeAllViews();
                			  		TextView name1=(TextView)findViewById(R.id.textView1);
                			  		TextView roll_no=(TextView)findViewById(R.id.textView2);
                			  		TextView p_program=(TextView)findViewById(R.id.textView3);
                			  		TextView p_dept=(TextView)findViewById(R.id.textView4);
                			  		
                			  		name1.setText(fname+" "+lname);
                					roll_no.setText(roll);
                					p_program.setText(program);
                					p_dept.setText(dept);
                			  		
        	                  		int j=Integer.parseInt(object.getString("j"));
        	                  		//int semester=Integer.parseInt(object.getString("semester"));
        	                  		Toast.makeText(getApplicationContext(), ""+j, Toast.LENGTH_SHORT).show();
        	                  		int[] textViewIDs = new int[] {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9, R.id.tv10, R.id.tv11, R.id.tv12, R.id.tv13, R.id.tv14, R.id.tv15, R.id.tv16, R.id.tv17, R.id.tv18, R.id.tv19, R.id.tv20, R.id.tv21, R.id.tv22, R.id.tv23, R.id.tv24, R.id.tv25, R.id.tv26, R.id.tv27, R.id.tv28, R.id.tv29, R.id.tv30, R.id.tv31, R.id.tv32, R.id.tv33 };
        	                  		for(int i=0;i<j;i++)
        	                  		{
        	                  	            //pairs.setText(object.getString(Integer.toString(k)));
        	                  	          	//courseID.setText(object.getString(Integer.toString(5*i+0)));
        	                  				int temp=i+1;
        	                  				String name = "tv"+temp;
        	                  				TextView tv = (TextView ) findViewById(textViewIDs[i]);
        	                  				if(i%3==2)
        	                  					tv.setText("Prof. "+object.getString(Integer.toString(i)));
        	                  				else
        	                  					tv.setText(object.getString(Integer.toString(i)));
        	                  				
        	                  				/*TextView textView1 = (TextView) findViewById(R.id.tv1); 
    	                  					textView1.setText(object.getString(Integer.toString(0)));
    	                  					TextView textView2 = (TextView) findViewById(R.id.tv2); 
    	                  					textView2.setText(object.getString(Integer.toString(1)));
    	                  					TextView textView3 = (TextView) findViewById(R.id.tv3); 
    	                  					textView3.setText(object.getString(Integer.toString(3)));*/
        	                  		}
        	                  		
        	                  		for(int i=j; i<textViewIDs.length;i++)
        	                  		{
        	                  			TextView tv = (TextView ) findViewById(textViewIDs[i]);
        	                  			tv.setVisibility(View.GONE);
        	                  		}
                		  }
                		  else
                		  {  
                			  Toast.makeText(getApplicationContext(), "Data Not Updated", Toast.LENGTH_SHORT).show();
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