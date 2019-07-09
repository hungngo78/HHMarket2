package com.hhmarket.mobile.ui.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.app.Application;
import android.util.Log;
import android.util.Patterns;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.api.repository.UserRepository;
import com.hhmarket.mobile.model.User;
import com.hhmarket.mobile.ui.activity.ui.login.LoginFormState;
import com.hhmarket.mobile.ui.activity.ui.login.LoginResult;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    @Inject
    public UserRepository loginRepository;

    public LoginViewModel(Application application) {

        super(application);
    }

    public void setUserRepository(UserRepository loginRepository) {

        this.loginRepository = loginRepository;

    }
    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        Callback<User> callback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.i("-------onResponse--------", response.body().toString());
                User data = response.body();
                loginResult.setValue(new LoginResult(data));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginResult.setValue(new LoginResult(R.string.login_failed));
                Log.i("-------onFailure--------", "-----------------------------");

            }

        };

        loginRepository.getUserInfo(username, password, callback);
 }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 3;
    }
}
