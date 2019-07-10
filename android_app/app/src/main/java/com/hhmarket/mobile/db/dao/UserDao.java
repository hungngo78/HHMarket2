package com.hhmarket.mobile.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hhmarket.mobile.db.entity.ProductEntity;
import com.hhmarket.mobile.db.entity.UserEntity;
import com.hhmarket.mobile.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<UserEntity> loadUserInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(UserEntity user);

   // @Query("SELECT * FROM user LIMIT 1")
    //Flowable<UserEntity> getUser();

    @Query("DELETE FROM user")
    void deleteAllUser();



}
