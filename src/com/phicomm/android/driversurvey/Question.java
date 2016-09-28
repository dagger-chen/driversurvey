package com.phicomm.android.driversurvey;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;

public class Question {
//	public static final String QUESTION_ONE = "��ʻԱ�Ƿ��ڳ���ǰ��ǰ����ȷ���ó���Ϣ��";
//	public static final String QUESTION_TWO = "�����Ƿ���ǰ�ֵ�Ԥ�������ص㣿";
//	public static final String QUESTION_THREE = "��ʻԱ�Ƿ��ڷ���ǰ�ٴ�����ȷ���г̣�";
//	public static final String QUESTION_FOUR = "����ǰ��ʻԱ�Ƿ�������ϵ�ð�ȫ����";
//	public static final String QUESTION_FIVE = "�ϳ�ʱ��ʻԱ�Ƿ������ʺ�˵\"����\",�³�����˵\"�ټ�\"��";
//	public static final String QUESTION_SIX = "��ʻԱ�Ƿ�����ʻ;�в���绰����ʻ;�м�ʻԱ�Ƿ�ʹ�����������������磿  ";
//	public static final String QUESTION_SEVEN = "�������ĳ����Ƿ�ɾ����ࣿ����Ӳ�����˳��⣩";
//	public static final String QUESTION_EIGHT = "�ڼ�ʻ;�м�ʻԱ�Ƿ��������ɧ��Թ�ԣ� ";
//	public static final String QUESTION_NINE = "������Ҫ��ʱ���ʻԱ�Ƿ�����Ϊ���ṩ����������ɡ����";
//	public static final String QUESTION_TEN = "���Լ�ʻԱ���ۺ����ۣ�";
	
	public static final String QUESTION_ONE = "���Լ�ʻԱ���ۺ����ۣ�";
	public static final String QUESTION_TWO = "��ʻԱ�Ƿ��ڳ���ǰ��ǰ����ȷ���ó���Ϣ��";
	public static final String QUESTION_THREE = "�����Ƿ���ǰ�ֵ�Ԥ�������ص㣿";
	public static final String QUESTION_FOUR = "��ʻԱ�Ƿ��ڷ���ǰ�ٴ�����ȷ���г̣�";
	public static final String QUESTION_FIVE = "����ǰ��ʻԱ�Ƿ�������ϵ�ð�ȫ����";
	public static final String QUESTION_SIX = " �ϳ�ʱ��ʻԱ�Ƿ������ʺ�˵\"����\",�³�����˵\"�ټ�\"��";
	public static final String QUESTION_SEVEN = "��ʻԱ�Ƿ�����ʻ;�в���绰����ʻ;�м�ʻԱ�Ƿ�ʹ�����������������磿 ";
	public static final String QUESTION_EIGHT = "�������ĳ����Ƿ�ɾ����ࣿ����Ӳ�����˳��⣩ ";
	public static final String QUESTION_NINE = "�ڼ�ʻ;�м�ʻԱ�Ƿ��������ɧ��Թ�ԣ�";
	public static final String QUESTION_TEN = "������Ҫ��ʱ���ʻԱ�Ƿ�����Ϊ���ṩ����������ɡ����";

	private static Question sQuestion;
	private Context mAppContext;
	private int index;
	private ArrayList<Integer> results;
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	private Question(Context appContext){
		mAppContext = appContext;
		results = new ArrayList<Integer>();
		index = 0;
	}
	
	//singleton pattern
	public static Question get(Context c){
		if(sQuestion == null){
			sQuestion = new Question(c.getApplicationContext());
		}
		return sQuestion;
	}
	
	public void refresh(){
		index = 0;
		results.clear();
	}
	public ArrayList<Integer> getResults() {
		return results;
	}
	public void setResults(ArrayList<Integer> results) {
		this.results = results;
	}
	
}
