package heu.ice.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int version = 1; // 数据库版本

	public DatabaseHelper(Context context, String name) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("Creat new DB");
		String sql = "create table student(id int not null UNIQUE,name varchar(20),grade varchar(20))";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	// 插入记录
	public boolean insert(SQLiteDatabase db, Object... args) {

		Cursor cursor = db.rawQuery("select * from student where id='" + args[0] + "'", null);
		if (cursor.moveToNext()) {
			return false;
		} else {

			String sql = "insert into student values";
			StringBuilder sb = new StringBuilder("(");
			for (int i = 0; i < args.length; i++) {
				sb.append("'" + args[i] + "',");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");

			db.execSQL(sql + sb.toString());
			return true;
		}

	}

	// 删除记录
	public boolean delete(SQLiteDatabase db, int id) {

		Cursor cursor = db.rawQuery("select * from student where id='" + id + "'", null);
		if (cursor.moveToNext()) {
			String sql = "delete from student where id='" + id + "'";
			db.execSQL(sql);
			return true;
		} else {
			return false;
		}

	}

	// 更新记录 id key valuse key value ...
	public boolean update(SQLiteDatabase db, Object... args) {

		Cursor cursor = db.rawQuery("select * from student where id='" + args[0] + "'", null);
		if (cursor.moveToNext()) {
			String sql = "update student set ";

			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length - 1; i++) {
				sb.append(args[i] + "='" + args[++i] + "',");
			}
			sb.delete(sb.length() - 1, sb.length());

			db.execSQL(sql + sb.toString() + "where id='" + args[0] + "'");

			return true;
		} else {
			return false;
		}

	}

	public Cursor select(SQLiteDatabase db, int id) {
		String sql = "select * from student where id='" + id + "'";
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
	}
	public Cursor selectAll(SQLiteDatabase db) {
		String sql = "select  id as _id, name, grade from student ";
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
	}

}
