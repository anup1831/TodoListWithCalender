package com.pathfinder.anup.login;

/**
 * Created by Anup on 2/6/2017.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);
}
