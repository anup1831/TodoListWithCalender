package com.pathfinder.anup.frgaments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

/**
 * Created by Anup on 2/7/2017.
 */

public class ImpTodoActivity extends Activity implements TodoView, View.OnClickListener, AdapterView.OnItemClickListener{

    TodoViewPresenter todoViewPresenter;
    ProgressBar progressBar;
    ListView listView;
    EditText wishEditText;
    ImageButton addButton;
    Button goToCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_to_do);

        wishEditText = (EditText) findViewById(R.id.todo_item);
        addButton = (ImageButton) findViewById(R.id.btn_add) ;
        listView = (ListView) findViewById(R.id.list);
        goToCalender = (Button) findViewById(R.id.btn_mark_on_calender);
        addButton.setOnClickListener(this);
        goToCalender.setOnClickListener(this);

        todoViewPresenter = new TodoViewPresenterImpl(getApplicationContext(), this);

    }

    @Override
    public void setWishEntryErr() {
        wishEditText.setError("EditText should not be empty!");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setItems(List<String> items) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void navigateToCalender() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        // buttons code
        switch (view.getId()){
            case R.id.btn_add :
                todoViewPresenter.addWishTodoItems(wishEditText.getText().toString());
                break;
            case R.id.btn_mark_on_calender:
                //todoViewPresenter.
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        todoViewPresenter.onItemClicked(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoViewPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        todoViewPresenter.onDestroy();
    }
}
