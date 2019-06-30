package come.hhmarket.mobile.di;

import javax.inject.Singleton;

import come.hhmarket.mobile.api.GetDataService;
import come.hhmarket.mobile.api.repository.CategoryRepository;
import come.hhmarket.mobile.api.repository.CategoryRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class CategoryRepositoryModule {
    @Provides
    @Singleton
    /*public CategoryRepository providePostRepository(GetDataService apiService) {
        return new CategoryRepositoryImpl(apiService);
    }*/
    public CategoryRepository providePostRepository() {
        return new CategoryRepositoryImpl();
    }

    //fun providePostRepository(apiService:GetDataService): CateRepository = SpeciesRepositoryImpl(apiService)

}
