package com.kyodude.rxjava_retrofit_app.di;

import com.kyodude.rxjava_retrofit_app.model.api.RetroService;
import com.kyodude.rxjava_retrofit_app.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
final class AppModule {
    private static final String BASE_URL = "https://books.googleapis.com/books/v1/";

    @Singleton
    @Provides
    static RetroService provideRetroService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RetroService.class);
    }

    @Singleton
    @Provides
    static Repository provideRepository(RetroService api) {
        return new Repository(api);
    }

}
