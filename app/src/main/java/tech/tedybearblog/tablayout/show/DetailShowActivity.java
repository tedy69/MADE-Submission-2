package tech.tedybearblog.tablayout.show;

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

public class DetailShowActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL = "extra_detail";

    private ImageView imgDetailShow, imgPosterShow;
    private TextView tvNameShow, tvFromShow, tvContentShow, tvBudgetShow, tvRevenueShow, tvRuntimeShow, tvLanguageShow;
    private ImageButton imgFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        ActionBar actionBar = getSupportActionBar();

        tvNameShow = findViewById(R.id.show_name);
        tvFromShow = findViewById(R.id.show_from);
        tvContentShow = findViewById(R.id.show_content);
        tvLanguageShow = findViewById(R.id.show_language);
        tvRuntimeShow = findViewById(R.id.show_runtime);
        tvBudgetShow = findViewById(R.id.show_budget);
        tvRevenueShow = findViewById(R.id.show_revenue);
        imgPosterShow = findViewById(R.id.img_poster);
        imgDetailShow = findViewById(R.id.img_show_photo);
        imgFavorite = findViewById(R.id.btn_favorite);

        final Show mShow = getIntent().getParcelableExtra(EXTRA_DETAIL);

        String name = "";
        String from = "";
        String content = "";
        String language = "";
        String runtime = "";
        String type = "";
        String genre = "";
        if (mShow != null) {
            name = mShow.getName();
            from = mShow.getFrom();
            content = mShow.getContent();
            language = mShow.getLanguage();
            runtime = mShow.getRuntime();
            type = mShow.getType();
            genre = mShow.getGenre();
        }

        tvNameShow.setText(name);
        tvFromShow.setText(from);
        tvContentShow.setText(content);
        tvLanguageShow.setText(language);
        tvRuntimeShow.setText(runtime);
        tvBudgetShow.setText(type);
        tvRevenueShow.setText(genre);

        if (mShow != null) {
            Glide.with(getApplicationContext())
                    .load(mShow.getPhoto())
                    .apply(new RequestOptions().override(525, 350))
                    .into(imgDetailShow);
        }

        if (mShow != null) {
            Glide.with(getApplicationContext())
                    .load(mShow.getPoster())
                    .apply(new RequestOptions())
                    .into(imgPosterShow);
        }

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShow != null) {
                    Toast.makeText(getApplicationContext(), mShow.getName() + " adalah favorit Anda", Toast.LENGTH_SHORT).show();
                }
            }
        });

        actionBar.setTitle(name);

    }

}
