package com.napster.nitrourkelastudentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class about extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
	}

	@Override
	protected void onPause(){
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
