package heu.ice.model;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import heu.ice.androidmvptest.IStudentView;
import heu.ice.bean.StudentBean;
import heu.ice.helper.DatabaseHelper;

public class StudentModel implements IStudentModel {

	private static final String DB_NAME = "mydata.db"; // Êý¾Ý¿âÃû³Æ
	private String mName;
	private String mGrade;
	private int mId;
	private DatabaseHelper dbHelper;
	SQLiteDatabase db;

	public StudentModel(Context context) {
		super();
		// TODO Auto-generated constructor stub
		dbHelper = new DatabaseHelper(context, DB_NAME);
		db = dbHelper.getReadableDatabase();
	}

	@Override
	public void setID(int id) {
		// TODO Auto-generated method stub
		mId = id;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		mName = name;
	}

	@Override
	public void setGrade(String grade) {
		// TODO Auto-generated method stub
		mGrade = grade;
	}

	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		boolean success = dbHelper.insert(db, mId, mName, mGrade);
		if (!success) {
			System.out.println("Is Already There");
		}
		return success;
	}

	@Override
	public StudentBean select(int id) {
		// TODO Auto-generated method stub
		String name = null;
		String grade = null;
		Cursor cursor = dbHelper.select(db, id);
		if (cursor.moveToNext()) {
			name = cursor.getString(cursor.getColumnIndex("name"));
			grade = cursor.getString(cursor.getColumnIndex("grade"));
			StudentBean mStudentBean = new StudentBean(id, name, grade);
			return mStudentBean;
		} else {
			System.out.println("Not find");
			return null;
		}

	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		boolean success = dbHelper.delete(db, mId);
		if (!success) {
			System.out.println("Is Not There");
		}
		return success;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		boolean success = dbHelper.update(db, mId, "name", mName, "grade", mGrade);
		if (!success) {
			System.out.println("Is Not There");
		}
		return success;
	}

	@Override
	public Cursor load() {
		// TODO Auto-generated method stub
		Cursor cursor = dbHelper.selectAll(db);
		return cursor;
	}

}
