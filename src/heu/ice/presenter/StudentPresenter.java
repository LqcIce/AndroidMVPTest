package heu.ice.presenter;

import android.content.Context;
import android.database.Cursor;
import heu.ice.androidmvptest.IStudentView;
import heu.ice.bean.StudentBean;
import heu.ice.model.IStudentModel;
import heu.ice.model.StudentModel;

public class StudentPresenter {

	private IStudentView mStudentView;
	private IStudentModel mStudentModel;

	public StudentPresenter(IStudentView mStudentView, Context context) {
		super();
		this.mStudentView = mStudentView;
		this.mStudentModel = new StudentModel(context);
	}

	public boolean insertStudent(int id, String name, String grade) {

		mStudentModel.setID(id);
		mStudentModel.setName(name);
		mStudentModel.setGrade(grade);
		return mStudentModel.insert();

	}

	public boolean deleteStudent(int id) {

		mStudentModel.setID(id);
		return mStudentModel.delete();

	}

	public boolean updateStudent(int id, String name, String grade) {

		mStudentModel.setID(id);
		mStudentModel.setName(name);
		mStudentModel.setGrade(grade);
		return mStudentModel.update();

	}

	public boolean selectStudent(int id) {
		StudentBean mStudentBean = mStudentModel.select(id);
		if (mStudentBean != null) {
			mStudentView.setName(mStudentBean.getName());
			mStudentView.setGrade(mStudentBean.getGrade());
			return true;
		} else
			return false;
	}

	public Cursor loadAllStudent() {
		return mStudentModel.load();
	}

}
