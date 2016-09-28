package com.phicomm.android.driversurvey;

import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyFragment extends Fragment {

	private int index;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private TextView mTextView;
	private Question mQuestion;
	private String carId;
	private String driverName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_survey, container, false);
		radioButton1 = (RadioButton) view
				.findViewById(R.id.radioButton_survey_yes);
		radioButton2 = (RadioButton) view
				.findViewById(R.id.radioButton_survey_no);
		mTextView = (TextView) view.findViewById(R.id.textView_survey_question);
		carId = getActivity().getIntent().getStringExtra("carId");
		driverName = getActivity().getIntent().getStringExtra("driverName");

		// System.out.println(SurveyActivity.index);
		setText();

		radioButton1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				radioButton1.setChecked(false);
				radioButton2.setChecked(false);
				// System.out.println("RadioButton1 onclick");
				// System.out.println(SurveyActivity.index);
				mQuestion = Question.get(getActivity());
				mQuestion.getResults().add(1);
				// Fragment fragment = new MainFragment();
				// //Question:why this getFragmentManager() work?
				// FragmentManager fm = getFragmentManager();
				// FragmentTransaction ft = fm.beginTransaction();
				// ft.replace(android.R.id.content, fragment);
				// ft.commit();
				if (SurveyActivity.index == 9) {
					getActivity().finish();
					Intent intent = new Intent(getActivity(),
							MainMenuActivity.class);
					intent.putExtra("carId", carId);
					intent.putExtra("driverName", driverName);
					startActivity(intent);
				} else {
					SurveyActivity.index++;
				}
				setText();

			}
		});

		radioButton2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				radioButton1.setChecked(false);
				radioButton2.setChecked(false);
				// System.out.println("RadioButton1 onclick");
				// System.out.println(SurveyActivity.index);
				mQuestion = Question.get(getActivity());
				mQuestion.getResults().add(0);
				if (SurveyActivity.index == 9) {
					getActivity().finish();
					Intent intent = new Intent(getActivity(),
							MainMenuActivity.class);
					intent.putExtra("carId", carId);
					intent.putExtra("driverName", driverName);
					startActivity(intent);
				} else {
					SurveyActivity.index++;
				}
				setText();
			}
		});

		return view;
	}

	private void setText() {
		//System.out.println(SurveyActivity.index);
		switch (SurveyActivity.index) {
		case 0:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_ONE);
			break;
		case 1:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_TWO);
			break;
		case 2:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_THREE);
			break;
		case 3:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_FOUR);
			break;
		case 4:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_FIVE);
			break;
		case 5:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_SIX);
			break;
		case 6:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_SEVEN);
			break;
		case 7:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_EIGHT);
			break;
		case 8:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_NINE);
		case 9:
			mTextView.setText((SurveyActivity.index+1)+"."+Question.QUESTION_TEN);

			// showResults();
			break;

		default:
			break;
		}
	}


	private void showResults() {
		System.out.println(mQuestion.getResults());
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
