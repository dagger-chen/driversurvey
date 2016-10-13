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
			String answer1, String answer2, String answer3,
			String answer4, String answer5, String answer6,
			String answer7) {
		
		System.out.println("SurveyDao add");
		
		SQLiteDatabase db = helper.getWritableDatabase();

		db.execSQL(
				"insert into survey (date,carId,driverName,answer1,answer2,"
						+ "answer3,answer4,answer5,"
						+ "answer6,answer7"
						+ ") values (?,?,?,?,?,?,?,?,?,?)",
				new Object[] { date, carId, driverName, answer1,
						answer2, answer3, answer4, answer5, answer6,
						answer7});

		db.close();
	}

	public void update(String carId, String answer1,
			String answer2, String answer3, String answer4,
			String answer5, String answer6, String answer7) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.execSQL(
				"update survey set answer1=?,answer2=?,answer3=?,answer4=?,answer5=?,answer6=?,answer7=? where carId =?",
				new Object[] { answer1, answer2,
						answer3, answer4, answer5, answer6, answer7, carId });

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
			String answer1 = cursor.getString(cursor
					.getColumnIndex("answer1"));
			String answer2 = cursor.getString(cursor
					.getColumnIndex("answer2"));
			String answer3 = cursor.getString(cursor
					.getColumnIndex("answer3"));
			String answer4 = cursor.getString(cursor
					.getColumnIndex("answer4"));
			String answer5 = cursor.getString(cursor
					.getColumnIndex("answer5"));
			String answer6 = cursor.getString(cursor
					.getColumnIndex("answer6"));
			String answer7 = cursor.getString(cursor
					.getColumnIndex("answer7"));
			Record record = new Record(date, answer1, answer2, answer3, answer4,
					answer5, answer6, answer7);
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
