package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.hhmarket.mobile.api.repository.ProductAPIRepository;
import com.hhmarket.mobile.api.repository.ReviewAPIRepository;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.Review;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewListViewModel extends AndroidViewModel {
    @Inject
    public ReviewAPIRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Review>> mObservableReviews;

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Throwable> apiError;
    private MutableLiveData<List<Review>> reviewsResponse;

    private String mProductId;

    public ReviewListViewModel(Application application, String _productId) {
        super(application);

        /* LiveData -> MutableLiveData -> MediatorLiveData */
        mObservableReviews = new MediatorLiveData<>();

        reviewsResponse =  new MediatorLiveData<>();
        isLoading = new MediatorLiveData<>();
        apiError  = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        mObservableReviews.setValue(null);

        // observe the changes of the products from the database and forward them
        mObservableReviews.addSource(reviewsResponse, mObservableReviews::setValue);

        mProductId = _productId;
    }

    public void getReviewsfromAPI() {
        Callback<List<Review>> callback = new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                //Log.i("-------onResponse--------", response.body().toString());
                reviewsResponse.postValue(response.body());
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                //Log.i("-------onFailure--------", "-----------------------------");
                apiError.postValue(t);
                isLoading.postValue(false);
            }
        };

        mRepository.getReviews(mProductId, callback);
    }

    /**
     * Expose the LiveData Review List so the UI can observe it.
     */
    public LiveData<List<Review>> getReviews() {
        return mObservableReviews;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }
}
