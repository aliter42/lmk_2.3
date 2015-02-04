package com.android.letmeknow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
 
public class opp_details extends Activity {
	String response;
	String id;
	TextView title, where, org, start_date, end_date, deadline, eligibility, type, pub_date, website, email;
	//TextView details;
	WebView details;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opp_details);
        title = (TextView)findViewById(R.id.title);
        where = (TextView)findViewById(R.id.location);
        org = (TextView)findViewById(R.id.org);
        start_date = (TextView)findViewById(R.id.start_date);
        end_date = (TextView)findViewById(R.id.end_date);
        deadline = (TextView)findViewById(R.id.deadline);
        eligibility = (TextView)findViewById(R.id.eligibility);
        type = (TextView)findViewById(R.id.type);
        
        pub_date = (TextView)findViewById(R.id.pub_date);
        //details = (WebView)findViewById(R.id.details);
        details = (WebView)(findViewById(R.id.details));
        website = (TextView)findViewById(R.id.website);
        email = (TextView)findViewById(R.id.email);
        WebSettings webSettings = details.getSettings();
        webSettings.setDefaultFontSize(14);
        
        
        ConnectivityManager connMgr = (ConnectivityManager) 
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        
        if(isWifiConn || isMobileConn)
        {        
        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getString("id");
        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
       
        DownloadWebPageTask task = new DownloadWebPageTask();
		task.execute("http://calldriver.in/codeforfood/get_opp_details.php?id="+id);
        

           }
        else
        	Toast.makeText(getApplicationContext(), "Please Connect to the internet !", Toast.LENGTH_LONG).show();
    }    
    
    public void sendEmail(View v) {    	
    	Intent mail = new Intent(Intent.ACTION_SEND);
    	mail.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
    	mail.putExtra(Intent.EXTRA_SUBJECT, "subject");
    	mail.putExtra(Intent.EXTRA_TEXT, "");
    	mail.setType("message/rfc822");
    	startActivity(Intent.createChooser(mail, "Choose an Email client :"));        
      }  
    public void openWebsite(View v) {
    	String url = website.getText().toString();
    	if (!url.startsWith("http://") && !url.startsWith("https://"))
    		   url = "http://" + url;
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    	startActivity(browserIntent);
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
    	
    	private ProgressDialog Dialog = new ProgressDialog(opp_details.this);
    	
    	@Override
        protected void onPreExecute()
        {
        	
        		Dialog.setMessage("Loading..");
        		Dialog.show();
            
        }
        @Override
        protected String doInBackground(String... urls) {
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
          return response;
        }

        @Override
        protected void onPostExecute(String result) 
        {
        	try{
            	Log.e("response", "building array");
            	response = response.replace("null", "\"Data Unavailable\"");
            	JSONArray items_arr = new JSONArray(response);
                Log.e("response", "array built");
                Log.e("response new", response);
                int arrlen = items_arr.length();
                
                Log.e("arrlen", Integer.toString(arrlen));
               
            	
                for(int i=0; i<arrlen;i++)
                {
                	JSONObject obj = items_arr.getJSONObject(i);
                	if(!obj.getString("location").equalsIgnoreCase("Data Unavailable"))
                		where.setText("Location : "+ obj.getString("location"));
                	else
                		where.setVisibility(View.GONE);
                	
                	if(!obj.getString("name").equalsIgnoreCase("Data Unavailable"))
                		title.setText(obj.getString("name"));
                	else
                		title.setVisibility(View.GONE);
                		
                	if(!obj.getString("org").equalsIgnoreCase("Data Unavailable"))
                		org.setText("Organizer : " +obj.getString("org"));
                	else
                		org.setVisibility(View.GONE);
                	
                	if(!obj.getString("start_date").equalsIgnoreCase("Data Unavailable"))
                		start_date.setText("Start Date : "+obj.getString("start_date"));
                	else
                		start_date.setVisibility(View.GONE);
                	
                	if(!obj.getString("end_date").equalsIgnoreCase("Data Unavailable"))
                		end_date.setText("End Date : "+obj.getString("end_date"));
                	else
                		end_date.setVisibility(View.GONE);
                	
                	if(!obj.getString("deadline").equalsIgnoreCase("Data Unavailable"))
                		deadline.setText("Deadline : "+obj.getString("deadline"));
                	else
                		deadline.setVisibility(View.GONE);
                	
                	
                	if(!obj.getString("eligibility").equalsIgnoreCase("Data Unavailable"))
                	{
                		HashMap hm = new HashMap();
                    	hm.put("0", "Undergrad Level");
                    	hm.put("1", "Grad Level");
                    	hm.put("2", "Post Grad Level");
                    	hm.put("3", "Non - Students");
                	
                    	String[] parts = obj.getString("eligibility").split(",");
                    	
                    	String elig ="";
                    	
                    	for (String s : parts)
                    	{
                    		elig = hm.get(s)+" / "+elig;
                    	}
                    	elig = elig.substring(0,elig.length()-3);
                		eligibility.setText("Eligibility : "+ elig);
                	}
                	else
                		eligibility.setVisibility(View.GONE);
                	
                	if(!obj.getString("type").equalsIgnoreCase("Data Unavailable"))
                	{
                		HashMap hm = new HashMap();
                    	hm.put("0", "Internship/Training");
                    	hm.put("1", "Scholarship/Fellowship");
                    	hm.put("2", "Festival");
                    	hm.put("3", "Performing Art events");
                    	hm.put("4", "Conference/Symposium/Summit");
                    	hm.put("5", "Workshop/Camp");
                    	hm.put("6", "Competition/Contest");
                    	hm.put("7", "Graduate Program");
                    	hm.put("8", "Volunteering");
                    	hm.put("9", "Part-time job");
                    	hm.put("10", "Freelancer job");
                    	hm.put("11", "Campus job");
                    	hm.put("12", "Full-time job");
                    	hm.put("13", "Call for Papers"); 	
                    	
                    	
                		type.setText("Type Of Opportunity : "+ hm.get(obj.getString("type")));
                	}
                	else
                		type.setVisibility(View.GONE);
                	
                	if(!obj.getString("pub_date").equalsIgnoreCase("Data Unavailable"))
                		pub_date.setText("Published On : "+obj.getString("pub_date"));
                	else
                		pub_date.setVisibility(View.GONE);
                	
                	
                	
                	


          
                	//details.setText((Html.fromHtml(obj.getString("desc_long"))).toString());
                	String css = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />";
                	String desc_long = obj.getString("desc_long").replace("../../../", "http://letmeknow.in");
                	details.loadDataWithBaseURL("file:///android_asset/", css+desc_long, "text/html","utf-8",  null);
                	
                	
                	if(!obj.getString("website").equalsIgnoreCase("Data Unavailable"))
                		website.setText(obj.getString("website"));
                	else
                		website.setVisibility(View.GONE);
                	
                	if(!obj.getString("email").equalsIgnoreCase("Data Unavailable"))
                		email.setText(obj.getString("email"));
                	else
                		email.setVisibility(View.GONE);
                	
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
        		Log.e("some other error", e2.toString());
        	}
        	
        	
        }
      }


    } 





