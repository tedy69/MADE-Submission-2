package tech.tedybearblog.tablayout.show;

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

public class FragmentShow extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private ArrayList<Show> shows;
    private String[] dataName;
    private String[] dataContent;
    private String[] dataRelease;
    private String[] dataType;
    private String[] dataLang;
    private String[] dataGenre;
    private String[] dataRuntime;
    private TypedArray dataPoster;
    private TypedArray dataPhoto;

    public FragmentShow() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tvshow_fragment,container,false);
        recyclerView = view.findViewById(R.id.show_rv);

        showRecycleList();
        return view;
    }

    private void showRecycleList() {
        ListShowAdapter listShowAdapter = new ListShowAdapter(shows);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listShowAdapter);

        listShowAdapter.setOnItemClickCallBack(new ListShowAdapter.OnItemClickCallBack() {

            @Override
            public void onItemClicked(Show data) {
                showSelectedShow(data);
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
        dataName = getResources().getStringArray(R.array.show_name);
        dataContent = getResources().getStringArray(R.array.show_description);
        dataRelease = getResources().getStringArray(R.array.show_release);
        dataType = getResources().getStringArray(R.array.show_type);
        dataRuntime = getResources().getStringArray(R.array.show_runtime);
        dataGenre = getResources().getStringArray(R.array.show_genre);
        dataLang = getResources().getStringArray(R.array.show_language);
        dataPhoto = getResources().obtainTypedArray(R.array.show_photo);
        dataPoster = getResources().obtainTypedArray(R.array.show_photoo);
    }

    private void addItem() {
        shows = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Show show = new Show();
            show.setPhoto(dataPhoto.getResourceId(i, -1));
            show.setPoster(dataPoster.getResourceId(i, -1));
            show.setName(dataName[i]);
            show.setFrom(dataRelease[i]);
            show.setContent(dataContent[i]);
            show.setType(dataType[i]);
            show.setRuntime(dataRuntime[i]);
            show.setGenre(dataGenre[i]);
            show.setLanguage(dataLang[i]);
            shows.add(show);
        }
    }


    private void showSelectedShow(Show show) {
        Toast.makeText(getActivity(), "detail to : " + show.getName(), Toast.LENGTH_SHORT).show();

        Show mShow = new Show();
        mShow.setName(show.getName());
        mShow.setFrom(show.getFrom());
        mShow.setContent(show.getContent());
        mShow.setPhoto(show.getPhoto());
        mShow.setPoster(show.getPoster());
        mShow.setLanguage(show.getLanguage());
        mShow.setRuntime(show.getRuntime());
        mShow.setType(show.getType());
        mShow.setGenre(show.getGenre());

        Intent intentDetailShow = new Intent(view.getContext(), DetailShowActivity.class);
        intentDetailShow.putExtra(DetailShowActivity.EXTRA_DETAIL, mShow);
        startActivity(intentDetailShow);

    }

}
