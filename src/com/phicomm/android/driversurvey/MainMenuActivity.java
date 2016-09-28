package com.phicomm.android.driversurvey;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity {

	public static int answer1, answer2, answer3, answer4, answer5, answer6, 
	answer7, answer8, answer9, answer10;

	private Question mQuestion;
	private Button buttonNew;
	private String carId;
	private String driverName;
	private Button buttonDone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu_main);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		getActionBar().setDisplayShowHomeEnabled(false);

		buttonNew = (Button) findViewById(R.id.button_menu_main_survey);
		buttonDone = (Button) findViewById(R.id.button_menu_done);

		carId = getIntent().getStringExtra("carId");
		driverName = getIntent().getStringExtra("driverName");

		buttonNew.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//mQuestion.refresh();
				SurveyActivity.index = 0;
				Intent intent = new Intent(MainMenuActivity.this,
						SurveyActivity.class);
				intent.putExtra("carId", carId);
				intent.putExtra("driverName", driverName);
				startActivity(intent);
			}
		});


		buttonDone.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainMenuActivity.this, "此问卷已保存,谢谢！", Toast.LENGTH_LONG).show();
				updateDatabase();
//				dataRefresh();
				//finish();
				Intent intent = new Intent(MainMenuActivity.this, InputNameAndIdActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dataRefresh();
	}
	
	private void dataRefresh() {
		answer1 = 0;
		answer2 = 0;
		answer3 = 0;
		answer3 = 0;
		answer4 = 0;
		answer5 = 0;
		answer6 = 0;
		answer7 = 0;
		answer8 = 0;
		answer9 = 0;
		answer10 = 0;

	}

	private void updateDatabase() {

		SurveyDao surveyDao = new SurveyDao(this);
		// use Calendar
		// Date date = new Date();
		// int year = date.getYear();
		// int month = date.getMonth();
		// int day = date.getDay();

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		mQuestion = Question.get(this);

		String dateString = "" + year + "-" + month + "-" + day;
		 String answer1 = mQuestion.getResults().get(0).toString();
		 if(answer1 == Integer.toString(0)){
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;
			 mQuestion.getResults().add(1);
			 SurveyActivity.index++;			 
		 }
		 String answer2 = mQuestion.getResults().get(1).toString();
		 String answer3 = mQuestion.getResults().get(2).toString();
		 String answer4 = mQuestion.getResults().get(3).toString();
		 String answer5 = mQuestion.getResults().get(4).toString();
		 String answer6 = mQuestion.getResults().get(5).toString();
		 String answer7 = mQuestion.getResults().get(6).toString();
		 String answer8 = mQuestion.getResults().get(7).toString();
		 String answer9 = mQuestion.getResults().get(8).toString();
		 String answer10 = mQuestion.getResults().get(9).toString();

		surveyDao.add(dateString, carId, driverName, answer1
				+ "", answer2 + "", answer3
				+ "", answer4 + "", answer5
				+ "", answer6 + "", answer7
				+ "", answer8 + "", answer9
				+ "", answer10 + "");

	}
}
