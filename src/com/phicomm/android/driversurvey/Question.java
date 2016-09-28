package com.phicomm.android.driversurvey;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;

public class Question {
//	public static final String QUESTION_ONE = "驾驶员是否在出车前提前与您确认用车信息？";
//	public static final String QUESTION_TWO = "车辆是否提前抵到预定发车地点？";
//	public static final String QUESTION_THREE = "驾驶员是否在发车前再次与您确认行程？";
//	public static final String QUESTION_FOUR = "出车前驾驶员是否提醒您系好安全带？";
//	public static final String QUESTION_FIVE = "上车时驾驶员是否主动问候说\"您好\",下车道别说\"再见\"？";
//	public static final String QUESTION_SIX = "驾驶员是否在行驶途中拨打电话？行驶途中驾驶员是否使用蓝牙耳机接听来电？  ";
//	public static final String QUESTION_SEVEN = "您乘坐的车辆是否干净整洁？（非硬件损伤除外）";
//	public static final String QUESTION_EIGHT = "在驾驶途中驾驶员是否和您发牢骚和怨言？ ";
//	public static final String QUESTION_NINE = "在您需要的时候驾驶员是否主动为您提供搬运行李或打伞服务？";
//	public static final String QUESTION_TEN = "您对驾驶员的综合评价？";
	
	public static final String QUESTION_ONE = "您对驾驶员的综合评价？";
	public static final String QUESTION_TWO = "驾驶员是否在出车前提前与您确认用车信息？";
	public static final String QUESTION_THREE = "车辆是否提前抵到预定发车地点？";
	public static final String QUESTION_FOUR = "驾驶员是否在发车前再次与您确认行程？";
	public static final String QUESTION_FIVE = "出车前驾驶员是否提醒您系好安全带？";
	public static final String QUESTION_SIX = " 上车时驾驶员是否主动问候说\"您好\",下车道别说\"再见\"？";
	public static final String QUESTION_SEVEN = "驾驶员是否在行驶途中拨打电话？行驶途中驾驶员是否使用蓝牙耳机接听来电？ ";
	public static final String QUESTION_EIGHT = "您乘坐的车辆是否干净整洁？（非硬件损伤除外） ";
	public static final String QUESTION_NINE = "在驾驶途中驾驶员是否和您发牢骚和怨言？";
	public static final String QUESTION_TEN = "在您需要的时候驾驶员是否主动为您提供搬运行李或打伞服务？";

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
