package come.hhmarket.mobile.api;

import java.util.List;

import come.hhmarket.mobile.model.Category;
import come.hhmarket.mobile.model.Category1;
import come.hhmarket.mobile.model.Species;
import come.hhmarket.mobile.model.SpeciesList;
import come.hhmarket.mobile.model.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/category/get_all_categories")
    Call<List<Category1>> getAllCategories();
    //Call<Category1> getAllCategories();

    @GET("login")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @GET("species/")
    Call<SpeciesList> getSpecies();
}
