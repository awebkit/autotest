package com.autonavi.tbt.navi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.os.Environment;
import android.os.SystemClock;

import com.autonavi.tbt.TBT;
import com.autonavi.tbt.demo.TBTNaviDemoMapView;

public class SetGPSInfor extends Thread{

	private TBT m_tbt;	
	private TBTNaviDemoMapView mTbtNavi;
	public SetGPSInfor(TBTNaviDemoMapView tbtNavi, TBT tbt){
		m_tbt = tbt;
		mTbtNavi = tbtNavi;	
	}	
	public void run(){
    	try{
    		FileInputStream fis=new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/GPSLog.txt");
    		 InputStreamReader isr=new InputStreamReader(fis);       
    		 BufferedReader br = new BufferedReader(isr);     
    		 String line="";       
    		 String[] arrs=null;
    		 while ((line=br.readLine())!=null) {  
    			 NmeaData nmea =new NmeaData();
    			 	//if (!line.contains("3A")) 
    			 { // valid GPS log
	    			    arrs=line.split(",");       
	    		  
	    				nmea.m_Longitude = Double.parseDouble(arrs[0]);		// γ��, ��λ�� (��ֵΪ��γ, ��ֵΪ��γ)
	    	    		nmea.m_Latitude = Double.parseDouble(arrs[1]);		// ����, ��λ�� (��ֵΪ����, ��ֵΪ����)
	    	    		nmea.m_Altitude = 300;		// ����, ��λ��
	    	    		nmea.m_Speed = Double.parseDouble(arrs[2]);			// �ٶ�, ��λǧ��/ʱ
	    	    		nmea.m_Track = Double.parseDouble(arrs[3]);			// �����, ��λ��
	    	    		nmea.m_MagVariation = 45;	// �شű仯, ��λ�� 
	    	    		nmea.m_Pdop = Double.parseDouble(arrs[4]);			// λ�þ��Ȳ���
	    	    		nmea.m_Hdop = 1;			// ˮƽ���Ȳ���
	    	    		nmea.m_Vdop = 1;			// ��ֱ���Ȳ���
	    	    		nmea.m_NumSats = 4;			// �ǿ�ͼ���Ǹ���
	    	    		nmea.m_FixedMode = 4;			// GPS��λ����
	    	    		nmea.m_BJYear = Integer.parseInt(arrs[5]);			// GPS(BJ)ʱ�䣭����
	    	    		nmea.m_BJMonth = Integer.parseInt(arrs[6]);			// GPS(BJ)ʱ�䣭����
	    	    		nmea.m_BJDay = Integer.parseInt(arrs[7]);			// GPS(BJ)ʱ�䣭����
	    	    		nmea.m_BJHour = Integer.parseInt(arrs[8]);			// GPS(BJ)ʱ�䣭��ʱ
	    	    		nmea.m_BJMinute = Integer.parseInt(arrs[9]);			// GPS(BJ)ʱ�䣭����
	    	    		nmea.m_BJSecond = Integer.parseInt(arrs[10]);			// GPS(BJ)ʱ�䣭����
	    	    		nmea.m_ValidChar = 'A';		//��λ�ɹ����ı�־
	    	    		nmea.m_LastFixQuality = 1; 	// ǰһ�ζ�λ������0 ��Ч���꣬1 ��Ч���꣬2 DGPS��������
	    	    		nmea.m_HasCoordEverBeenValid = true;	//�Ƿ�������λ�ɹ���
	    	    		//m_tbt.setGPSInfor(nmea);
	    	    		m_tbt.setGPSInfor(2, nmea.m_Longitude, nmea.m_Latitude, nmea.m_Speed, nmea.m_Track,
	    	    				nmea.m_BJYear, nmea.m_BJMonth, nmea.m_BJDay, nmea.m_BJHour, nmea.m_BJMinute, nmea.m_BJSecond);

						// update GPS view
						mTbtNavi.notifyGPSInfo(nmea);
	    			 }  	
    	    		
    	    		SystemClock.sleep(1000);    	    		
    	    		//sleep(1000);    			   		
    		}    	        
    	}
    	catch(Exception e){
    		e.printStackTrace();    		
    	} 
	}
}
