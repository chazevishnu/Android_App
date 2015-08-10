package com.napster.nitrourkelastudentportal;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
		Bundle gt=getIntent().getExtras();
		String fname=gt.getString("fname");
		String lname=gt.getString("lname");
		String roll=gt.getString("roll");
		String dob=gt.getString("dob");
		String sex=gt.getString("sex");
		String program=gt.getString("program");
		String mno=gt.getString("mno");
		String father=gt.getString("father");
		String mother=gt.getString("mother");
		String dept=gt.getString("dept");
		
		TextView name=(TextView)findViewById(R.id.textView1);
        TextView roll_no=(TextView)findViewById(R.id.textView2);
        TextView p_program=(TextView)findViewById(R.id.textView3);
        TextView p_dept=(TextView)findViewById(R.id.textView4);
        TextView p_mno=(TextView)findViewById(R.id.textView5);
        TextView p_sex=(TextView)findViewById(R.id.textView6);
        TextView p_dob=(TextView)findViewById(R.id.textView7);
        TextView p_father=(TextView)findViewById(R.id.textView8);
        TextView p_mother=(TextView)findViewById(R.id.textView9);
		
		name.setText(fname+" "+lname);
		roll_no.setText(roll);
		p_program.setText(program);
		p_dept.setText(dept);
		p_mno.setText(mno);
		p_sex.setText(sex);
		p_dob.setText(dob);
		p_father.setText(father);
		p_mother.setText(mother);
		

    }
}