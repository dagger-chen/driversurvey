package com.phicomm.android.driversurvey;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ThisTableSummaryActivity extends Activity {

	private String carId;
	private String driverName;
	private TextView textViewAnswer1, textViewAnswer2, textViewAnswer3,
			textViewAnswer4, textViewAnswer5, textViewAnswer6, textViewAnswer7;

	// private int[][] answerOneToNine = new int[9][2];
	// private int[] answerTen = new int[4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_month_summary);

		carId = getIntent().getStringExtra("carId");
		driverName = getIntent().getStringExtra("driverName");

		SurveyDao dao = new SurveyDao(this);
		//ArrayList<Record> records = (ArrayList<Record>) dao.find(driverName);
		// use array to save records
		// summitRecords(records);
		// System.out.println(records.size());

//		textViewAnswer1 = (TextView) findViewById(R.id.textview_answer1_monthSummary);
//		textViewAnswer2 = (TextView) findViewById(R.id.textview_answer2_monthSummary);
//		textViewAnswer3 = (TextView) findViewById(R.id.textview_answer3_monthSummary);
//		textViewAnswer4 = (TextView) findViewById(R.id.textview_answer4_monthSummary);
//		textViewAnswer5 = (TextView) findViewById(R.id.textview_answer5_monthSummary);
//		textViewAnswer6 = (TextView) findViewById(R.id.textview_answer6_monthSummary);
//		textViewAnswer7 = (TextView) findViewById(R.id.textview_answer7_monthSummary);
//		textViewAnswer8 = (TextView) findViewById(R.id.textview_answer8_monthSummary);
//		textViewAnswer9 = (TextView) findViewById(R.id.textview_answer9_monthSummary);
//		textViewAnswer10 = (TextView) findViewById(R.id.textview_answer10_monthSummary);

		setText();

	}

	private void setText() {
		textViewAnswer1.setText("司机的问题十统计结果为：“满意”"
				+ MainMenuActivity.answer1 + "次，“不满意”"
				+ MainMenuActivity.answer1 + "次");
		textViewAnswer2.setText("司机的问题二统计结果为：“是”" + MainMenuActivity.answer2
				+ "次，“否”" + MainMenuActivity.answer2 + "次");
		textViewAnswer3.setText("司机的问题三统计结果为：“是”" + MainMenuActivity.answer3
				+ "次，“否”" + MainMenuActivity.answer3 + "次");
		textViewAnswer4.setText("司机的问题四统计结果为：“是”" + MainMenuActivity.answer4
				+ "次，“否”" + MainMenuActivity.answer4 + "次");
		textViewAnswer5.setText("司机的问题五统计结果为：“是”" + MainMenuActivity.answer5
				+ "次，“否”" + MainMenuActivity.answer5 + "次");
		textViewAnswer6.setText("司机的问题六统计结果为：“是”" + MainMenuActivity.answer6
				+ "次，“否”" + MainMenuActivity.answer6 + "次");
		textViewAnswer7.setText("司机的问题七统计结果为：“是”" + MainMenuActivity.answer7
				+ "次，“否”" + MainMenuActivity.answer7 + "次");
	}


}
