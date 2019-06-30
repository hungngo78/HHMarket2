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

public interface GetDataService {
    @GET("/category/get_all_categories")
    Call<List<Category1>> getAllCategories();
    //Call<Category1> getAllCategories();

    @GET("login?username=huongquadeo&password=1234")
    Call<User> login();

    @GET("species/")
    Call<SpeciesList> getSpecies();
}
