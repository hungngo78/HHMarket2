package com.hhmarket.mobile.db.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.hhmarket.mobile.db.AppDatabase;
import com.hhmarket.mobile.db.entity.ProductEntity;
import com.hhmarket.mobile.db.entity.UserEntity;

import java.util.List;

import io.reactivex.Completable;

/**
 * Repository handling the work with products and comments.
 */
public class UserDBRepository implements UserDataSource {

    private static UserDBRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<ProductEntity>> mObservableProducts;
    private MutableLiveData<UserEntity> mObservableUserInfo;

    private UserDBRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.productDao().loadAllProducts(),
                productEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableProducts.postValue(productEntities);
                    }
                });

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
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<ProductEntity> loadProduct(final int productId) {
        return mDatabase.productDao().loadProduct(productId);
    }

    public LiveData<List<ProductEntity>> searchProducts(String query) {
        return mDatabase.productDao().searchAllProducts(query);
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
