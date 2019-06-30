package come.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.AndroidViewModel;

import come.hhmarket.mobile.api.repository.CategoryRepository;
import come.hhmarket.mobile.db.entity.ProductEntity;
import come.hhmarket.mobile.model.Category;

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
        Function<List<Category>, Void> successHandler =
                parameter -> {  categoriesResponse.setValue(parameter);
                    isLoading.setValue(false);
                    return null; };

        Function<Throwable, Void> failureHandler =
                parameter -> {  apiError.setValue(parameter);
                    isLoading.setValue(false);
                    return null; };

        //mRepository.getCategories(successHandler, failureHandler);
        mRepository.getSpecies();

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
