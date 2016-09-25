package com.phicomm.android.driversurvey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SurveySQLiteOpenHelper extends SQLiteOpenHelper {

	public SurveySQLiteOpenHelper(Context context) {
		super(context, "survey.db", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table survey (id integer primary key autoincrement, date varchar(10), carId varchar(10), driverName varchar(10), answer1_1 varchar(10), answer1_2 varchar(10), answer2_1 varchar(10), answer2_2 varchar(10), answer3_1 varchar(10), answer3_2 varchar(10), answer4_1 varchar(10), answer4_2 varchar(10), answer5_1 varchar(10), answer5_2 varchar(10), answer6_1 varchar(10), answer6_2 varchar(10), answer7_1 varchar(10), answer7_2 varchar(10), answer8_1 varchar(10),answer8_2 varchar(10), answer9_1 varchar(10), answer9_2 varchar(10), answer10_1 varchar(10), answer10_2 varchar(10), answer10_3 varchar(10), answer10_4 varchar(10))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
