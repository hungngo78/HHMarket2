package com.hhmarket.mobile.di;

import android.content.Context;

import com.hhmarket.mobile.AppExecutors;
import com.hhmarket.mobile.db.AppDatabase;
import com.hhmarket.mobile.db.repository.DataRepository;
import com.hhmarket.mobile.db.repository.UserDataSource;
import com.hhmarket.mobile.ui.viewmodel.LoginViewModelFactory;

public class LoginInjector {

    public static UserDataSource provideUserDataSource(Context context) {
        AppDatabase database = AppDatabase.getInstance(context, new AppExecutors());
        return DataRepository.getInstance(database);
    }

    public static LoginViewModelFactory provideViewModelFactory(Context context) {
        UserDataSource dataSource = provideUserDataSource(context);
        return new LoginViewModelFactory(dataSource);
    }
}
