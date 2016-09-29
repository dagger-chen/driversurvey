package com.phicomm.android.driversurvey;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputNameAndIdFragment extends Fragment {

	private EditText carEditText;
	private EditText driverEditText;
	private Button startButton;
	private String driverName;
	private String carId;
	private Question mQuestion;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_input_name_id,
				container, false);
		carEditText = (EditText) view.findViewById(R.id.editText_car_id);
		driverEditText = (EditText) view
				.findViewById(R.id.editText_driver_name);

		Drawable drawableName = getResources().getDrawable(
				R.drawable.accont_number);
		Drawable drawableCarId = getResources().getDrawable(R.drawable.icon_id);
		drawableCarId.setBounds(0, 0, 74, 74);
		drawableName.setBounds(0,0,70,70);
		
		
		carEditText.setCompoundDrawables(drawableCarId, null, null, null);
		driverEditText.setCompoundDrawables(drawableName, null, null, null);

		carEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				carId = s.toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		driverEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				driverName = s.toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		startButton = (Button) view.findViewById(R.id.button_start_survey);
		startButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (carEditText.getText().toString().equals("")
						|| driverEditText.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "信息不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				} else {
					SurveyDao dao = new SurveyDao(getActivity());
					ArrayList records = (ArrayList) dao.findById(carId);
					if (records.size() != 0) {
						Toast.makeText(getActivity(), "该单已经存在",
								Toast.LENGTH_SHORT).show();
						return;
					}
				}

				Intent intent = new Intent(getActivity(),
						SurveyActivity.class);
				intent.putExtra("carId", carId);
				intent.putExtra("driverName", driverName);
				startActivity(intent);
				startRefresh();
			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main_activity_options, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_maim_check_summarize_upload:
			Intent intent_upload = new Intent(getActivity(),
					MonthSumInputActivity.class);
			startActivity(intent_upload);
			return true;
		case R.id.menu_maim_check_summarize:
			Intent intent = new Intent(getActivity(),
					MonthSumInputActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	private void startRefresh() {
		// System.out.println(mQuestion.getResults());
		// System.out.println("start refresh");
		SurveyActivity.index = 0;
		// System.out.println(SurveyActivity.index);

	}
	
}
