package com.android.letmeknow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
 
public class search_result extends Activity implements
        OnItemClickListener{
	String cat;
	ArrayList<String> titles;
	ArrayList<String> descriptions;
	ArrayList<String> start_dates;
	ArrayList<String> end_dates;
	ArrayList<String> deadlines;
	ArrayList<String> ids;
	int count = 0;
	String response;
	Intent opp_details;
	Bundle bundle;	
    ListView listView;
    List<row_item> rowItems;
    CustomArrayAdapter adapter;
    String start_date;
    String end_date;
    String url = "http://calldriver.in/codeforfood/search_results.php?";
    int check = 0;
    

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        cat = this.getIntent().getStringExtra("cat");
        start_date = this.getIntent().getStringExtra("start_date");
        end_date = this.getIntent().getStringExtra("end_date");
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("cat", cat));
        params.add(new BasicNameValuePair("start_date", start_date));
        params.add(new BasicNameValuePair("end_date", end_date));
        params.add(new BasicNameValuePair("count",Integer.toString(count)));
        String paramString = URLEncodedUtils.format(params, "utf-8");
        url += paramString;

        
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        
        if(isWifiConn || isMobileConn)
        {        
        
	        titles = new ArrayList<String>();
	        descriptions = new ArrayList<String>();
	        start_dates = new ArrayList<String>();
	        end_dates = new ArrayList<String>();
	        ids = new ArrayList<String>();
	        deadlines = new ArrayList<String>();
	        DownloadWebPageTask task = new DownloadWebPageTask();
	        
			task.execute(url);
	        rowItems = new ArrayList<row_item>();
	        for (int i = 0; i < titles.size(); i++) {
	            row_item item = new row_item(titles.get(i), start_dates.get(i), end_dates.get(i), descriptions.get(i), deadlines.get(i));
	            rowItems.add(item);
	        }
	 
	        listView = (ListView) findViewById(R.id.list);
	        adapter = new CustomArrayAdapter(this,R.layout.row_layout, rowItems);
	        listView.setAdapter(adapter);
	        listView.setOnItemClickListener(this);
	        listView.setOnScrollListener(new OnScrollListener() {
	
	            @Override
	            public void onScrollStateChanged(AbsListView view, int scrollState) {
	            }
	
	            @Override
	            public void onScroll(AbsListView view, int firstVisibleItem,
	                    int visibleItemCount, int totalItemCount) {
	            	int lastInScreen = firstVisibleItem + visibleItemCount;
	            	if(lastInScreen == totalItemCount && count!=0)
	            	{
	            		DownloadWebPageTask task_1 = new DownloadWebPageTask();
	            		List<NameValuePair> params = new LinkedList<NameValuePair>();
	                    params.add(new BasicNameValuePair("cat", cat));
	                    params.add(new BasicNameValuePair("start_date", start_date));
	                    params.add(new BasicNameValuePair("end_date", end_date));
	                    params.add(new BasicNameValuePair("count",Integer.toString(count)));
	                    String paramString = URLEncodedUtils.format(params, "utf-8");
	                    url += paramString;
	            		task_1.execute(url);
	            	}
	               	Log.e("scroll", "scroll");
	                }
	        });
	    }
        else
        	Toast.makeText(getApplicationContext(), "Please Connect to the internet !", Toast.LENGTH_LONG).show();
    }
      
    
 
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//        Toast toast = Toast.makeText(getApplicationContext(),
//            "Item " + (position + 1) + ": " + rowItems.get(position),
//            Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//        toast.show();
//        Toast.makeText(getApplicationContext(), ids.get(position), Toast.LENGTH_SHORT).show();
        opp_details = new Intent(getApplicationContext(), opp_details.class);
       	opp_details.putExtra("id",ids.get(position) );
        startActivity(opp_details);
    }
    

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        
        private ProgressDialog Dialog = new ProgressDialog(search_result.this);

        @Override
        protected void onPreExecute()
        {
            Dialog.setMessage("Loading..");
            Dialog.show();
        }
        
    	@Override
        protected String doInBackground(String... urls) {
    		if (check ==0 )
    		{ 
    			check = 1;
    		
          response = "[";
          for (String url : urls) {
        	  Log.e("url", url);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
            	Log.e("httpget", "httpget");
              HttpResponse execute = client.execute(httpGet);
              InputStream content = execute.getEntity().getContent();
              Log.e("httpget", "afterhttpget");
              BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
              String s = "";
              while ((s = buffer.readLine()) != null) {
                response += s;
              }
              response += "]";
              
              Log.e("response", response);              
              
            } catch (Exception e) {
              e.printStackTrace();
            }
            
            
          }
          
        }
    		return response;
    		}

        @Override
        protected void onPostExecute(String result) 
        {
        	check =0;
        	if(response.compareTo("[[]]")==0)
        	{
        	Toast.makeText(getApplicationContext(), "No Matching Results Found !", Toast.LENGTH_LONG).show();	
        	Dialog.dismiss();
        	 finish();
        	
        	}
        	else
        	{
        	try{
            	Log.e("response", "building array");
            	response = response.replace("null", "\"Data Unavailable\"");
            	JSONArray items_arr = new JSONArray(response);
                Log.e("response", "array built");
                Log.e("response new", response);
                int arrlen = items_arr.length();
                
                Log.e("arrlen", Integer.toString(arrlen));
                if(arrlen<15)
                	  count = 0;
                else
              	  count = count+15;
            	
                for(int i=0; i<arrlen;i++)
                {
                	JSONObject obj = items_arr.getJSONObject(i);
                	titles.add(obj.getString("name"));
                	ids.add(obj.getString("id"));
                	descriptions.add(obj.getString("description"));
                	start_dates.add(obj.getString("start_date"));
                	end_dates.add(obj.getString("end_date"));
                	deadlines.add(obj.getString("deadline"));
                	row_item new_item = new row_item(obj.getString("name"), obj.getString("start_date"), obj.getString("end_date"), obj.getString("description"),obj.getString("deadline"));
                	adapter.add( new_item);
                	Dialog.dismiss();
                	            	
                }
            }
            catch (JSONException e1 ) {
        		// TODO Auto-generated catch block
        		e1.printStackTrace();
        		Log.e("json", e1.toString());
        		
        		}
        	catch(Exception e2)
        	{
        		Log.e("some other erroe", e2.toString());
        	}
        	
        	adapter.notifyDataSetChanged();
        }
      }

    } 
}





