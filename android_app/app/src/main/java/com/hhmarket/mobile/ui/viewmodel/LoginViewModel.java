package com.hhmarket.mobile.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.hhmarket.mobile.R;

import com.hhmarket.mobile.api.repository.UserAPIRepository;

import com.hhmarket.mobile.db.entity.UserEntity;
import com.hhmarket.mobile.db.repository.UserDataSource;
import com.hhmarket.mobile.model.User;
import com.hhmarket.mobile.ui.activity.LoginFormState;
import com.hhmarket.mobile.ui.activity.LoginResult;

import javax.inject.Inject;

import io.reactivex.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private UserEntity mUser;
    private UserDataSource mDataSource;

    @Inject
    public UserAPIRepository loginRepository;

    public LoginViewModel(UserDataSource dataSource) {
        mDataSource = dataSource;
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

                if (response.isSuccessful()) {
                    Log.i("-------onResponse--------", response.body().toString());
                    final User data = response.body();

                    loginResult.setValue(new LoginResult(data));
                    //deleteAllUser();

                    Thread a = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            updateUser(data).subscribe(() -> {
                                // handle the completion server has update the user object
                                System.out.println("Huong -> complete");
                            },error -> {
                                //handle error
                                System.out.println("Huong -> error" + error.fillInStackTrace()) ;
                            }) ;
                        }
                    });
                    a.start();


//                    .subscribe(() -> {
//                        // handle the completion server has update the user object
//                        System.out.println("Huong -> complete");
//                    },error -> {
//                        //handle error
//                        System.out.println("Huong -> error" + error.fillInStackTrace()) ;
//                    }) ;

                } else {
                    loginResult.setValue(new LoginResult(R.string.login_failed ));
                    deleteAllUser();
                    Log.i("-------onFailure--------", "-----------------------------");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginResult.setValue(new LoginResult(R.string.login_failed));
                deleteAllUser();
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

    /**
     * Update the user name.
     *
     * @param user the new user name
     * @return a {@link Completable} that completes when the user name is updated
     */
    public Completable updateUser(final User user) {
        // if there's no user, create a new user.
        // if we already have a user, then, since the user object is immutable,
        // create a new user, with the id of the previous user and the updated user name.
        mUser = mUser == null
                ? new UserEntity(user)
                : new UserEntity(mUser.userId, user.address, user.city, user.state,user.zipCode);
        return mDataSource.insertUserOrUpdateUser(mUser);
    }

    public void deleteAllUser() {
        mDataSource.deleteAllUser();
    }

    public LiveData<UserEntity> getUserFromDataBase() {
        return mDataSource.getUserInfo();
    }
}
