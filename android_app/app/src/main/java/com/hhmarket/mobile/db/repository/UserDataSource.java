package com.hhmarket.mobile.db.repository;

import androidx.lifecycle.LiveData;

import com.hhmarket.mobile.db.entity.UserEntity;

import io.reactivex.Completable;

public interface UserDataSource {

    /**
     * Get user information
     */

    public LiveData<UserEntity> getUserInfo() ;
    /**
     * insert user to database
     */
    public Completable insertUserOrUpdateUser(UserEntity user) ;

    /**
     * delete user when logout
     */

    public void deleteAllUser();
}
