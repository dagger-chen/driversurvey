package com.phicomm.android.driversurvey;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class SurveyActivity extends Activity {

	public static int index;
	
	private Question mQuestion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setTitle("ÃÓ–¥Œ æÌ");

		mQuestion = Question.get(getApplicationContext());
		mQuestion.refresh();
		
		Fragment fragment = new SruveyFragment();

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(android.R.id.content, fragment);
		ft.commit();

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		SurveyActivity.index = 0;
	}
	
}
