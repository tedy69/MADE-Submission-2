package tech.tedybearblog.tablayout.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import tech.tedybearblog.tablayout.R;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private final ArrayList<Movie> listMovie;

    public ListMovieAdapter(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    private OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Movie movie = listMovie.get(position);

        holder.tvMovieName.setText(movie.getName());
        holder.tvMovieFrom.setText(movie.getFrom());
        holder.tvMovieContent.setText(movie.getContent());

        Glide.with(holder.itemView.getContext())
                .load(movie.getPhoto())
                .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                .into(holder.imgPhotoMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClicked(listMovie.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() { return listMovie.size(); }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPhotoMovie;
        private TextView tvMovieName, tvMovieFrom, tvMovieContent;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imgPhotoMovie = itemView.findViewById(R.id.img_movie_photo);
            this.tvMovieName = itemView.findViewById(R.id.movie_name);
            this.tvMovieFrom = itemView.findViewById(R.id.movie_from);
            this.tvMovieContent = itemView.findViewById(R.id.movie_content);
        }
    }

    // Parameter Interface onClickItem
    public interface OnItemClickCallBack{
        void onItemClicked(Movie data);
    }

}
