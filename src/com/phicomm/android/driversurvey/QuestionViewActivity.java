package com.phicomm.android.driversurvey;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionViewActivity extends Activity {
	
//	public static final int MAIN_TO_NEW = 0;
//	public static final int MAIN_TO_SUMMARIZE = 1;
	
	private CheckBox mQuestion2;
	private CheckBox mQuestion3;
	private CheckBox mQuestion4;
	private CheckBox mQuestion5;
	private CheckBox mQuestion6;
	private CheckBox mQuestion7;
	private Button mQuestionComfirm;
    private EditText questionComment;
	private String driverName;
	private String carId;
	private Question mQuestion;
	private String[] answer = new String[]{"否","否","否","否","否"};
//	private string answer2;
//	private string answer3;
//	private string answer4;
//	private string answer5;
//	private string answer6;
//	private string answer7;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionview);
		mQuestion2 = (CheckBox)this.findViewById(R.id.question2);
		mQuestion3 = (CheckBox)this.findViewById(R.id.question3);
		mQuestion4 = (CheckBox)this.findViewById(R.id.question4);
		mQuestion5 = (CheckBox)this.findViewById(R.id.question5);
		mQuestion6 = (CheckBox)this.findViewById(R.id.question6);
		mQuestion7 = (CheckBox)this.findViewById(R.id.question7);
		questionComment = (EditText)this.findViewById(R.id.questioncomment);
		questionComment.setVisibility(View.GONE);
		mQuestionComfirm = (Button)this.findViewById(R.id.button_question_comfirm);
		
		carId = getIntent().getStringExtra("carId");
		driverName = getIntent().getStringExtra("driverName");
		mQuestion = Question.get(this);
		
		mQuestion2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					answer[0] = "是";
				}
				else{
					answer[0] = "否";
				}
			}
		});
		
		mQuestion3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					answer[1] = "是";
				}
				else{
					answer[1] = "否";
				}
			}
		});
		
		mQuestion4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					answer[2] = "是";
				}
				else{
					answer[2] = "否";
				}
			}
		});
		
		mQuestion5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					answer[3] = "是";
				}
				else{
					answer[3] = "否";
				}
			}
		});
		
		mQuestion6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					answer[4] = "是";
				}
				else{
					answer[4] = "否";
				}
			}
		});
		
		mQuestion7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					questionComment.setVisibility(View.VISIBLE);
				}
				else{
					questionComment.setVisibility(View.GONE);
				}
			}
		});

		

		mQuestionComfirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (questionComment.getText().toString().length() > 40){
					Toast.makeText(QuestionViewActivity.this, "你输入的汉字数超过40个了，请重新输入", Toast.LENGTH_LONG).show();
					return;
				}
				
				for(int i = 0; i<5; i++){
					mQuestion.getResults().add(answer[i]);
					SurveyActivity.index++;
					
				}
				
				mQuestion.getResults().add(questionComment.getText().toString());
				SurveyActivity.index++;
				
				QuestionViewActivity.this.finish();
				Intent intent = new Intent(QuestionViewActivity.this,
						MainMenuActivity.class);
				intent.putExtra("carId", carId);
				intent.putExtra("driverName", driverName);
				startActivity(intent);
					
			}
		});

		
	}
	
	
}
