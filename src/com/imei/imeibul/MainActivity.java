package com.imei.imeibul;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView deviceInfo;
	TelephonyManager telephonyManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView textview2 = (TextView)findViewById(R.id.textView2);
		ImageButton imagebutton1 = (ImageButton)findViewById(R.id.imageButton1);
		
	    telephonyManager = ((TelephonyManager)getSystemService("phone"));
	    textview2.setText(telephonyManager.getDeviceId());
	    
	    imagebutton1.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				int currentapiVersion = android.os.Build.VERSION.SDK_INT;
				if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
				     android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
				        ClipData clip = ClipData.newPlainText("imei", textview2.getText().toString());
				        clipboard.setPrimaryClip(clip); 
				} else{
				    android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
				    clipboard.setText(textview2.getText().toString());
				}
				Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();				
			}
		});//imagebutton1.setOnClickListener
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
