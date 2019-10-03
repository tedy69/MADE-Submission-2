package tech.tedybearblog.tablayout.movie;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import tech.tedybearblog.tablayout.R;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL = "extra_detail";

    private ImageView imgDetailMovie,imgPosterMovie;
    private TextView tvNameMovie, tvFromMovie, tvContentMovie, tvBudgetMovie, tvRevenueMovie, tvRuntimeMovie, tvLanguageMovie;
    private ImageButton imgFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ActionBar actionBar = getSupportActionBar();

        tvNameMovie= findViewById(R.id.movie_name);
        tvFromMovie = findViewById(R.id.movie_from);
        tvContentMovie = findViewById(R.id.movie_content);
        tvLanguageMovie= findViewById(R.id.movie_language);
        tvRuntimeMovie= findViewById(R.id.movie_runtime);
        tvBudgetMovie= findViewById(R.id.movie_budget);
        tvRevenueMovie= findViewById(R.id.movie_revenue);
        imgPosterMovie = findViewById(R.id.img_poster);
        imgDetailMovie = findViewById(R.id.img_movie_photo);
        imgFavorite = findViewById(R.id.btn_favorite);


        final Movie mMovie = getIntent().getParcelableExtra(EXTRA_DETAIL);

        String name = "";
        String from = "";
        String content = "";
        String language = "";
        String runtime = "";
        String budget = "";
        String revenue = "";
        if (mMovie != null) {
            name = mMovie.getName();
            from = mMovie.getFrom();
            content = mMovie.getContent();
            language = mMovie.getLanguage();
            runtime = mMovie.getRuntime();
            budget = mMovie.getBudget();
            revenue = mMovie.getRevenue();
        }
        tvNameMovie.setText(name);
        tvFromMovie.setText(from);
        tvContentMovie.setText(content);
        tvLanguageMovie.setText(language);
        tvRuntimeMovie.setText(runtime);
        tvBudgetMovie.setText(budget);
        tvRevenueMovie.setText(revenue);


        if (mMovie != null) {
            Glide.with(getApplicationContext())
                    .load(mMovie.getPhoto())
                    .apply(new RequestOptions().override(525, 350))
                    .into(imgDetailMovie);
        }

        if (mMovie != null) {
            Glide.with(getApplicationContext())
                    .load(mMovie.getPoster())
                    .apply(new RequestOptions())
                    .into(imgPosterMovie);
        }

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMovie != null) {
                    Toast.makeText(getApplicationContext(), mMovie.getName() + " adalah favorit Anda", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (actionBar != null){
            actionBar.setTitle(name);
        }
    }
}
