package todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pathfinder.anup.imptodo.R;

import java.util.List;

import calender.CalenderActivity;

/**
 * Created by Anup on 2/8/2017.
 */

public class TodoListActivity extends Activity implements TodoMainView, AdapterView.OnItemClickListener, View.OnClickListener{

    ListView listView;
    ProgressBar progressBar;
    TodoMainViewPresenter todoViewPresenter;
    Button markOnCalender;
    ImageButton btnAdd;
    EditText wishTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_to_do);

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        wishTodo = (EditText) findViewById(R.id.todo_item);
        todoViewPresenter = new TodoMainViewPresenterImpl(this, new TodoMainViewInteractorImpl());

        markOnCalender = (Button) findViewById(R.id.btn_mark_on_calender);
        markOnCalender.setOnClickListener(this);
        btnAdd = (ImageButton) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        todoViewPresenter.onItemClicked(i);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<String> items) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {

       startActivity(new Intent(TodoListActivity.this, CalenderActivity.class));
    }

    @Override
    public void todoItemErr() {
        wishTodo.setError(getString(R.string.todo_adding_error));
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoViewPresenter.onResume(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        todoViewPresenter.onDestroy();
    }

    @Override
    public void onClick(View view) {
        // can pass params here
        switch (view.getId()){
            case R.id.btn_mark_on_calender:
                todoViewPresenter.onSuccess();
                break;
            case R.id.btn_add:
                todoViewPresenter.addWishTodoInDB(getApplicationContext(), wishTodo.getText().toString());
                wishTodo.getText().clear();
                break;
            default:
                break;
        }

    }
}
