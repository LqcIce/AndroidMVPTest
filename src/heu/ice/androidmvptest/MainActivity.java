package heu.ice.androidmvptest;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import heu.ice.presenter.StudentPresenter;

public class MainActivity extends Activity implements IStudentView, OnClickListener {

	private EditText id;
	private EditText mNameEditText;
	private EditText mGradeEditText;
	private StudentPresenter mStudentPresenter;
	private Button mAddButton;
	private Button mSelectButton;
	private Button mDeleteButton;
	private Button mUpdateButton;
	private Button mAllButton;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		id = (EditText) findViewById(R.id.id);
		mNameEditText = (EditText) findViewById(R.id.name);
		mGradeEditText = (EditText) findViewById(R.id.grade);
		mStudentPresenter = new StudentPresenter(this, this);
		mAddButton = (Button) findViewById(R.id.add);
		mSelectButton = (Button) findViewById(R.id.select);
		mDeleteButton = (Button) findViewById(R.id.delete);
		mUpdateButton = (Button) findViewById(R.id.update);
		mAllButton = (Button) findViewById(R.id.all);
		mAddButton.setOnClickListener(this);
		mSelectButton.setOnClickListener(this);
		mDeleteButton.setOnClickListener(this);
		mUpdateButton.setOnClickListener(this);
		mAllButton.setOnClickListener(this);

		mListView = (ListView) findViewById(R.id.listview);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Integer.parseInt(id.getText().toString());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return mNameEditText.getText().toString();
	}

	@Override
	public String getGrade() {
		// TODO Auto-generated method stub
		return mGradeEditText.getText().toString();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		mNameEditText.setText(name);
	}

	@Override
	public void setGrade(String grade) {
		// TODO Auto-generated method stub
		mGradeEditText.setText(grade);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add:
			if (!mStudentPresenter.insertStudent(getID(), getName(), getGrade())) {
				Toast.makeText(this, "Is Already There", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.delete:
			if (!mStudentPresenter.deleteStudent(getID())) {
				Toast.makeText(this, "Is not There", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.update:
			if (!mStudentPresenter.updateStudent(getID(), getName(), getGrade())) {
				Toast.makeText(this, "Is not There", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.select:

			if (!mStudentPresenter.selectStudent(getID())) {
				Toast.makeText(this, "Is not There", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.all:
			Cursor cursor = mStudentPresenter.loadAllStudent();
			CursorAdapter mCAdapter = new SimpleCursorAdapter(this, R.layout.list_item3, cursor,
					new String[] { "_id", "name", "grade" },
					new int[] { R.id.textview1, R.id.textview2, R.id.textview3 },
					CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

			mListView.setAdapter(mCAdapter);
			break;
		default:
			break;
		}
	}
}
