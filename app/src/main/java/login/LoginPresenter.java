package login;

/**
 * Created by Anup on 2/6/2017.
 */

public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();
}
