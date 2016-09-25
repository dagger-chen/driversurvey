package com.phicomm.android.driversurvey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

public class SruveyFragment extends Fragment {

	private int index;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private TextView mTextView;
	private Question mQuestion;

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
				if (SurveyActivity.index == 8) {
					callLastFragment();
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
				if (SurveyActivity.index == 8) {
					callLastFragment();
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

			// showResults();
			break;

		default:
			break;
		}
	}

	private void callLastFragment() {
		Fragment fragment = new LastSurveyFragment();
		// Question:why this getFragmentManager() work?
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(android.R.id.content, fragment);
		ft.commit();
	}

	private void showResults() {
		System.out.println(mQuestion.getResults());
	}

}
