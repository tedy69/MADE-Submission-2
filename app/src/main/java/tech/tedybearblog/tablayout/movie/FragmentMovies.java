package tech.tedybearblog.tablayout.movie;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.tedybearblog.tablayout.R;

public class FragmentMovies extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private ArrayList<Movie> movies;
    private String[] dataName;
    private String[] dataContent;
    private String[] dataRelease;
    private String[] dataBudget;
    private String[] dataLang;
    private String[] dataRevenue;
    private String[] dataRuntime;
    private TypedArray dataPoster;
    private TypedArray dataPhoto;

    public FragmentMovies() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tvmovies_fragment, container, false);
        recyclerView = view.findViewById(R.id.movies_rv);

        moviesRecycleList();
        return view;
    }

    private void moviesRecycleList() {
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallBack(new ListMovieAdapter.OnItemClickCallBack() {

            @Override
            public void onItemClicked(Movie data) {
                moviesSelectedMovie(data);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepare();
        addItem();

    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.movies_name);
        dataContent = getResources().getStringArray(R.array.movies_description);
        dataRelease = getResources().getStringArray(R.array.movies_release);
        dataBudget = getResources().getStringArray(R.array.movies_budget);
        dataRuntime = getResources().getStringArray(R.array.movies_runtime);
        dataRevenue = getResources().getStringArray(R.array.movies_revenue);
        dataLang = getResources().getStringArray(R.array.movies_language);
        dataPhoto = getResources().obtainTypedArray(R.array.movies_photo);
        dataPoster = getResources().obtainTypedArray(R.array.movies_photoo);
    }

    private void addItem() {
        movies = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setPoster(dataPoster.getResourceId(i, -1));
            movie.setName(dataName[i]);
            movie.setFrom(dataRelease[i]);
            movie.setContent(dataContent[i]);
            movie.setBudget(dataBudget[i]);
            movie.setRuntime(dataRuntime[i]);
            movie.setRevenue(dataRevenue[i]);
            movie.setLanguage(dataLang[i]);
            movies.add(movie);
        }
    }


    private void moviesSelectedMovie(Movie movie) {
        Toast.makeText(getActivity(), "detail to : " + movie.getName(), Toast.LENGTH_SHORT).show();

        Movie mMovie = new Movie();
        mMovie.setName(movie.getName());
        mMovie.setFrom(movie.getFrom());
        mMovie.setContent(movie.getContent());
        mMovie.setPhoto(movie.getPhoto());
        mMovie.setPoster(movie.getPoster());
        mMovie.setLanguage(movie.getLanguage());
        mMovie.setRuntime(movie.getRuntime());
        mMovie.setBudget(movie.getBudget());
        mMovie.setRevenue(movie.getRevenue());

        Intent intentDetailMovie = new Intent(view.getContext(), DetailMovieActivity.class);
        intentDetailMovie.putExtra(DetailMovieActivity.EXTRA_DETAIL, mMovie);
        startActivity(intentDetailMovie);

    }
}
