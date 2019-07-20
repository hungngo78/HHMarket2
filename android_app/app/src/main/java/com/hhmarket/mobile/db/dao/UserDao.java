package com.hhmarket.mobile.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hhmarket.mobile.db.entity.UserEntity;

import io.reactivex.Completable;

@Dao
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
