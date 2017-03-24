package com.pathfinder.anup.todolist;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by Anup on 2/8/2017.
 */

public class TodoMainViewPresenterImpl implements TodoMainViewPresenter, TodoMainViewInteractor.OnLoadListFinishedListener {

    TodoMainView todoMainView;
    TodoMainViewInteractor todoMainViewInteractor;
    boolean error = false;

    public TodoMainViewPresenterImpl(TodoMainView todoMainView, TodoMainViewInteractor todoMainViewInteractor) {
        this.todoMainView = todoMainView;
        this.todoMainViewInteractor = todoMainViewInteractor;
    }

    @Override
    public void onResume(Context context) {
        if(todoMainView != null){
            todoMainView.showProgress();
        }
        todoMainViewInteractor.pullListFromDB(context, this);
    }

    @Override
    public void onItemClicked(int position) {
        todoMainView.showMessage(String.format("Clicked position "+ position));
    }

    @Override
    public void onDestroy() {
        todoMainView = null;
    }

    @Override
    public void onSuccess() {
        todoMainView.navigateToHome();
    }

    @Override
    public void addWishTodoInDB(Context context, int id, String todoItem, String desc) {
        if(TextUtils.isEmpty(todoItem)){
            todoMainView.todoItemErr();
            todoMainView.hideProgress();
            error = true;
            return;
        } else {
            todoMainViewInteractor.addWishTodoData(context, id, todoItem, desc);
            todoMainView.showProgress();
            todoMainViewInteractor.pullListFromDB(context, this);
            todoMainView.hideProgress();

        }
    }

    @Override
    public void onLoadListFinished(List<String> listItems) {
        if(todoMainView != null){
          todoMainView.setItems(listItems);
            todoMainView.hideProgress();
        }
    }

}
