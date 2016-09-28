package com.phicomm.android.driversurvey;

import java.util.Calendar;
import java.util.Date;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class FirstSurveyFragment extends Fragment {

	private Question mQuestion;
	private RadioButton radioButton0;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioButton radioButton3;
	private String carId;
	private String driverName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		carId = getActivity().getIntent().getStringExtra("carId");
		driverName = getActivity().getIntent().getStringExtra("driverName");
		mQuestion = Question.get(getActivity());
		//System.out.println(carId);
		//System.out.println(driverName);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_first_survey, container,
				false);

		radioButton0 = (RadioButton) view
				.findViewById(R.id.radioButton_survey_last_excellent);
		radioButton1 = (RadioButton) view
				.findViewById(R.id.radioButton_survey_last_good);
		radioButton2 = (RadioButton) view
				.findViewById(R.id.radioButton_survey_last_bad);
		radioButton3 = (RadioButton) view
				.findViewById(R.id.radioButton_survey_last_poor);

		radioButton0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// System.out.println("RadioButton1 onclick");

				// System.out.println(SurveyActivity.index);
				mQuestion.getResults().add(0);
				// updateDatabase();
				Intent intent = new Intent(getActivity(),
						MainMenuActivity.class);
				intent.putExtra("carId", carId);
				intent.putExtra("driverName", driverName);
				startActivity(intent);
			}

		});

		radioButton1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mQuestion.getResults().add(1);
				SurveyActivity.index++;
				callSurveyFragment();
			}
		});

		radioButton2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mQuestion.getResults().add(2);
				SurveyActivity.index++;
				callSurveyFragment();
			}
		});

		radioButton3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				mQuestion.getResults().add(3);
				// updateDatabase();
				//summitData();
				//activityFinish();
				SurveyActivity.index++;
				callSurveyFragment();
			}

		});
		return view;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		mQuestion.refresh();
//		SurveyActivity.index = 0;
	}

	private void callSurveyFragment() {
		Fragment fragment = new SurveyFragment();
		// Question:why this getFragmentManager() work?
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(android.R.id.content, fragment);
		ft.commit();
	}	
	
	private void summitData() {
		if (mQuestion.getResults().get(0).toString().equals("0")) {
			MainMenuActivity.answer1++;
		} 
		
		if (mQuestion.getResults().get(1).toString().equals("0")) {
			MainMenuActivity.answer2++;
		}
		
		if (mQuestion.getResults().get(2).toString().equals("0")) {
			MainMenuActivity.answer3++;
		} 
		
		if (mQuestion.getResults().get(3).toString().equals("0")) {
			MainMenuActivity.answer4++;
		} 
		
		if (mQuestion.getResults().get(4).toString().equals("0")) {
			MainMenuActivity.answer5++;
		}
		
		if (mQuestion.getResults().get(5).toString().equals("0")) {
			MainMenuActivity.answer6++;
		} 
		
		if (mQuestion.getResults().get(6).toString().equals("0")) {
			MainMenuActivity.answer7++;
		}
		
		if (mQuestion.getResults().get(7).toString().equals("0")) {
			MainMenuActivity.answer8++;
		}
		
		if (mQuestion.getResults().get(8).toString().equals("0")) {
			MainMenuActivity.answer9++;
		}

		if (mQuestion.getResults().get(9).toString().equals("0")) {
			MainMenuActivity.answer10++;
		} 
	}


	private void activityFinish() {
		// System.out.println(mQuestion.getResults());
		// SurveyActivity.index = 0;
		// System.out.println(mQuestion.getResults());
		Toast.makeText(getActivity(), "此问卷已保存,谢谢！", Toast.LENGTH_LONG).show();
		updateDatabase();
		mQuestion.refresh();
		SurveyActivity.index = 0;
		getActivity().finish();
	}

	private void updateDatabase() {

		SurveyDao surveyDao = new SurveyDao(getActivity());
		// use Calendar
		// Date date = new Date();
		// int year = date.getYear();
		// int month = date.getMonth();
		// int day = date.getDay();

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		mQuestion = Question.get(getActivity());

		String dateString = "" + year + "-" + month + "-" + day;
		// String answer1 = mQuestion.getResults().get(0).toString();
		// String answer2 = mQuestion.getResults().get(1).toString();
		// String answer3 = mQuestion.getResults().get(2).toString();
		// String answer4 = mQuestion.getResults().get(3).toString();
		// String answer5 = mQuestion.getResults().get(4).toString();
		// String answer6 = mQuestion.getResults().get(5).toString();
		// String answer7 = mQuestion.getResults().get(6).toString();
		// String answer8 = mQuestion.getResults().get(7).toString();
		// String answer9 = mQuestion.getResults().get(8).toString();
		// String answer10 = mQuestion.getResults().get(9).toString();

		if ((MainMenuActivity.answer1 ) == 1) {
			System.out.println("i need to add");
			surveyDao.add(dateString, carId, driverName,
					MainMenuActivity.answer1 + "", MainMenuActivity.answer2 + "",
					MainMenuActivity.answer3 + "",
					MainMenuActivity.answer4 + "", MainMenuActivity.answer5 + "", MainMenuActivity.answer6 + "",
					MainMenuActivity.answer7 + "", MainMenuActivity.answer8 + "", MainMenuActivity.answer9 + "",
					MainMenuActivity.answer10 + "");
		} else {
			System.out.println("i need to update");
			surveyDao.update(carId, MainMenuActivity.answer1 + "", MainMenuActivity.answer2 + "",
					MainMenuActivity.answer3 + "",
					MainMenuActivity.answer4 + "", MainMenuActivity.answer5 + "", MainMenuActivity.answer6 + "",
					MainMenuActivity.answer7 + "", MainMenuActivity.answer8 + "", MainMenuActivity.answer9 + "",
					MainMenuActivity.answer10 + "");
		}

	}

}
