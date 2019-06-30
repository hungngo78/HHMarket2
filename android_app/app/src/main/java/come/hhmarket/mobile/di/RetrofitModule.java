package come.hhmarket.mobile.di;

import com.google.gson.Gson;
import com.hhmarket.mobile.R;

import javax.inject.Singleton;

import come.hhmarket.mobile.api.GetDataService;
import come.hhmarket.mobile.utils.HHMarketConstants;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.GsonBuilder;
/*
@Module
public class RetrofitModule {

    @Provides  @Singleton
    public GetDataService provideApiService()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Retrofit.Builder retrofitBuilder = new retrofit2.Retrofit.Builder();
        Retrofit retrofit = retrofitBuilder
                .baseUrl(HHMarketConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(GetDataService.class);
    }
}
*/