package com.kyodude.rxjava_retrofit_app.model.api;

import com.kyodude.rxjava_retrofit_app.model.dataModel.BookDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroService {

    @GET("volumes")
    Call<List<BookDataModel>> getBooks(@Query("q") String query);
}
