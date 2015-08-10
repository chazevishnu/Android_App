package com.napster.nitrourkelastudentportal;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.napster.nitrourkelastudentportal.R;
import com.napster.nitrourkelastudentportal.ShakeDetector.OnShakeListener;
import com.napster.nitrourkelastudentportal.WebViewFragment;
import com.napster.nitrourkelastudentportal.ShakeDetector;

public class welcome extends Activity{
	Button button1;
	String fname, lname, roll, dob, sex, program, mno, father, mother, dept;
	
    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
	
	// Within which the entire activity is enclosed
	private DrawerLayout mDrawerLayout;

	// ListView represents Navigation Drawer
	private ListView mDrawerList;

	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
	private ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	private String mTitle = "";

	@SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        
		// ShakeDetector initialization
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector();
		mShakeDetector.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
				Toast.makeText(getApplicationContext(), "Give Gesture Commands...", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), gesture.class);
				startActivity(i);
			}
		});
       
		Bundle gt=getIntent().getExtras();
		fname=gt.getString("fname");
		lname=gt.getString("lname");
		roll=gt.getString("roll");
		dob=gt.getString("dob");
		sex=gt.getString("sex");
		program=gt.getString("program");
		mno=gt.getString("mno");
		father=gt.getString("father");
		mother=gt.getString("mother");
		dept=gt.getString("dept");
		
		
		mTitle = "NITR Student Portal";
		getActionBar().setTitle(mTitle);

		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		mDrawerList.setClickable(false);
		mDrawerList.setTextFilterEnabled(true);
		
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			WebViewFragment rFragment = new WebViewFragment();
			Bundle data = new Bundle();
			data.putInt("position", 0);
			data.putString("url", "Google.com");
			rFragment.setArguments(data);
			
			
			// Getting reference to the FragmentManager
			FragmentManager fragmentManager = getFragmentManager();

			// Creating a fragment transaction
			FragmentTransaction ft = fragmentManager.beginTransaction();

			// Adding a fragment to the fragment transaction
			ft.replace(R.id.content_frame, rFragment);

			// Committing the transaction
			ft.commit();

			// Closing the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
		}
		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Options");
				invalidateOptionsMenu();
			}

		};
		
		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Getting an array of rivers
				String[] menuItems = getResources().getStringArray(R.array.menus);
				// Currently selected river
				mTitle = menuItems[position];

				// Creating a fragment object
				WebViewFragment rFragment = new WebViewFragment();

				// Passing selected item information to fragment
				Bundle data = new Bundle();
				data.putInt("position", position);
				data.putString("url", getUrl(position));
				rFragment.setArguments(data);
				
				
				// Getting reference to the FragmentManager
				FragmentManager fragmentManager = getFragmentManager();

				// Creating a fragment transaction
				FragmentTransaction ft = fragmentManager.beginTransaction();

				// Adding a fragment to the fragment transaction
				ft.replace(R.id.content_frame, rFragment);

				// Committing the transaction
				ft.commit();

				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});
		
		// Creating an ArrayAdapter to add items to the listview mDrawerList
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), 
				R.layout.drawer_list_item, getResources().getStringArray(R.array.menus));

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);
		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Setting item click listener for the listview mDrawerList
    }
	
	protected String getUrl(int position) {
		switch (position) {
		case 0:
			return "Home";
		case 1:
			return "search";
		case 2:
			return "website";
		case 3:
			return "feedback";
		case 4:
			return "contact";
		case 5:
			return "about";
		case 6:
			return "exit";
		default:
			return "Oops !!! Something went wrong";
		}
	}
	
	public String fname() {
        return fname;
    }
	
	public String lname() {
        return lname;
    }
	
	public String roll() {
        return roll;
    }
	
	public String dob() {
        return dob;
    }
	
	public String sex() {
        return sex;
    }
	
	public String program() {
        return program;
    }
	
	public String mno() {
        return mno;
    }
	
	public String father() {
        return father;
    }

	public String mother() {
        return mother;
    }
	
	public String dept() {
        return dept;
    }
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    } 
}