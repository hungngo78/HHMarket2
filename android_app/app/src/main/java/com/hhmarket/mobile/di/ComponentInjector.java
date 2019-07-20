package com.hhmarket.mobile.di;


public class ComponentInjector {
    public static MagicBox magicBox;

    public static void init() {
        // build magic box
        magicBox = DaggerMagicBox.builder().build();

                //.apiModule(new ApiModule())
                //.userRepositoryModule(new UserRepositoryModule())
                //.productionRepositoryModule(new ProductionRepositoryModule())
                //.reviewRepositoryModule(new ReviewRepositoryModule())
    }
}
