package com.kyodude.rxjava_retrofit_app.repository;

import android.util.Log;

import com.kyodude.rxjava_retrofit_app.model.api.RetroService;
import com.kyodude.rxjava_retrofit_app.model.dataModel.ApiResponse;
import com.kyodude.rxjava_retrofit_app.model.dataModel.BookDataModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {

    private static final String TAG = "Repository";
    private final RetroService api;
    private MutableLiveData<List<BookDataModel>> bookList = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    @Inject
    public Repository(RetroService api) {
        this.api = api;
    }

    public LiveData<List<BookDataModel>> getBooks(String query) {
        api.getBooks(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getBookListObserverRx());

        return bookList;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public Observer<ApiResponse> getBookListObserverRx() {
        return new Observer<ApiResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                isLoading.setValue(true);
            }

            @Override
            public void onNext(@NonNull ApiResponse apiResponse) {
                bookList.postValue(apiResponse.getItems());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                bookList.postValue(null);
                Log.d(TAG, "onFailure: failed to fetch book list from server");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                isLoading.setValue(false);
            }
        };
    }

}
