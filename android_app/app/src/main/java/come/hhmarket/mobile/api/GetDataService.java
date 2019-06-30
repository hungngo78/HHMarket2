package come.hhmarket.mobile.api;

import com.google.gson.JsonElement;

import java.util.List;

import come.hhmarket.mobile.model.Category;
import come.hhmarket.mobile.model.Category1;
import come.hhmarket.mobile.model.Species;
import come.hhmarket.mobile.model.SpeciesList;
import come.hhmarket.mobile.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Observable;

public interface GetDataService {
    @GET("get_all_categories")
    Call<List<Category>> getAllCategories();

    @GET("get_all_categories")
    Call<List<Category1>> getAllCategories1();

    @GET("hello")
    Call<String> hello();

    @GET("login")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @GET("species/")
    Call<SpeciesList> getSpecies();
}
