package tech.tedybearblog.tablayout.show;

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

public class ListShowAdapter extends RecyclerView.Adapter<ListShowAdapter.ListViewHolder> {
    private final ArrayList<Show> listShow;

    public ListShowAdapter(ArrayList<Show> listShow) {
        this.listShow = listShow;
    }

    private ListShowAdapter.OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(ListShowAdapter.OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }
    @NonNull
    @Override
    public ListShowAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_show, parent, false);

        return new ListShowAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListShowAdapter.ListViewHolder holder, int position) {
        Show show = listShow.get(position);

        holder.tvShowName.setText(show.getName());
        holder.tvShowFrom.setText(show.getFrom());
        holder.tvShowContent.setText(show.getContent());

        Glide.with(holder.itemView.getContext())
                .load(show.getPhoto())
                .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                .into(holder.imgPhotoMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClicked(listShow.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() { return listShow.size(); }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPhotoMovie;
        private TextView tvShowName, tvShowFrom, tvShowContent;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imgPhotoMovie = itemView.findViewById(R.id.img_show_photo);
            this.tvShowName = itemView.findViewById(R.id.show_name);
            this.tvShowFrom = itemView.findViewById(R.id.show_from);
            this.tvShowContent = itemView.findViewById(R.id.show_content);
        }
    }

    // Parameter Interface onClickItem
    public interface OnItemClickCallBack{
        void onItemClicked(Show data);
    }
}
