package com.phicomm.android.driversurvey;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SurveyDao {

	private SurveySQLiteOpenHelper helper;

	public SurveyDao(Context context) {
		helper = new SurveySQLiteOpenHelper(context);
	}

	public void add(String date, String carId, String driverName,
			String answer1_1, String answer1_2, String answer2_1,
			String answer2_2, String answer3_1, String answer3_2,
			String answer4_1, String answer4_2, String answer5_1,
			String answer5_2, String answer6_1, String answer6_2,
			String answer7_1, String answer7_2, String answer8_1,
			String answer8_2, String answer9_1, String answer9_2,
			String answer10_1, String answer10_2, String answer10_3,
			String answer10_4) {
		
		System.out.println("SurveyDao add");
		
		SQLiteDatabase db = helper.getWritableDatabase();

		db.execSQL(
				"insert into survey (date,carId,driverName,answer1_1,answer1_2,answer2_1,"
						+ "answer2_2,answer3_1,answer3_2,answer4_1,answer4_2,answer5_1,answer5_2,"
						+ "answer6_1,answer6_2,answer7_1,answer7_2,answer8_1,answer8_2,answer9_1,"
						+ "answer9_2,answer10_1,answer10_2,answer10_3,"
						+ "answer10_4) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { date, carId, driverName, answer1_1, answer1_2,
						answer2_1, answer2_2, answer3_1, answer3_2, answer4_1,
						answer4_2, answer5_1, answer5_2, answer6_1, answer6_2,
						answer7_1, answer7_2, answer8_1, answer8_2, answer9_1,
						answer9_2, answer10_1, answer10_2, answer10_3,
						answer10_4 });

		db.close();
	}

	public void update(String carId, String answer1_1, String answer1_2,
			String answer2_1, String answer2_2, String answer3_1,
			String answer3_2, String answer4_1, String answer4_2,
			String answer5_1, String answer5_2, String answer6_1,
			String answer6_2, String answer7_1, String answer7_2,
			String answer8_1, String answer8_2, String answer9_1,
			String answer9_2, String answer10_1, String answer10_2,
			String answer10_3, String answer10_4) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.execSQL(
				"update survey set answer1_1=?,answer1_2=?,answer2_1=?,answer2_2=?,answer3_1=?,answer3_2=?,answer4_1=?,answer4_2=?,answer5_1=?,answer5_2=?,answer6_1=?,answer6_2=?,answer7_1=?,answer7_2=?,answer8_1=?,answer8_2=?,answer9_1=?,answer9_2=?,answer10_1=?,answer10_2=?,answer10_3=?,answer10_4=? where carId =?",
				new Object[] { answer1_1, answer1_2, answer2_1, answer2_2,
						answer3_1, answer3_2, answer4_1, answer4_2, answer5_1,
						answer5_2, answer6_1, answer6_2, answer7_1, answer7_2,
						answer8_1, answer8_2, answer9_1, answer9_2, answer10_1,
						answer10_2, answer10_3, answer10_4, carId });

		db.close();
	}

	public List<Record> findByDriverName(String driverName) {
		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor cursor = db.rawQuery("select * from survey where driverName=?",
				new String[] { driverName });
		ArrayList<Record> records = (ArrayList<Record>) createRecordsList(cursor);
		db.close();
		return records;
	}

	public List<Record> findById(String carId) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from survey where carId=?",
				new String[] { carId });
		ArrayList<Record> records = (ArrayList<Record>) createRecordsList(cursor);
		db.close();
		return records;
	}

	public String findNameById(String carId){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from survey where carId=?",
				new String[] { carId });
		cursor.moveToFirst();
		String driverName = cursor.getString(cursor.getColumnIndex("driverName"));
		db.close();
		return driverName;
	}
	
	public List<Record> createRecordsList(Cursor cursor) {
		List<Record> records = new ArrayList<Record>();
		while (cursor.moveToNext()) {
			String date = cursor.getString(cursor.getColumnIndex("date"));
			String answer1_1 = cursor.getString(cursor
					.getColumnIndex("answer1_1"));
			String answer1_2 = cursor.getString(cursor
					.getColumnIndex("answer1_2"));
			String answer2_1 = cursor.getString(cursor
					.getColumnIndex("answer2_1"));
			String answer2_2 = cursor.getString(cursor
					.getColumnIndex("answer2_2"));
			String answer3_1 = cursor.getString(cursor
					.getColumnIndex("answer3_1"));
			String answer3_2 = cursor.getString(cursor
					.getColumnIndex("answer3_2"));
			String answer4_1 = cursor.getString(cursor
					.getColumnIndex("answer4_1"));
			String answer4_2 = cursor.getString(cursor
					.getColumnIndex("answer4_2"));
			String answer5_1 = cursor.getString(cursor
					.getColumnIndex("answer5_1"));
			String answer5_2 = cursor.getString(cursor
					.getColumnIndex("answer5_2"));
			String answer6_1 = cursor.getString(cursor
					.getColumnIndex("answer6_1"));
			String answer6_2 = cursor.getString(cursor
					.getColumnIndex("answer6_2"));
			String answer7_1 = cursor.getString(cursor
					.getColumnIndex("answer7_1"));
			String answer7_2 = cursor.getString(cursor
					.getColumnIndex("answer7_2"));
			String answer8_1 = cursor.getString(cursor
					.getColumnIndex("answer8_1"));
			String answer8_2 = cursor.getString(cursor
					.getColumnIndex("answer8_2"));
			String answer9_1 = cursor.getString(cursor
					.getColumnIndex("answer9_1"));
			String answer9_2 = cursor.getString(cursor
					.getColumnIndex("answer9_2"));
			String answer10_1 = cursor.getString(cursor
					.getColumnIndex("answer10_1"));
			String answer10_2 = cursor.getString(cursor
					.getColumnIndex("answer10_2"));
			String answer10_3 = cursor.getString(cursor
					.getColumnIndex("answer10_3"));
			String answer10_4 = cursor.getString(cursor
					.getColumnIndex("answer10_4"));
			Record record = new Record(date, answer1_1, answer1_2, answer2_1,
					answer2_2, answer3_1, answer3_2, answer4_1, answer4_2,
					answer5_1, answer5_2, answer6_1, answer6_2, answer7_1,
					answer7_2, answer8_1, answer8_2, answer9_1, answer9_2,
					answer10_1, answer10_2, answer10_3, answer10_4);
			records.add(record);
		}
		return records;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		helper.close();
	}

}
