package com.napster.nitrourkelastudentportal;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class WebViewFragment extends Fragment implements OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		String url = getArguments().getString("url");

		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);
		
		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.fragment_layout, container, false);
		Button button1=(Button)v.findViewById(R.id.button1);
		Button button2=(Button)v.findViewById(R.id.button2);
		Button button3=(Button)v.findViewById(R.id.button3);
		Button button4=(Button)v.findViewById(R.id.button4);
		Button button5=(Button)v.findViewById(R.id.button5);
		Button button6=(Button)v.findViewById(R.id.button6);
		
		welcome activity = (welcome) getActivity();
		final String roll = activity.roll();
        final String fname = activity.fname();
        final String lname = activity.lname();
        final String dob = activity.dob();
        final String sex = activity.sex();
        final String program = activity.program();
        final String mno = activity.mno();
        final String father = activity.father();
        final String mother = activity.mother();
        final String dept = activity.dept();
		
		TextView name=(TextView)v.findViewById(R.id.textView1);
        TextView roll_no=(TextView)v.findViewById(R.id.textView2);
		
		name.setText(fname+" "+lname);
		roll_no.setText("( "+roll+" )");
		
		button1.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view)
			{	
				Toast.makeText(getActivity()," Button 1 Clicked",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getActivity(), profile.class);
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
        });
		button2.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view)
			{	
				Toast.makeText(getActivity()," Button 2 Clicked ",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getActivity(), search.class);
				startActivity(i);
			}
        });
		button3.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view)
			{	
				Toast.makeText(getActivity()," Button 3 Clicked ",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getActivity(), examination.class);
				startActivity(i);
			}
        });
		button4.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view)
			{	
				Toast.makeText(getActivity()," Button 4 Clicked ",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getActivity(), notice.class);
				startActivity(i);
			}
        });
		button5.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view)
			{	
				Toast.makeText(getActivity()," Button 5 Clicked ",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getActivity(), registration.class);
				Bundle basket= new Bundle();
	            basket.putString("roll", roll);
	            basket.putString("fname", fname);
	            basket.putString("lname", lname);
	            basket.putString("program", program);
	            basket.putString("dept", dept);
	            i.putExtras(basket);
				startActivity(i);
			}
        });
		button6.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view)
			{	
				Toast.makeText(getActivity()," Button 6 Clicked ",Toast.LENGTH_SHORT).show();
				Toast.makeText(getActivity()," Button 5 Clicked ",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getActivity(), attendence.class);
				Bundle basket= new Bundle();
	            basket.putString("roll", roll);
	            basket.putString("fname", fname);
	            basket.putString("lname", lname);
	            basket.putString("program", program);
	            basket.putString("dept", dept);
	            i.putExtras(basket);
				startActivity(i);
			}
        });
		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		//Initializing and loading url in webview
		//WebView webView = (WebView)v.findViewById(R.id.webView); 
		//webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl(url);
		Toast.makeText(getActivity()," "+url,Toast.LENGTH_SHORT).show();
		if(url.equals("Home"))
		{
			
		}
		else if(url.equals("search"))
		{
			Intent i = new Intent(getActivity(), search.class);
			startActivity(i);
		}
		else if(url.equals("website"))
		{
			Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://nitrkl.ac.in/"));
			startActivity(viewIntent);
		}
		else if(url.equals("feedback"))
		{
			url="Home";
			Intent i = new Intent(getActivity(), feedback.class);
			Bundle basket= new Bundle();
            basket.putString("roll", roll);
            i.putExtras(basket);
			startActivity(i);
			
		}
		else if(url.equals("contact"))
		{
			Intent i = new Intent(getActivity(), contact.class);
			startActivity(i);
		}
		else if(url.equals("about"))
		{
			Intent i = new Intent(getActivity(), about.class);
			startActivity(i);
		}
		else if(url.equals("exit"))
		{
			System.exit(0);
		}
		return v;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
}