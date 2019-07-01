package com.hhmarket.mobile.di;


public class ComponentInjector {
    public static MagicBox magicBox;

    public static void init() {
        // build magic box
        magicBox = DaggerMagicBox.builder()
                .apiModule(new ApiModule())
                .categoryRepositoryModule(new CategoryRepositoryModule())
                .build();
    }
}
