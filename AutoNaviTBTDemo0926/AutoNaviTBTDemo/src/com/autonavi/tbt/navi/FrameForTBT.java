package com.autonavi.tbt.navi;

import com.autonavi.tbt.CarLocation;
import com.autonavi.tbt.DGNaviInfor;
import com.autonavi.tbt.IFrameForTBT;
import com.autonavi.tbt.TBT;
import com.autonavi.tbt.demo.TBTNaviDemoMapView;

public class FrameForTBT implements IFrameForTBT{
	
	private TBT mTbt;
	private TBTNaviDemoMapView mTbtNavi;
	
	public FrameForTBT(TBTNaviDemoMapView tbtNavi, TBT tbt)
	{
		mTbtNavi = tbtNavi;
		mTbt = tbt;
	}
	
	/********************************************************************
	�������ƣ�netRequestHttp
	���ܽ��ܣ�HTTP����
	�� �� ֵ����
	˵    �����˽ӿ�Ϊ�첽�ӿڣ�����ռ��ʵ���ߵ��̣߳��������ñ�����������
	********************************************************************/
	public void netRequestHttp(
		int moduleID,	
		int connectId,			// [I] ����ID��Frame�������ݺ��ô�ID�����ݴ���TBT
		int type,				// [I] 0ΪPost��ʽ��1ΪGet��ʽ
		String url,				// [I] �����URL��
		String head,			// [I] HTTPͷ��Ĭ��Ϊ��
		byte[] data,			// [I] Post��ʽ��Data���ݣ�Ĭ��Ϊ��
		int dataLength			// [I] Data���ݳ��ȣ�Ĭ��Ϊ��
		) 
	{
		HttpRequest http = new HttpRequest(mTbt, connectId, type, url, head, data, dataLength);
		http.start();
	}


	/********************************************************************
	���ܽ��ܣ�����GPS�Ƿ���Ч
	�� �� ֵ����
	˵    ������
	********************************************************************/
	public void setGpsValid(
		int isValid				// [I] 0 ��Ч��1��Ч
		)
	{
		
	}
	
	/********************************************************************
	�������ƣ�updateNaviInfor
	���ܽ��ܣ����µ�����Ϣ���μ�DGNaviInfor����
	�� �� ֵ����
	˵    ������
    ********************************************************************/
	public void updateNaviInfor(
		DGNaviInfor  dgNaviInfor		// [I] ������Ϣ
		) 
	{
		String text = "��ǰ��·���� = ";
		text += dgNaviInfor.m_CurRoadName;
		text += "\n��һ·�� = ";
		text += dgNaviInfor.m_NextRoadName;
		text += "\n�����۾��� = ";
		text += dgNaviInfor.m_CameraDist;
		text += "\n·��ʣ����� = ";
		text += dgNaviInfor.m_RouteRemainDis;
		text += "\n·��ʣ��ʱ�� = ";
		text += dgNaviInfor.m_RouteRemainTime;
		text += "\n·��ʣ����� = ";
		text += dgNaviInfor.m_SegRemainDis;
		text += "\n·��ʣ��ʱ�� = ";
		text += dgNaviInfor.m_SegRemainTime;
		text += "\nת���ͷ = ";
		text += dgNaviInfor.m_Icon;
		String s = "Loc " + dgNaviInfor.m_Longitude;
		s += " " + dgNaviInfor.m_Latitude;
		s += " " + dgNaviInfor.m_CarDirection;
//		System.out.println(s);
		
		System.out.printf("updateNaviInfor[CCP]: %.6f, %.6f\n", dgNaviInfor.m_Longitude, dgNaviInfor.m_Latitude);

		mTbtNavi.updateNaviInfor(dgNaviInfor);
	}



	@Override
	public void showCross(byte[] pCrossBackBuf, byte[] pCrossArrowBuf,
			int iBackSize, int iArrowSize) {
		
		mTbtNavi.showCross(pCrossBackBuf, pCrossArrowBuf, iBackSize, iArrowSize);
		
	}
	
	@Override
	public void hideCross() {
		// TODO Auto-generated method stub
		mTbtNavi.hideCross();
		
	}

	/********************************************************************
	�������ƣ�ShowLaneInfo
 	���ܽ��ܣ���ʾ������Ϣ
	�� �� ֵ����
	˵    ������
    ********************************************************************/
	public void showLaneInfo(
		byte[] laneBackInfo,			// [I] ����������Ϣ
		byte[] laneSelectInfo			// [I] ����ѡ����Ϣ
		) {
	/*	System.out.printf("showLaneInfo: laneBackInfo[%d, %d, %d, %d, %d, %d, %d, %d], laneSelectInfo[%d, %d, %d, %d, %d, %d, %d, %d]\n",
				laneBackInfo[0],laneBackInfo[1],laneBackInfo[2],laneBackInfo[3],laneBackInfo[4],
				laneBackInfo[5],laneBackInfo[6],laneBackInfo[7], laneSelectInfo[0], laneSelectInfo[1]
				, laneSelectInfo[2], laneSelectInfo[3], laneSelectInfo[4], laneSelectInfo[5], laneSelectInfo[6], laneSelectInfo[7]);*/
		mTbtNavi.showLaneInfo(laneBackInfo, laneSelectInfo);
	}

	/********************************************************************
	�������ƣ�hideLaneInfo
	���ܽ��ܣ����س�����Ϣ
	�� �� ֵ����
	˵    ������
    ********************************************************************/
	public void hideLaneInfo() {
		//System.out.println("hideLaneInfo");
		mTbtNavi.hideLaneInfo();
		
	}

	/********************************************************************
	�������ƣ�playNaviSound
	���ܽ��ܣ�ʹ��TTS����һ���ַ���
	�� �� ֵ����
	˵    ��������ռ��Frame���̣߳��˺������ú����Ϸ���
    ********************************************************************/
	public void playNaviSound(
		int iSoundType,			   // [I] �������ͣ�1 ����������2 ǰ��·������
		String soundStr				// [I] Ҫ�������ַ���Unicode����
		) 
	{
		System.out.println(soundStr);
		if (mTbtNavi != null) {
	//		if (Tts.JniIsPlaying() == 2) {
				((TBTNaviDemoMapView) mTbtNavi).playSound(soundStr);
	//		}
		}

		mTbtNavi.PostText(soundStr, 1);

	}
	/********************************************************************
	�������ƣ�endEmulatorNavi
	���ܽ��ܣ�ֹͣģ�⵼��
	�� �� ֵ����
	˵    ����ָ��ģ�⵼��������֪ͨFrame�Ա����UI�����
    ********************************************************************/
	public void endEmulatorNavi() 
	{
		if (mTbt != null) {
			mTbt.stopEmulatorNavi();
			mTbtNavi.onEmulatorNaviEnd();
		}

	}

	/********************************************************************
	�������ƣ�arriveWay
	���ܽ��ܣ�֪ͨ����;���㣬�������Ŀ�ĵ�iWayIDΪ0
	�� �� ֵ����
	˵    ������
	********************************************************************/
	public void arriveWay(
		int wayId		// [I] ����;����ı�ţ���Ŵ�1��ʼ���������Ŀ�ĵ�iWayIDΪ0
		) 
	{
		if (wayId == 0)
		{
			mTbtNavi.onGPSNaviEnd();
		}
	}

	/****************************************************************
	�������ƣ�reroute
	���ܽ��ܣ�֪ͨFrame��Ҫ���¹滮·��
	�� �� ֵ����
	˵    ������
	****************************************************************/
	public void reroute()
	{
		if (mTbtNavi != null) {
	//		if (Tts.JniIsPlaying() == 2) {
				((TBTNaviDemoMapView) mTbtNavi).playSound("ƫ��·�����������¹滮·��");
	//		}
		}
		mTbtNavi.reroute();
	}
	
	public void rerouteForTMC() {
		if (mTbtNavi != null) {
	//		if (Tts.JniIsPlaying() == 2) {
				((TBTNaviDemoMapView) mTbtNavi).playSound("������Ĭ��·�����¹滮·��");
	//		}
		}
		mTbtNavi.tmcReroute();
	}
	/********************************************************************
	�������ƣ�routeDestroy
	���ܽ��ܣ�֪ͨ��ǰ·������
	�� �� ֵ����
	˵    ������
    ********************************************************************/
	public void routeDestroy() 
	{
		int i = 0;
		i++;
	}
//	CarLocation loc;
	/********************************************************************
	�������ƣ�carLocationChange
	���ܽ��ܣ�֪ͨ��ǰƥ����GPSλ�õ�
	�� �� ֵ����
	˵    ����
    ********************************************************************/
	public void carLocationChange(
		CarLocation  carLocation			// [I] ��ǰƥ���ĵ㣬�μ�VPLocation�ṹ
		) 
	{
		//System.out.printf("carLocationChange: %.6f, %.6f\n", carLocation.m_Longitude, carLocation.m_Latitude);
	 	mTbtNavi.onCarLocationChanged(carLocation);
		//loc= carLocation;

	}
 
	/********************************************************************
	�������ƣ�setRequestRouteState
	���ܽ��ܣ�֪ͨ·������״̬
	�� �� ֵ����
	˵    ����requestRouteState��������
			1	·������ɹ�
			2	���糬ʱ
			3	����ʧ��
			4	�����������
			5	�������ݸ�ʽ����
			6	�����Χû�е�·
			7	����ڲ��н�
			8	�յ���Χû�е�·
			9	�յ��ڲ��н�
			10	;������Χû�е�·
			11	;�����ڲ��н�
    ********************************************************************/
	public void setRequestRouteState(
		int requestRouteState
		) {
		
		String str = "setRequestRouteState = ";
		str += requestRouteState;
		System.out.println(str);
		
		mTbtNavi.notifyRouteResult(requestRouteState);
		mTbtNavi.setRoutePrepared(true);
		
/*		String rpHint;

		switch(requestRouteState){
		case 1:
			
			mTbtNavi.setRoutePrepared(true);
			rpHint = "·������ɹ�";
	//		mTbtNavi.createDialog();
	//		mTbtNavi.createNaviChoiceDialog();
			break;
		case 2:
			rpHint = "���糬ʱ";
			break;
		case 3:
			rpHint = "����ʧ��";
			break;
		case 4:
			rpHint = "�����������";
			break;
		case 5:
			rpHint = "�������ݸ�ʽ����";
			break;
		case 6:
			rpHint = "�����Χû�е�·";
			break;
		case 7:
			rpHint = "����ڲ��н�";
			break;
		case 8:
			rpHint = "�յ���Χû�е�·";
			break;
		case 9:
			rpHint = "�յ��ڲ��н�";
			break;
		case 10:
			rpHint = ";������Χû�е�·";
			break;
		case 11:
			rpHint = ";�����ڲ��н�";
			break;
			
		default:
			rpHint = "δ֪����";
		}
		
		mTbtNavi.PostText(rpHint, 1);
*/		
	}

	/********************************************************************
	�������ƣ�tmcUpdate
	���ܽ��ܣ�֪ͨ��̬��Ϣ�ı�
	�� �� ֵ����
	˵    ������̬���º���Ҫ���¹�����
    ********************************************************************/
	public  void tmcUpdate() 
	{
		int n = 0;
		n++;
	}

	/********************************************************************
	�������ƣ�showTrafficPanel
	���ܽ��ܣ���ʾ�鱨��
	�� �� ֵ����
	˵    ������
	��    �ߣ�������	xinyan.yu@autonavi.com
    ********************************************************************/
	public  void showTrafficPanel(
		int iSize,
		byte[] panelBuf			// [I] �鱨������
		) 
	{
		String s = "Traffic Panel size" + iSize;
		System.out.println(s);
		
		mTbtNavi.showTrafficPanel(iSize, panelBuf);
		
		int i=0;
		i++;
	}

	/********************************************************************
	�������ƣ�hideTrafficPanel
	���ܽ��ܣ��ر��鱨��
	�� �� ֵ����
	˵    ������
	��    �ߣ�������	xinyan.yu@autonavi.com
    ********************************************************************/
	public void hideTrafficPanel() {
		mTbtNavi.hideTrafficPanel();
	}
}
