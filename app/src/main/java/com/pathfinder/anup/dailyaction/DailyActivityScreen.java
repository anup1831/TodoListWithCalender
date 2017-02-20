package com.pathfinder.anup.dailyaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pathfinder.anup.adapter.WishListAdapter;
import com.pathfinder.anup.imptodo.R;
import com.pathfinder.anup.model.WishItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 2/13/2017.
 */

public class DailyActivityScreen extends Activity implements DailyActivityMainView, View.OnClickListener{

    ListView listView;
    Button btn_done;
    ProgressBar progressBar;
    DailyActivityMainViewPresenter mainViewPresenter;
    public static List<WishItemModel> modelList = new ArrayList<>();;

    public void setModelList(List<WishItemModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_activity_screen);

        listView = (ListView) findViewById(R.id.daily_activity_view);
        btn_done = (Button) findViewById(R.id.btn_mark_done);
        btn_done.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        mainViewPresenter = new DailyActivityMainViewPresenterImpl(this, new DailyActivityMainViewInteractorImpl());

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_mark_done){
            Toast.makeText(getApplicationContext(), "Save data to DB "+modelList.size(), Toast.LENGTH_LONG).show();
           // saveDataInDB();
        }
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
    public void setItems(List<WishItemModel> items) {
        listView.setAdapter( new WishListAdapter(getApplicationContext(), items));
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void navigateToHome() {
        Toast.makeText(getApplicationContext(), "done clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void listenToCheckBox(boolean b) {
        Toast.makeText(getApplicationContext(), "checkbox clicked"+b, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainViewPresenter.onResume(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewPresenter.onDestroy();
    }

}
