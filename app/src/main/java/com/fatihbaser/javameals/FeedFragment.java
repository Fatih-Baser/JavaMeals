package com.fatihbaser.javameals;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fatihbaser.javameals.adapter.MovieListAdapter;
import com.fatihbaser.javameals.model.MovieModel;
import com.fatihbaser.javameals.viewmodel.MovieListViewModel;

import java.util.List;


public class FeedFragment extends Fragment implements MovieListAdapter.ItemClickListener  {
    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    Context context;
    private RecyclerView recyclerView;
    TextView noresult ;
    View v;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      v = inflater.inflate(R.layout.fragment_feed,container,false);
      recyclerView =v.findViewById(R.id.recyclerView);
      noresult=v.findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new GridLayoutManager(v.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new MovieListAdapter(v.getContext(),movieModelList,this);
        recyclerView.setAdapter(adapter);



       return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObserver().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null) {
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModels);
                    noresult.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMovieClick(MovieModel movie) {
        Toast.makeText(v.getContext(), "Clicked Movie Name is : " +movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}