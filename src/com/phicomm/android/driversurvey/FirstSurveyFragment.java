package com.phicomm.android.driversurvey;

import java.util.Calendar;
import java.util.Date;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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
				.findViewById(R.id.radioButton_survey_last_good);
		radioButton1 = (RadioButton) view
				.findViewById(R.id.radioButton_survey_last_bad);

		radioButton0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// System.out.println("RadioButton1 onclick");

				// System.out.println(SurveyActivity.index);
				mQuestion.getResults().add("ÂúÒâ");
				// updateDatabase();
				getActivity().finish();
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
				mQuestion.getResults().add("²»ÂúÒâ");
				SurveyActivity.index++;
				//getActivity().finish();
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
	

}
