package com.android.letmeknow;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tabHost = getTabHost();
        TabSpec searchspec = tabHost.newTabSpec("search");
        // setting Title and Icon for the Tab
        searchspec.setIndicator(null, getResources().getDrawable(R.layout.search_icon));
        Intent searchintent = new Intent(this, search_tab.class);
        searchspec.setContent(searchintent);
        
        TabSpec categoriesspec = tabHost.newTabSpec("Songs");
        categoriesspec.setIndicator(null, getResources().getDrawable(R.layout.cat_icon));
        Intent catIntent = new Intent(this, categories_tab.class);
        categoriesspec.setContent(catIntent);
        
        tabHost.addTab(categoriesspec); // Adding categories tab
        tabHost.addTab(searchspec); // Adding search tab
        setTabColor(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
        	  @Override
        	  public void onTabChanged(String tabId) {
        	    int tab = tabHost.getCurrentTab();
        	    tabHost.getTabWidget().getChildAt(tab).setBackgroundColor(Color.parseColor("#FFFFFF"));
        	    setTabColor(tabHost);
        	  }
        	});
        
        
    }
    
    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF")); // selected
    }
    
    public void switchTab(int tab){
        tabHost.setCurrentTab(tab);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
