package com.napster.nitrourkelastudentportal;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class examination extends Activity {
	Button ctt,me, ee;
	int flag=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.examination);
		
		ctt=(Button)findViewById(R.id.button1);
		me=(Button)findViewById(R.id.button2);	
		ee=(Button)findViewById(R.id.button3);	
		
        ctt.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), classtest.class);
				startActivity(i);
			}
		}); 
        	
        me.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), midsemester.class);
				startActivity(i);
				System.exit(0);   /* ADDED */
			}
		});
        
        ee.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), endsemester.class);
				startActivity(i);
				System.exit(0);   /* ADDED */
			}
		});
	}
}