package come.hhmarket.mobile.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.AndroidViewModel;

import come.hhmarket.mobile.api.GetDataService;
import come.hhmarket.mobile.api.repository.CategoryRepository;
import come.hhmarket.mobile.db.entity.ProductEntity;
import come.hhmarket.mobile.model.Category;
import come.hhmarket.mobile.model.Category1;
import come.hhmarket.mobile.model.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        mObservableCategories = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableCategories.setValue(null);

        //mRepository.getCategories(successHandler, failureHandler);

        // observe the changes of the products from the database and forward them
        //mObservableCategories.addSource(categoriesResponse, mObservableCategories::setValue);
    }

    public void getCategoriesfromAPI() {
        /*Function<List<Category1>, Void> successHandler =
                parameter -> {  categoriesResponse.setValue(parameter);
                    isLoading.setValue(false);
                    return null; };

        Function<Throwable, Void> failureHandler =
                parameter -> {  apiError.setValue(parameter);
                    isLoading.setValue(false);
                    return null; };*/

        //mRepository.getCategories(successHandler, failureHandler);
        //mRepository.getSpecies();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-34-238-44-113.compute-1.amazonaws.com:80/category/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GetDataService apiService = retrofit.create(GetDataService.class);
        //Call<User> call = apiService.login("huongquadeo", "1234");
        Call<List<Category>> call = apiService.getAllCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.i("-------onResponse--------", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.i("-------onFailure--------", "-----------------------------");
            }
        });

        // observe the changes of the products from the database and forward them
        mObservableCategories.addSource(categoriesResponse, mObservableCategories::setValue);
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
