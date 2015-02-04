package com.android.letmeknow;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class search_tab extends Activity {
	LinearLayout linearLayout;
	ImageButton search;
	Spinner s;
	CheckBox sw;
	CheckBox el;
	CheckBox ppt;
	CheckBox engg;
	CheckBox vfi;
	CheckBox bp;
	CheckBox se;
	CheckBox cs;
	CheckBox pa;
	CheckBox lit;
	CheckBox de;
	CheckBox arch;
	CheckBox mnj;
	CheckBox math;
	CheckBox phy;
	CheckBox chem;
	CheckBox bio;
	CheckBox geo;
	CheckBox phi;
	CheckBox pol;
	CheckBox hist;
	CheckBox eco;
	CheckBox law;
	CheckBox fin;
	CheckBox sales;
	ImageButton datepicker1;
	ImageButton datepicker2;
	private int mYear;
    private int mMonth;
    private int mDay;
    TextView start_date;
    TextView end_date;

    static final int DATE_DIALOG_ID = 0;
    int id_2; 

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        
        String array_spinner[]=new String[5];
        array_spinner[0]="Technology";
        array_spinner[1]="Business";
        array_spinner[2]="Arts and Design";
        array_spinner[3]="Maths and Sciences";
        array_spinner[4]="Social Sciences";
        linearLayout =  (LinearLayout)findViewById(R.id.linearlayout1);
        s = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new MyOnItemSelectedListener());
        search = (ImageButton)findViewById(R.id.button1);
        search.setOnClickListener(new ButtonClickListener());
        datepicker1 = (ImageButton) findViewById(R.id.datepicker1);
        datepicker2 = (ImageButton) findViewById(R.id.datepicker2);
        start_date = (TextView)findViewById(R.id.start_date);
        end_date = (TextView)findViewById(R.id.end_date);
        
        datepicker1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(1);
            }
        });
        
        datepicker2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(2);
            }
        });
        
        
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        
        
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
    	
    	id_2 = id;
    	
    	Log.e("id", Integer.toString(id_2));
    	
    	DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, 
                                          int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        
                        start_date.setText(
                    			new StringBuilder()
                                // Month is 0 based so add 1
                                	.append(mMonth + 1).append("-")
                                	.append(mDay).append("-")
                                	.append(mYear).append(" "));
                        
                   }
                };
                
                DatePickerDialog.OnDateSetListener nDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year, 
                                                  int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;
                                
                                end_date.setText(
                            			new StringBuilder()
                                        // Month is 0 based so add 1
                                        	.append(mMonth + 1).append("-")
                                        	.append(mDay).append("-")
                                        	.append(mYear).append(" "));
                                
                                
                                
                           }
                        };
        switch (id) {
        case 1:
            return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        case 2:
            return new DatePickerDialog(this,
                        nDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
    
    public class MyOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parentview, View v,int position, long id){
            linearLayout.removeAllViews();
            if (position==0)
            {
            	sw = new CheckBox(getParent());
            	sw.setText("Software Engg./Coding/Programming");
            	el = new CheckBox(getParent());
            	el.setText("Electronics/VLSI/Robotics");
            	ppt = new CheckBox(getParent());
            	ppt.setText("Paper/Poster Presentation");
            	engg = new CheckBox(getParent());
            	engg.setText("Engineering");
            	linearLayout.addView(sw);
            	linearLayout.addView(el);
            	linearLayout.addView(ppt);
            	linearLayout.addView(engg);
            }
            if (position==1)
            {
            	vfi = new CheckBox(getParent());
            	vfi.setText("Venture Funding and Incubation");
            	bp = new CheckBox(getParent());
            	bp.setText("Business Plan");
            	se = new CheckBox(getParent());
            	se.setText("Social Entrepreneurship");
            	cs = new CheckBox(getParent());
            	cs.setText("Case Study");
            	fin = new CheckBox(getParent());
            	fin.setText("Finance");
            	sales = new CheckBox(getParent());
            	sales.setText("Sales/Marketing/HR");
            	linearLayout.addView(fin);
            	linearLayout.addView(vfi);
            	linearLayout.addView(bp);
            	linearLayout.addView(se);
            	linearLayout.addView(cs);
            	linearLayout.addView(sales);
            	
            }
            if (position==2)
            {
            	pa = new CheckBox(getParent());
            	pa.setText("Performing Arts (Theatre, Music etc.)");
            	lit = new CheckBox(getParent());
            	lit.setText("Literary");
            	de = new CheckBox(getParent());
            	de.setText("Design");
            	arch = new CheckBox(getParent());
            	arch.setText("Architecture");
            	mnj = new CheckBox(getParent());
            	mnj.setText("Media and Journalism");
            	linearLayout.addView(pa);
            	linearLayout.addView(lit);
            	linearLayout.addView(de);
            	linearLayout.addView(arch);
            	linearLayout.addView(mnj);

            }
            
            if (position==3)
            {
            	math = new CheckBox(getParent());
            	math.setText("Mathematics");
            	phy = new CheckBox(getParent());
            	phy.setText("Physics");
            	chem = new CheckBox(getParent());
            	chem.setText("Chemistry");
            	bio = new CheckBox(getParent());
            	bio.setText("Biology and Life Sciences");
            	geo = new CheckBox(getParent());
            	geo.setText("Geology and Earth Sciences");
            	linearLayout.addView(math);
            	linearLayout.addView(phy);
            	linearLayout.addView(chem);
            	linearLayout.addView(bio);
            	linearLayout.addView(geo);
            }
            if (position==4)
            {
            	phi = new CheckBox(getParent());
            	phi.setText("Philosophy and Psychology");
            	pol = new CheckBox(getParent());
            	pol.setText("Political Sciences");
            	hist = new CheckBox(getParent());
            	hist.setText("History");
            	eco = new CheckBox(getParent());
            	eco.setText("Economics");
            	law = new CheckBox(getParent());
            	law.setText("Law");
            	linearLayout.addView(phi);
            	linearLayout.addView(pol);
            	linearLayout.addView(hist);
            	linearLayout.addView(eco);
            	linearLayout.addView(law);
            }

        }

        public void onNothingSelected(AdapterView<?> arg0) 
        {
            //do nothing
        }
    };
    
    public class ButtonClickListener implements OnClickListener
    {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int position = s.getSelectedItemPosition();
			if(position ==0)
			{	String cat = "";
				if(sw.isChecked())
				{
					cat = cat+"1,";
				}
				if(el.isChecked())
				{
					cat = cat+"2,";
				}
				if(ppt.isChecked())
				{
					cat = cat+"3,";
				}
				if(engg.isChecked())
				{
					cat = cat+"4,";
				}
				
				if(cat.length()!=0)
				{
//					if(cat.charAt(cat.length()-1)==',')
//					{
//						cat = cat.substring(0, cat.length()-1);
//					}
					Intent search_result = new Intent(getApplicationContext(), search_result.class);
					search_result.putExtra("cat", cat);
					search_result.putExtra("start_date", start_date.getText());
					search_result.putExtra("end_date", end_date.getText());
					startActivity(search_result);
					
				}
				else
					Toast.makeText(getParent(), "Please Select a Sub - Category", Toast.LENGTH_LONG).show();
				
//				Toast.makeText(getParent(), cat, Toast.LENGTH_LONG).show();
			}
			
			if(position ==1)
			{	String cat = "";
				if(vfi.isChecked())
				{
					cat = cat+"6,";
				}
				if(bp.isChecked())
				{
					cat = cat+"7,";
				}
				if(se.isChecked())
				{
					cat = cat+"8,";
				}
				if(cs.isChecked())
				{
					cat = cat+"9,";
				}
				if(sales.isChecked())
				{
					cat = cat+"10,";
				}
				if(fin.isChecked())
				{
					cat = cat+"26,";
				}
				if(cat.length()!=0)
				{
					Intent search_result = new Intent(getApplicationContext(), search_result.class);
					search_result.putExtra("cat", cat);
					search_result.putExtra("start_date", start_date.getText());
					search_result.putExtra("end_date", end_date.getText());
					startActivity(search_result);
				}
				else
					Toast.makeText(getParent(), "Please Select a Sub - Category", Toast.LENGTH_LONG).show();
				
//				Toast.makeText(getParent(), cat, Toast.LENGTH_LONG).show();
			}
			if(position ==2)
			{	String cat = "";
				if(pa.isChecked())
				{
					cat = cat+"11,";
				}
				if(lit.isChecked())
				{
					cat = cat+"12,";
				}
				if(de.isChecked())
				{
					cat = cat+"13,";
				}
				if(arch.isChecked())
				{
					cat = cat+"14,";
				}
				if(mnj.isChecked())
				{
					cat = cat+"15,";
				}
				if(cat.length()!=0)
				{
					Intent search_result = new Intent(getApplicationContext(), search_result.class);
					search_result.putExtra("cat", cat);
					search_result.putExtra("start_date", start_date.getText());
					search_result.putExtra("end_date", end_date.getText());
					startActivity(search_result);
				}
				else
					Toast.makeText(getParent(), "Please Select a Sub - Category", Toast.LENGTH_LONG).show();
				
//				Toast.makeText(getParent(), cat, Toast.LENGTH_LONG).show();
			}
			if(position ==3)
			{	String cat = "";
				if(math.isChecked())
				{
					cat = cat+"16,";
				}
				if(phy.isChecked())
				{
					cat = cat+"17,";
				}
				if(chem.isChecked())
				{
					cat = cat+"18,";
				}
				if(bio.isChecked())
				{
					cat = cat+"19,";
				}
				if(geo.isChecked())
				{
					cat = cat+"20,";
				}
				if(cat.length()!=0)
				{
					Intent search_result = new Intent(getApplicationContext(), search_result.class);
					search_result.putExtra("cat", cat);
					search_result.putExtra("start_date", start_date.getText());
					search_result.putExtra("end_date", end_date.getText());
					startActivity(search_result);
				}
				else
					Toast.makeText(getParent(), "Please Select a Sub - Category", Toast.LENGTH_LONG).show();
				
//				Toast.makeText(getParent(), cat, Toast.LENGTH_LONG).show();
			}
			if(position ==4)
			{	String cat = "";
				if(phi.isChecked())
				{
					cat = cat+"21,";
				}
				if(pol.isChecked())
				{
					cat = cat+"22,";
				}
				if(hist.isChecked())
				{
					cat = cat+"23,";
				}
				if(eco.isChecked())
				{
					cat = cat+"24";
				}
				if(law.isChecked())
				{
					cat = cat+"25,";
				}
				if(cat.length()!=0)
				{
					Intent search_result = new Intent(getApplicationContext(), search_result.class);
					search_result.putExtra("cat", cat);
					search_result.putExtra("start_date", start_date.getText());
					search_result.putExtra("end_date", end_date.getText());
					startActivity(search_result);
				}
				else
					Toast.makeText(getParent(), "Please Select a Sub - Category", Toast.LENGTH_LONG).show();

//				Toast.makeText(getParent(), cat, Toast.LENGTH_LONG).show();
				
			}
			
		}
    	
    }
}