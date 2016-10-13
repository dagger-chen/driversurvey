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
		textViewAnswer1.setText("˾��������ʮͳ�ƽ��Ϊ�������⡱"
				+ MainMenuActivity.answer1 + "�Σ��������⡱"
				+ MainMenuActivity.answer1 + "��");
		textViewAnswer2.setText("˾���������ͳ�ƽ��Ϊ�����ǡ�" + MainMenuActivity.answer2
				+ "�Σ�����" + MainMenuActivity.answer2 + "��");
		textViewAnswer3.setText("˾����������ͳ�ƽ��Ϊ�����ǡ�" + MainMenuActivity.answer3
				+ "�Σ�����" + MainMenuActivity.answer3 + "��");
		textViewAnswer4.setText("˾����������ͳ�ƽ��Ϊ�����ǡ�" + MainMenuActivity.answer4
				+ "�Σ�����" + MainMenuActivity.answer4 + "��");
		textViewAnswer5.setText("˾����������ͳ�ƽ��Ϊ�����ǡ�" + MainMenuActivity.answer5
				+ "�Σ�����" + MainMenuActivity.answer5 + "��");
		textViewAnswer6.setText("˾����������ͳ�ƽ��Ϊ�����ǡ�" + MainMenuActivity.answer6
				+ "�Σ�����" + MainMenuActivity.answer6 + "��");
		textViewAnswer7.setText("˾����������ͳ�ƽ��Ϊ�����ǡ�" + MainMenuActivity.answer7
				+ "�Σ�����" + MainMenuActivity.answer7 + "��");
	}


}
