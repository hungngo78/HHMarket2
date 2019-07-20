package com.hhmarket.mobile.db.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hhmarket.mobile.db.AppDatabase;
import com.hhmarket.mobile.db.entity.UserEntity;

import io.reactivex.Completable;

/**
 * Repository handling the work with products and comments.
 */
public class UserDBRepository implements UserDataSource {

    private static UserDBRepository sInstance;

    private final AppDatabase mDatabase;
    private MutableLiveData<UserEntity> mObservableUserInfo;

    private UserDBRepository(final AppDatabase database) {
        mDatabase = database;

        mObservableUserInfo = new MutableLiveData<>();
    }

    public static UserDBRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (UserDBRepository.class) {
                if (sInstance == null) {
                    sInstance = new UserDBRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get user information
     */
    @Override
    public LiveData<UserEntity> getUserInfo() {
        return mDatabase.userDao().loadUserInfo();

    }

    /**
     * insert user to database
     */
    @Override
    public Completable insertUserOrUpdateUser(UserEntity user) {
        return mDatabase.userDao().insertUser(user);

    }

    /**
     * delete user when logout
     */
    @Override
    public void deleteAllUser() {
        Completable.fromAction(()-> {
            mDatabase.userDao().deleteAllUser();
        });

    }

}
