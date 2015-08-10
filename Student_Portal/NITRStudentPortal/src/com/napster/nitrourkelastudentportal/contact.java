package com.napster.nitrourkelastudentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class contact extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
	}

	@Override
	protected void onPause(){
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
