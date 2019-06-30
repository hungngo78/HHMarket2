package come.hhmarket.mobile.di;


import android.util.Log;

import come.hhmarket.mobile.model.Category;

public class ComponentInjector {
    public static MagicBox magicBox;

    public static void init() {
        Log.i("ComponentInjector","--------------------------------ComponentInjector: Init  START---------------------");

        // build magic box
        magicBox = DaggerMagicBox.builder()
                                //.retrofitModule(new RetrofitModule())
                                .categoryRepositoryModule(new CategoryRepositoryModule())
                                .build();
        Log.i("ComponentInjector","--------------------------------ComponentInjector: Init  END---------------------");
    }
}
