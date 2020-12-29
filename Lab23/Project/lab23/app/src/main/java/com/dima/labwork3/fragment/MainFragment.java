package com.dima.labwork3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dima.labwork3.R;
import com.dima.labwork3.adapter.RecyclerViewAdapter;
import com.dima.labwork3.pojo.MovieDetails;
import com.dima.labwork3.util.MoviesDB;
import com.dima.labwork3.util.NetworkHelper;
import com.dima.labwork3.util.Repo;
import com.dima.labwork3.util.ScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Repo repository;
    private ScrollListener endlessScrollListener;
    private Button savedButton;
    private boolean isSavedMoviesShown = false;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        init(root);

        savedButton.setOnClickListener(v -> {
            if (isSavedMoviesShown) {
                isSavedMoviesShown = false;
                savedButton.setText("Открыть сохраненные");
                getApiList();
            } else {
                isSavedMoviesShown = true;
                getDBList();
                savedButton.setText("Открыть все");
            }
        });

        endlessScrollListener = new ScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                repository.getPopularMovies(page, new NetworkHelper.MovieListLoadCallback() {
                    @Override
                    public void onLoadFail(Call call) {
                    }

                    @Override
                    public void onLoadSuccess(Response response, List<MovieDetails> movieModels) {
                        adapter.addData(movieModels);
                    }
                });
            }
        };
        recyclerView.addOnScrollListener(endlessScrollListener);

        return root;
    }

    void init(View view) {
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        savedButton = view.findViewById(R.id.cached_button);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        repository = new Repo();
        repository.getPopularMovies(1, new NetworkHelper.MovieListLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, List<MovieDetails> movieModels) {
                adapter.addData(movieModels);
            }
        });
    }

    private void getApiList() {
        repository.getPopularMovies(1, new NetworkHelper.MovieListLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, List<MovieDetails> movieModels) {
                adapter.setData(movieModels);
            }
        });
        recyclerView.addOnScrollListener(endlessScrollListener);
    }

    private void getDBList() {
        adapter.setData(MoviesDB.getDatabaseInstance(getContext()).moviesDAO().getAll());
        recyclerView.removeOnScrollListener(endlessScrollListener);
        if (adapter.getItemCount() == 0 & NetworkHelper.isInternetUnavailable(getActivity())) {
            Toast.makeText(getContext(), "Нет интернета и сохраненных", Toast.LENGTH_LONG).show();
        }
    }
}