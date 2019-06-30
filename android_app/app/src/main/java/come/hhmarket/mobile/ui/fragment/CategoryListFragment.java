package come.hhmarket.mobile.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.databinding.CategoryListFragmentBinding;

import java.util.List;

import come.hhmarket.mobile.api.GetDataService;
import come.hhmarket.mobile.db.entity.ProductEntity;
import come.hhmarket.mobile.model.Category;
import come.hhmarket.mobile.model.Category1;
import come.hhmarket.mobile.model.User;
import come.hhmarket.mobile.ui.adapter.CategoryListAdapter;
import come.hhmarket.mobile.di.ComponentInjector;
import come.hhmarket.mobile.ui.viewmodel.CategoryListViewModel;
import come.hhmarket.mobile.utils.HHMarketConstants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryListFragment extends Fragment {
    private CategoryListViewModel mViewModel;
    private CategoryListAdapter mAdapter;
    private CategoryListFragmentBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(HHMarketConstants.TAG_HOME);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mBinding = DataBindingUtil.inflate(inflater, R.layout.category_list_fragment, container, false);
        mBinding = CategoryListFragmentBinding.inflate(inflater, container, false);

        // adapter
        mAdapter = new CategoryListAdapter();
        mBinding.categoriesList.setAdapter(mAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // create ViewModel & allow inject repository
        mViewModel = ViewModelProviders.of(this).get(CategoryListViewModel.class);
        if (ComponentInjector.magicBox != null) {
            Log.i("-----------------", "--------------------------------------- magix box is not NULL");
        } else {
            Log.i("-----------------", "--------------------------------------- magix box is NULL");
        }
        ComponentInjector.magicBox.inject(mViewModel);

        //mViewModel.getCategoriesfromAPI();

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://ec2-34-238-44-113.compute-1.amazonaws.com:80/category/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        GetDataService apiService = retrofit.create(GetDataService.class);
//        //Call<User> call = apiService.login("huongquadeo", "1234");
//        Call<List<Category>> call = apiService.getAllCategories();
//
//        call.enqueue(new Callback<List<Category>>() {
//            @Override
//            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//                Log.i("-------onResponse--------", response.body().toString());
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Category>> call, Throwable t) {
//                Log.i("-------onFailure--------", "-----------------------------");
//            }
//        });

        subscribeUi();
    }

    private void subscribeUi() {
        mViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> myCategories) {
                if (myCategories != null) {
                    mBinding.setIsLoading(false);
                    mAdapter.setCategoryList(myCategories);
                } else {
                    mBinding.setIsLoading(true);
                }
                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }
}
