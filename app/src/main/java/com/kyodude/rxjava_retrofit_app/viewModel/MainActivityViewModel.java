package com.kyodude.rxjava_retrofit_app.viewModel;

import com.kyodude.rxjava_retrofit_app.model.dataModel.BookDataModel;
import com.kyodude.rxjava_retrofit_app.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<String> query = new MutableLiveData<>();

    @Inject MainActivityViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<BookDataModel>> getBookListLiveData() {
        return Transformations.switchMap(query, query -> repository.getBooks(query));
    }

    public LiveData<Boolean> getIsLoading() {
        return repository.getIsLoading();
    }

    public void setSearch(String query) {
        this.query.setValue(query);
    }
}
