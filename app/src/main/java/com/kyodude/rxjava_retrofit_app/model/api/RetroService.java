package com.kyodude.rxjava_retrofit_app.model.api;

import com.kyodude.rxjava_retrofit_app.model.dataModel.ApiResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroService {

    @GET("volumes")
    Observable<ApiResponse> getBooks(@Query("q") String query);
}
