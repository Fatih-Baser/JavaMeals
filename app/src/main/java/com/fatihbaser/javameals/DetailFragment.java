package com.fatihbaser.javameals;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fatihbaser.javameals.adapter.MovieListAdapter;
import com.fatihbaser.javameals.model.MovieModel;
import com.fatihbaser.javameals.viewmodel.DetailViewModel;
import com.fatihbaser.javameals.viewmodel.MovieListViewModel;

import java.util.List;


public class DetailFragment extends Fragment implements MovieListAdapter.ItemClickListener{

    private NavController navController;
    private int mealUuid;
    private List<MovieModel> moviedetailModelList;
    private MovieListAdapter adapter;
    private DetailViewModel viewModel;
    private TextView malzemeler;
    private TextView yemek_adi;
    private ImageView resim;
    private TextView tarifi;
    private RecyclerView recyclerView;
    View v;
    String posterPath = "";

    public DetailFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_feed,container,false);
        yemek_adi =v.findViewById(R.id.mealName);
        malzemeler=v.findViewById(R.id.malzemelerTextView);
        tarifi=v.findViewById(R.id.tarifiTextView);
        resim=v.findViewById(R.id.mealImage);
        LinearLayoutManager layoutManager = new GridLayoutManager(v.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new MovieListAdapter(v.getContext(),moviedetailModelList,this);
        recyclerView.setAdapter(adapter);



        return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mealUuid=DetailFragmentArgs.fromBundle(getArguments()).getMealUuid();
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.getMoviesListObserver().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null) {
                    moviedetailModelList = movieModels;
                    adapter.setMovieList(movieModels);

                } else {

                }
            }
        });
        viewModel.makeApiCall();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMovieClick(MovieModel movie) {
        yemek_adi.setText(movie.getTitle());
        malzemeler.setText(movie.getMalzemeler());
        tarifi.setText(movie.getTarifi());
        Glide.with(DetailFragment.this).load(posterPath).into(resim);
        posterPath=movie.getImage();
    }
}