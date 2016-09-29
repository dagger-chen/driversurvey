package com.phicomm.android.driversurvey;

import java.util.ArrayList;
import java.util.Calendar;

//import com.phicomm.library.phidatetimenumberpicker.PhiDatePicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MonthSumInputActivity extends Activity {

	public static final String MONTH_INPUT_DRIVER_NAME = "monthInputDriverName";
	public static final String MONTH_INPUT_ID = "monthInputId";
	public static final String MONTH_INPUT_DATE = "monthInputDate";

	private Button buttonDone;
	private EditText editTextDriverName;
	private EditText editTextId;
	private EditText editTextMonth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_month_sum_input);

		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setTitle("输入查询信息");

		buttonDone = (Button) findViewById(R.id.button_month_sum_input_done);
		editTextDriverName = (EditText) findViewById(R.id.editText_month_sum_input_driver);
		editTextId = (EditText) findViewById(R.id.editText_month_sum_input_id);
		editTextMonth = (EditText) findViewById(R.id.editText_month_sum_input_month);

		Calendar calendar = Calendar.getInstance();
		final int year = calendar.get(Calendar.YEAR);
		final int monthOfYear = calendar.get(Calendar.MONTH);
		final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

		editTextMonth
				.setOnFocusChangeListener(new View.OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
//							System.out.println("EditText On Click");
//							new DatePickerDialog(MonthSumInputActivity.this,
//									new DatePickerDialog.OnDateSetListener() {
// 
//										@Override
//										public void onDateSet(DatePicker view,
//												int year, int monthOfYear,
//												int dayOfMonth) {
//											int monthOfYear1 = monthOfYear + 1;
//											editTextMonth.setText(year + "-"
//													+ monthOfYear1 + "-"
//													+ dayOfMonth);
//										}
//									}, year, monthOfYear, dayOfMonth).show();
							
							//引用libary工程里的自定义PhiDatePicker控件
							final DatePicker datePicker = new DatePicker(MonthSumInputActivity.this);
							AlertDialog.Builder builder = new AlertDialog.Builder(MonthSumInputActivity.this);
							builder.setView(datePicker);
							builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									editTextMonth.setText(Integer.toString(datePicker.getMonth()));
								}
							});
							builder.show();
						}
					}
				});

//		editTextMonth.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				System.out.println("EditText On Click");
//				new DatePickerDialog(MonthSumInputActivity.this,
//						new DatePickerDialog.OnDateSetListener() {
//
//							@Override
//							public void onDateSet(DatePicker view, int year,
//									int monthOfYear, int dayOfMonth) {
//								int monthOfYear1 = monthOfYear + 1;
//								editTextMonth.setText(year + "-" + monthOfYear1
//										+ "-" + dayOfMonth);
//							}
//						}, year, monthOfYear, dayOfMonth).show();
//			}
//		});

		buttonDone.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ArrayList<Record> records = new ArrayList<Record>();
				SurveyDao dao = new SurveyDao(MonthSumInputActivity.this);

				String driverName = editTextDriverName.getText().toString();
				String carId = editTextId.getText().toString();
				String month = editTextMonth.getText().toString();

				if (driverName.equals("") && carId.equals("")
						&& month.equals("")) {
					Toast.makeText(MonthSumInputActivity.this, "内容不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				} else if (driverName.equals("") && carId.equals("")) {
					Toast.makeText(MonthSumInputActivity.this, "内容不全",
							Toast.LENGTH_SHORT).show();
					return;
				} else if (!carId.equals("")) {
					records = (ArrayList<Record>) dao.findById(carId);
					if (records.size() == 0) {
						Toast.makeText(MonthSumInputActivity.this, "该单不存在",
								Toast.LENGTH_SHORT).show();
						return;
					}
				} else if ((!driverName.equals("")) && (!month.equals(""))) {
					// 这个月是否有信息
					boolean monthCorrect = false;
					records = (ArrayList<Record>) dao
							.findByDriverName(driverName);
					for (Record record : records) {
						String[] monthInput = month.split("-");
						String[] monthDatabase = record.getDate().split("-");
						if ((monthInput[0].equals(monthDatabase[0]))
								&& monthInput[1].equals(monthDatabase[1])) {
							monthCorrect = true;
						}
					}
					if (monthCorrect == false) {
						Toast.makeText(MonthSumInputActivity.this, "该司机该月无记录",
								Toast.LENGTH_SHORT).show();
						return;
					}
				} else if (!driverName.equals("")) {

					records = (ArrayList<Record>) dao
							.findByDriverName(driverName);
					if (records.size() == 0) {
						Toast.makeText(MonthSumInputActivity.this, "姓名不存在",
								Toast.LENGTH_SHORT).show();
						return;
					}

				}

				Intent intent = new Intent(MonthSumInputActivity.this,
						MonthSumDisplayActivity.class);
				intent.putExtra(MONTH_INPUT_DRIVER_NAME, editTextDriverName
						.getText().toString());
				intent.putExtra(MONTH_INPUT_ID, editTextId.getText().toString());
				intent.putExtra(MONTH_INPUT_DATE, editTextMonth.getText()
						.toString());
				startActivity(intent);
			}
		});

	}

}
