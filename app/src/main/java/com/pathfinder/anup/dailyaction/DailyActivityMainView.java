package com.pathfinder.anup.dailyaction;

import com.pathfinder.anup.model.WishItemModel;

import java.util.List;

/**
 * Created by Anup on 2/13/2017.
 */

public interface DailyActivityMainView {

    void showProgress();
    void hideProgress();
    void setItems(List<WishItemModel> items);
    void showMessage(String message);
    void navigateToHome();
    void listenToCheckBox(boolean b);
}
