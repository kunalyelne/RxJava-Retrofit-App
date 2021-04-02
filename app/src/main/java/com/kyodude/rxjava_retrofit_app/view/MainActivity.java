package com.kyodude.rxjava_retrofit_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.kyodude.rxjava_retrofit_app.view.adapters.BooksAdapter;
import com.kyodude.rxjava_retrofit_app.databinding.ActivityMainBinding;
import com.kyodude.rxjava_retrofit_app.model.dataModel.BookDataModel;
import com.kyodude.rxjava_retrofit_app.viewModel.MainActivityViewModel;

import java.util.ArrayList;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        BooksAdapter booksAdapter = new BooksAdapter(new ArrayList<BookDataModel>());
        binding.rvData.setLayoutManager(new LinearLayoutManager(this));
        binding.rvData.setAdapter(booksAdapter);
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.setSearch(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        viewModel.getIsLoading().observe(this, isLoading -> {
            if(isLoading) {
               binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getBookListLiveData().observe(this, booksAdapter::setList);
    }
}