package come.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.R;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

import come.hhmarket.mobile.model.Category;
import come.hhmarket.mobile.model.Species;

public interface CategoryRepository {
    public void getCategories(Function<List<Category>, Void> successHandler,
                                        Function<Throwable, Void> failureHandler);


    public void getSpecies();
}
