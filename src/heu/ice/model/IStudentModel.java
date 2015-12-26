package heu.ice.model;

import android.database.Cursor;
import heu.ice.bean.StudentBean;

public interface IStudentModel {

	void setID(int id);

	void setName(String name);

	void setGrade(String grade);

	boolean insert();

	boolean delete();

	boolean update();

	StudentBean select(int id);

	Cursor load();
}
