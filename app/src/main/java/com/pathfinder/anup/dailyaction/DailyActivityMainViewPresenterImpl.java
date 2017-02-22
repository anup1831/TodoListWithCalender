package com.pathfinder.anup.dailyaction;

import android.content.Context;
import android.util.Log;

import com.pathfinder.anup.model.WishItemModel;

import java.util.List;

/**
 * Created by Anup on 2/13/2017.
 */

public class DailyActivityMainViewPresenterImpl implements DailyActivityMainViewPresenter, DailyActivityMainViewInteractor.OnLoadListFinishedListener/*, DailyActivityMainViewInteractor.OnCheckBoxClickedListener */{

    DailyActivityMainView mainView;
    DailyActivityMainViewInteractor mainViewInteractor;

    public DailyActivityMainViewPresenterImpl(DailyActivityMainView mainView, DailyActivityMainViewInteractor mainViewInteractor) {
        this.mainView = mainView;
        this.mainViewInteractor = mainViewInteractor;
    }

    @Override
    public void onResume(Context context) {
        if(mainView != null){
            mainView.showProgress();
        }
        mainViewInteractor.pullListFromDB(context, this);
    }

    /*@Override
    public void onCheckedListener() {
        if(mainView != null){
            mainView.listenToCheckBox();
        }

    }*/

    @Override
    public void onLoadListFinished(List<WishItemModel> listItems) {
        if(mainView != null){
            mainView.setItems(listItems);
            mainView.hideProgress();
        }
    }



    @Override
    public void onItemClicked(int position) {
        if(mainView != null){
            mainView.showMessage("");
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onSuccess() {
        // save the data in DB again with updated status of completed task that marked via checkbox
        //Log.i("Anup", " list size -"+lis)
    }

    @Override
    public void addUpdatedTodoDataInDB(Context context, List<WishItemModel> updatedTodoItemList) {
        if(updatedTodoItemList.size() > -1){
            Log.i("Anup", "updated todoList size "+updatedTodoItemList.size());
            mainView.showProgress();
            mainViewInteractor.addUpdatedTodoDataInDB(context, updatedTodoItemList);
            mainView.hideProgress();

        }
    }
}
