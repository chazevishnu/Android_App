package com.napster.nitrourkelastudentportal;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
 
public class gesture extends Activity {
 
GestureLibrary gestureLibrary = null;
GestureOverlayView gestureOverlayView;
 
 /** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.gesture);
     gestureOverlayView = (GestureOverlayView)findViewById(R.id.gestures);
   
     gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
     gestureLibrary.load();
   
     gestureOverlayView.addOnGesturePerformedListener(gesturePerformedListener);
 }
 
 OnGesturePerformedListener gesturePerformedListener
 = new OnGesturePerformedListener(){
 
 @Override
 public void onGesturePerformed(GestureOverlayView view, Gesture gesture) {
  // TODO Auto-generated method stub
  ArrayList<Prediction> prediction = gestureLibrary.recognize(gesture);
  String gesturePerformed = prediction.get(0).name;
  if(prediction.size() > 0){
   
   if(gesturePerformed.equals("exit")){ 
		  Toast.makeText(getApplicationContext(), "Exiting...", Toast.LENGTH_LONG).show();
		  finish();
	  }else if(gesturePerformed.equals("google")){ 
		  Toast.makeText(getApplicationContext(), "Forwarding to Google.co.in", Toast.LENGTH_LONG).show();
		  Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://google.co.in/"));
		  startActivity(viewIntent);
	  }else if(gesturePerformed.equals("help")){
		  Toast.makeText(getApplicationContext(), "Forwarding to Help Module", Toast.LENGTH_LONG).show();
	  }else if(gesturePerformed.equals("nit")){
		  Toast.makeText(getApplicationContext(), "Forwarding to NIT Rourkela Website", Toast.LENGTH_LONG).show();
		  Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://nitrkl.ac.in/"));
		  startActivity(viewIntent);
	  }else if(gesturePerformed.equals("profile")){
		  Toast.makeText(getApplicationContext(), "Your Profile", Toast.LENGTH_LONG).show();
		  finish();
	  }else if(gesturePerformed.equals("search")){ 
		  Toast.makeText(getApplicationContext(), "Search for Student or Faculty", Toast.LENGTH_LONG).show();
	  }
  }
  
 }};
}