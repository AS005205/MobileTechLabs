package com.dima.labwork3.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dima.labwork3.R;
import com.dima.labwork3.pojo.MovieDetails;
import com.dima.labwork3.util.MoviesDB;
import com.dima.labwork3.util.NetworkHelper;
import com.dima.labwork3.util.Repo;

import retrofit2.Call;
import retrofit2.Response;

public class DetailsFragment extends Fragment {

    private TextView title;
    private TextView tag;
    private TextView description;
    private TextView budget;
    private TextView revenue;
    private TextView release;
    private ImageView poster;
    private Repo repository = new Repo();
    private String id;
    private MovieDetails movieDetail;
    private Button save;

    public DetailsFragment() {

    }

    public static DetailsFragment newInstance(String param1) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString("id", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_details, container, false);
        init(root);

        save.setOnClickListener(v -> {
            if (movieDetail != null) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        MoviesDB.getDatabaseInstance(getContext()).moviesDAO().insert(movieDetail);
                        return null;
                    }
                }.execute();
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });


        if (NetworkHelper.isInternetUnavailable(getContext())) {
            setDetails(MoviesDB.getDatabaseInstance(getContext()).moviesDAO().getById(id));
        } else {
            repository.getMovieDetails(id, new NetworkHelper.MovieDetailsLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLoadSuccess(Response response, MovieDetails movieDetails) {
                    movieDetail = movieDetails;
                    setDetails(movieDetails);
                }
            });
        }
        return root;
    }

    private void init(View view) {
        title = view.findViewById(R.id.movie_details_title);
        tag = view.findViewById(R.id.movie_details_tag);
        description = view.findViewById(R.id.movie_details_description);
        poster = view.findViewById(R.id.movie_details_poster);
        budget = view.findViewById(R.id.movie_details_budget);
        revenue = view.findViewById(R.id.movie_details_revenue);
        release = view.findViewById(R.id.movie_details_release);
        save = view.findViewById(R.id.movie_details_favorite_button);
    }

    public void setDetails(final MovieDetails details) {
        title.setVisibility(View.VISIBLE);
        title.setText(details.getTitle());
        tag.setVisibility(View.VISIBLE);
        tag.setText(details.getType());
        description.setVisibility(View.VISIBLE);
        description.setText(details.getPlot());
        poster.setVisibility(View.VISIBLE);

        Glide.with(this)
                .load(details.getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(poster);
    }
}