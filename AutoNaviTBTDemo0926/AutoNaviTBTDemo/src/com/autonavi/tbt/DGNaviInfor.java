package com.autonavi.tbt;

	/********************************************************************
	������ת��ͼ�궨�����£�
	1			// �Գ�ͼ��
	2			// ��תͼ��
	3			// ��תͼ��
	4			// ��ǰ��ͼ��
	5			// ��ǰ��ͼ��
	6			// ���ͼ��
	7			// �Һ�ͼ��
	8			// ��ת��ͷͼ��
	9			// ֱ��ͼ��
	10		    // ����;����ͼ��
	11		    // ���뻷��ͼ��
	12		    // ʻ������ͼ��
	13		    // ���������ͼ��
	14		    // �����շ�վͼ��
	15		    // ����Ŀ�ĵ�ͼ��
	16		    // �������ͼ��
	********************************************************************/
public class DGNaviInfor {
	public int m_Type;         		    // ��������,1 GPS��������,2 ģ�⵼������
	public String m_CurRoadName;	    // ��ǰ��·����
	public String m_NextRoadName;	    // ������·����
	public int m_SAPADist;				// ��������������ľ��루��λ�ף�����Ϊ-1��˵��������Ч��û�з�����
	public int m_CameraDist;			// ������������۾��루��λ�ף�����Ϊ-1��˵��������Ч��û�е�����
	public int m_CameraType;			// ���������ͣ�0 ��������ͷ��1Ϊ�������ͷ
	public int m_CameraSpeed;			// ���������٣�����������Ϣ��Ϊ0
	public int m_Icon;         		    // ������ת��ͼ�꣬���϶���
	public int m_RouteRemainDis;	    // ·��ʣ����루��λ�ף�
	public int m_RouteRemainTime;	    // ·��ʣ��ʱ�䣨��λ�룩
	public int m_SegRemainDis;		    // ��ǰ������ʣ����루��λ�ף�
	public int m_SegRemainTime;		    // ��ǰ������ʣ��ʱ�䣨��λ�룩
	public int m_CarDirection;		    // �Գ����򣨵�λ�ȣ���������Ϊ��׼��˳ʱ������
	public double m_Longitude;		    // �Գ�����
	public double m_Latitude;			// �Գ�γ��
	public int m_LimitedSpeed;		    // ��ǰ��·�ٶ����ƣ���λ����ÿСʱ��
	public int m_CurSegNum;   		    // ��ǰ�Գ�����segment�Σ���0��ʼ
	public int m_CurLinkNum;			// ��ǰ�Գ�����Link����0��ʼ
	public int m_CurPointNum;			// ��ǰλ�õ�ǰһ����״��ţ���0��ʼ
}