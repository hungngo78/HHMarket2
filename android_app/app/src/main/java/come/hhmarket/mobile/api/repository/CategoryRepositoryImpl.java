package come.hhmarket.mobile.api.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

//import come.hhmarket.mobile.di.RetrofitModule;
import come.hhmarket.mobile.api.GetDataService;
import come.hhmarket.mobile.model.Category;
import come.hhmarket.mobile.model.Category1;
import come.hhmarket.mobile.model.Species;
import come.hhmarket.mobile.model.SpeciesList;
import come.hhmarket.mobile.model.User;

//import io.reactivex.observers.DisposableObserver;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRepositoryImpl implements  CategoryRepository {
    private GetDataService apiService;

    public CategoryRepositoryImpl() {

    }

    public void getSpecies() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-34-238-44-113.compute-1.amazonaws.com:80/account/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GetDataService apiService = retrofit.create(GetDataService.class);
        Call<User> call = apiService.login("huongquadeo", "1234");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("-------onResponse--------", response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("-------onFailure--------", "-----------------------------");
            }
        });

        int iii = 3;
    }

    public void getCategories(Function<List<Category>, Void> successHandler,
                                        Function<Throwable, Void> failureHandler) {
        /*
        Call<Category> call = apiService.getAllCategories();
        //Call<List<Category>> call = apiService.getAllCategories();
        call.enqueue(new Callback<Category1>() {
            @Override
            public void onResponse(Call<Category1> call, Response<Category1> response) {
                //successHandler.apply(response.body());
                int i =1;
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Category1> call, Throwable t) {
                failureHandler.apply(t);
            }
        });*/
    }


}
