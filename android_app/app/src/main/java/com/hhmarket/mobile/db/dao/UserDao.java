package com.hhmarket.mobile.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hhmarket.mobile.db.entity.ProductEntity;
import com.hhmarket.mobile.db.entity.UserEntity;
import com.hhmarket.mobile.model.User;

import java.util.List;

public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<UserEntity> loadUserInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

}
