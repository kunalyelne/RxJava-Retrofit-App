package com.kyodude.rxjava_retrofit_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kyodude.rxjava_retrofit_app.databinding.BookItemBinding;
import com.kyodude.rxjava_retrofit_app.model.dataModel.BookDataModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private List<BookDataModel> bookList;

    public BooksAdapter(List<BookDataModel> list, Context context) {
        bookList = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemBinding bookItemBinding = BookItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(bookItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookDataModel book = bookList.get(position);
        Glide.with(holder.bookItemBinding.ivBook).load(book.getVolumeInfo().getImageLinks().getSmallThumbnail()).circleCrop().into(holder.bookItemBinding.ivBook);
        holder.bookItemBinding.tvBookName.setText(book.getVolumeInfo().getTitle());
        holder.bookItemBinding.tvPublisher.setText(book.getVolumeInfo().getPublisher());
        holder.bookItemBinding.tvBookDesc.setText(book.getVolumeInfo().getDescription());
    }
    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        BookItemBinding bookItemBinding;
        public BookViewHolder(@NonNull BookItemBinding bookItemBinding) {
            super(bookItemBinding.getRoot());
            this.bookItemBinding = bookItemBinding;
        }
    }
}
