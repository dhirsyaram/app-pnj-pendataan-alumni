package dts.pnj.pendataanalumni.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dts.pnj.pendataanalumni.R;
import dts.pnj.pendataanalumni.activity.DetailBeritaActivity;
import dts.pnj.pendataanalumni.data.response.ResultsItem;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<ResultsItem> newsList;
    private final Context context;

    public NewsAdapter(Context context, List<ResultsItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_grid, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        ResultsItem article = newsList.get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView summaryTextView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_news_photo);
            titleTextView = itemView.findViewById(R.id.tv_title_news);
            summaryTextView = itemView.findViewById(R.id.tv_desc_news);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ResultsItem article = newsList.get(position);
                    Intent intent = new Intent(context, DetailBeritaActivity.class);
                    intent.putExtra("article", article);
                    context.startActivity(intent);
                }
            });
        }

        public void bind(ResultsItem article) {
            titleTextView.setText(article.getTitle());
            summaryTextView.setText(article.getSummary());
            Picasso.get().load(article.getImageUrl()).into(imageView);
        }
    }
}
