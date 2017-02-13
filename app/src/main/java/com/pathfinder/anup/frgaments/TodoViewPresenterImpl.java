package com.pathfinder.anup.frgaments;

import android.content.Context;

import java.util.List;

/**
 * Created by Anup on 2/7/2017.
 */

public class TodoViewPresenterImpl implements TodoViewPresenter, TodoViewInteractor.OnFinishedListener {
    TodoView todoView;
    TodoViewInteractor todoViewInteractor;
    Context context = null;

    public TodoViewPresenterImpl(Context context, TodoView todoView/*, TodoMainViewInteractor todoViewInteractor*/) {
        this.context = context;
        this.todoView = todoView;
        this.todoViewInteractor = new TodoViewInteractorImpl();
    }

    @Override
    public void onWishEntryError() {
        if (todoView != null){
            todoView.setWishEntryErr();
            todoView.hideProgress();
        }
    }

    @Override
    public void onSuccess(List<String> items) {
        if(todoView != null){
            todoView.setItems(items);
            todoView.hideProgress();
        }
    }

    @Override
    public void onResume() {
        if(todoView != null){
            todoView.showProgress();
        }
        todoViewInteractor.pullListFromDB(context, this);
    }

    @Override
    public void onItemClicked(int position) {
        if (todoView != null) {
            todoView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy() {
        todoView = null;
    }

    @Override
    public void addWishTodoItems(String wishStr) {
        if(todoView != null){
            todoView.showProgress();
        }
        todoViewInteractor.addWishTodoList(context, wishStr, this);
    }

    public TodoView getTodoView() {
        return todoView;
    }
}
