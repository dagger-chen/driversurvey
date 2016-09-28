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
		db.execSQL("create table survey (id integer primary key autoincrement, date varchar(10), carId varchar(10), driverName varchar(10), answer1 varchar(10), answer2 varchar(10), answer3 varchar(10), answer4 varchar(10), answer5 varchar(10), answer6 varchar(10), answer7 varchar(10), answer8 varchar(10), answer9 varchar(10), answer10 varchar(10))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
