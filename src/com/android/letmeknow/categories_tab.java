package com.android.letmeknow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class categories_tab extends Activity {
	
	ImageButton artNd,tech, mathNsc, business,all, sNs;
	Intent intent;
	Bundle bundle;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_layout);
        
     tech = (ImageButton)findViewById(R.id.tech);
     artNd = (ImageButton)findViewById(R.id.artNd);
     all = (ImageButton)findViewById(R.id.all);
     sNs = (ImageButton)findViewById(R.id.sNs);
     mathNsc = (ImageButton)findViewById(R.id.mathNsc);
     business = (ImageButton)findViewById(R.id.business);
     
     tech.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             // TODO Auto-generated method stub
        	 move_to_list("tech");
         }
     });
     mathNsc.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             // TODO Auto-generated method stub
        	 move_to_list("math");
         }
     });
     
     sNs.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             // TODO Auto-generated method stub
        	 move_to_list("sns");
         }
     });
     all.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             // TODO Auto-generated method stub
        	 move_to_list("all");
         }
     });
     business.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             // TODO Auto-generated method stub
        	 move_to_list("business");
         }
     });
     artNd.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             // TODO Auto-generated method stub
        	 move_to_list("artnd");
         }
     });
     
    }
    
    void move_to_list(String cat)
    {
    	intent = new Intent(this, list.class);
    	intent.putExtra("cat",cat );
    	startActivity(intent);
    }
    
    }

