package com.example.smstest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import android.provider.ContactsContract;
	 

public class MainActivity extends Activity  implements LocationListener {
	  Button button;
	  EditText editPhoneNum;
	  EditText editSMS;
	  
	  private LocationManager locationManager;
	  private TextView tv;
	  private static final String TAG = "SMStestApp";  
	  private double Latitude;
	  private double Longitude;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 button = (Button) findViewById(R.id.SendButton);
		 editPhoneNum = (EditText) findViewById(R.id.TelefonNumarasi);
		 editSMS = (EditText) findViewById(R.id.SmsText);
		 
		 Latitude=0;
		 Longitude=0;
		 	 
		 button.setOnClickListener(new OnClickListener() {
		 	 
			 @Override
			 public void onClick(View v) {
		 	 
				 String phoneNo = editPhoneNum.getText().toString();
				 String sms     = "Ercan Test SMS:<<"+editSMS.getText().toString()+">>Test SMS idir.";
				 try {
					 	SmsManager smsManager = SmsManager.getDefault();
					 	smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					 	Toast.makeText(getApplicationContext(), "SMS Sent!",
		 		 			Toast.LENGTH_LONG).show();
		 	     	} catch (Exception e) {
		 	     		Toast.makeText(getApplicationContext(),
		 	                            "SMS faild, please try again later!",
		 	                            Toast.LENGTH_LONG).show();
		 	                    e.printStackTrace();
		 	     	}
		 	 
			 }
		 });
		 
		 
		 /********** get Gps location service LocationManager object ***********/
         locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         tv = (TextView) findViewById(R.id.label1);
          
         /* CAL METHOD requestLocationUpdates */
            
           // Parameters :
           //   First(provider)    :  the name of the provider with which to register 
           //   Second(minTime)    :  the minimum time interval for notifications, 
           //                         in milliseconds. This field is only used as a hint 
           //                         to conserve power, and actual time between location 
           //                         updates may be greater or lesser than this value. 
           //   Third(minDistance) :  the minimum distance interval for notifications, in meters 
           //   Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location) 
           //                         method will be called for each location update 
         
          
         locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                 1000,   // 3 sec
                 1, this);
          
         /********* After registration onLocationChanged method  ********/
         /********* called periodically after each 3 sec ***********/
     }
      
     /************* Called after each 3 sec **********/
     @Override
     public void onLocationChanged(Location location) {
             
    	 
         String str = "Latitude: "+location.getLatitude()+"\r\nLongitude: "+location.getLongitude()+"\r\n";
         tv.setText(str);
         Latitude=location.getLatitude();
         Longitude=location.getLatitude();
         
         Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
     }
  
     @Override
     public void onProviderDisabled(String provider) {
          
         /******** Called when User off Gps *********/
          
         Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
     }
  
     @Override
     public void onProviderEnabled(String provider) {
          
         /******** Called when User on Gps  *********/
          
         Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
     }
  
     @Override
     public void onStatusChanged(String provider, int status, Bundle extras) 
     {
    	 String str = "Latitude: "+Latitude+"\r\nLongitude: "+Longitude+"\r\n"
    			    +"status changed to " + provider + " [" + status + "]"+">>"+extras;
         tv.setText(str);

          
     }

     
		 /*		 
		 

	        tv = (TextView) findViewById(R.id.label1);

	        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
	        
	        Log.v(TAG,"Uygulama basladi");

	}
	
    public void onLocationChanged(Location arg0) {

        String lat = String.valueOf(arg0.getLatitude());
        String lon = String.valueOf(arg0.getLongitude());
        Log.e("GPS", "location changed: lat="+lat+", lon="+lon);
        tv.setText("lat="+lat+", lon="+lon);

    }

    public void onProviderDisabled(String arg0) {

        Log.e("GPS", "provider disabled " + arg0);

    }

    public void onProviderEnabled(String arg0) {

        Log.e("GPS", "provider enabled " + arg0);

    }

    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

        Log.e("GPS", "status changed to " + arg0 + " [" + arg1 + "]");
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/

}
