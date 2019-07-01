package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.AndroidViewModel;

import com.hhmarket.mobile.api.repository.CategoryRepository;
import com.hhmarket.mobile.model.Category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListViewModel extends AndroidViewModel {
    @Inject
    public CategoryRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Category>> mObservableCategories;

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Throwable> apiError;
    private MutableLiveData<List<Category>> categoriesResponse;

    public CategoryListViewModel(Application application) {
        super(application);

        /* LiveData -> MutableLiveData -> MediatorLiveData */
        mObservableCategories = new MediatorLiveData<>();

        categoriesResponse =  new MediatorLiveData<>();   // phải new trc, ko thì luc addSrc cho mObservableCategories sẽ crash
        isLoading = new MediatorLiveData<>();
        apiError  = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        mObservableCategories.setValue(null);

        // observe the changes of the products from the database and forward them
        mObservableCategories.addSource(categoriesResponse, mObservableCategories::setValue);
    }

    public void getCategoriesfromAPI() {
        Callback<List<Category>> callback = new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                //Log.i("-------onResponse--------", response.body().toString());
                categoriesResponse.postValue(response.body());
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                //Log.i("-------onFailure--------", "-----------------------------");
                apiError.postValue(t);
                isLoading.postValue(false);
            }
        };

        mRepository.getCategories(callback);
    }

    /**
     * Expose the LiveData Category so the UI can observe it.
     */
    public LiveData<List<Category>> getCategories() {
        return mObservableCategories;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }
}
