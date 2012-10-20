package com.autonavi.tbt.demo;

import com.example.autotest.ScreenShotHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class TBTNaviWarningActivity extends Activity {
	
	private Button agreed; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.warning);
		
		agreed = (Button)findViewById(R.id.warning_agreed);
		agreed.setOnClickListener(onClick);
		
		ScreenShotHelper.setActivity(this);
	}
	
	private OnClickListener onClick=new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == agreed)
			{
				if (networkStatus() && gpsStatus())
				{
					Intent intent = new Intent(getApplicationContext(), TBTNaviDemoMapView.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(intent);
					
					finish();
				}
			}
		}
	};
	 
	private boolean networkStatus() { 
		boolean netSataus = false;        
		ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);        
		cwjManager.getActiveNetworkInfo();        
		if (cwjManager.getActiveNetworkInfo() != null) {
			netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
		}        
		
		if (false == netSataus) 
		{ 
			Builder b = new AlertDialog.Builder(this).setTitle("û�п��õ�����").setMessage("�Ƿ������������ã�");            
			
			AlertDialog d = b.setPositiveButton("��", new DialogInterface.OnClickListener() {    		
				public void onClick(DialogInterface dialog, int whichButton) {
					Intent mIntent = new Intent("/");                    
					ComponentName comp = new ComponentName( 
						"com.android.settings", 
						"com.android.settings.WirelessSettings"); 
				
					mIntent.setComponent(comp);                    
					mIntent.setAction("android.intent.action.VIEW");                    
					startActivityForResult(mIntent,0);  // �����������ɺ���Ҫ�ٴν��в�����������д�������룬�����ﲻ����д                
				}            
			}).setNeutralButton("��", new DialogInterface.OnClickListener() { 
				public void onClick(DialogInterface dialog, int whichButton) { 
					dialog.cancel();                
				}            
			}).show();
			ScreenShotHelper.registerDialog(d);
		}        
		
		return netSataus;    
	}
	
	private boolean gpsStatus() {
		boolean gpsStatus = false;
		
		LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {

            gpsStatus = true;
        }
		if (!gpsStatus) {
			Builder b = new AlertDialog.Builder(this).setTitle("GPS����δ��").setMessage("�Ƿ��GPS����������ã�");   
			AlertDialog d = b.setPositiveButton("��", new DialogInterface.OnClickListener() {    		
				public void onClick(DialogInterface dialog, int whichButton) {
					Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			        startActivityForResult(intent,0);          
				}            
			}).setNeutralButton("��", new DialogInterface.OnClickListener() { 
				public void onClick(DialogInterface dialog, int whichButton) { 
					dialog.cancel();                
				}            
			}).show();
			ScreenShotHelper.registerDialog(d);
		}
		
		return gpsStatus;
	}
}
