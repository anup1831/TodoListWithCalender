package login;

/**
 * Created by Anup on 2/6/2017.
 */

public interface LoginView {

    void showProgress();
    void hideProgress();
    void setUsernameError();
    void setPasswordError();
    void navigateToHome();
}
