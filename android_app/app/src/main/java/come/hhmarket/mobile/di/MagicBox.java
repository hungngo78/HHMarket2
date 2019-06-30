package come.hhmarket.mobile.di;

import javax.inject.Singleton;

import come.hhmarket.mobile.ui.viewmodel.CategoryListViewModel;
import dagger.Component;

@Singleton
//@Component (modules = { RetrofitModule.class, CategoryRepositoryModule.class })
@Component (modules = { CategoryRepositoryModule.class })
public interface MagicBox {
    // allow to inject into our viewmodel classes
    // method name not important
    void inject(CategoryListViewModel model);
}
