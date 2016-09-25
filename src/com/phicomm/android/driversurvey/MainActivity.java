package com.phicomm.android.driversurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
//	public static final int MAIN_TO_NEW = 0;
//	public static final int MAIN_TO_SUMMARIZE = 1;
	
	private Button buttonNew;
	private Button buttonSummarize;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonNew = (Button) findViewById(R.id.button_main_activity_new);
		//buttonSummarize = (Button) findViewById(R.id.button_main_activity_summarize);
		
		buttonNew.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, InputNameAndIdActivity.class);
				startActivity(intent);
			}
		});
		
//		buttonSummarize.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this, MonthSumInputActivity.class);
//				startActivity(intent);
//			}
//		});
		
	}
	
	
}
