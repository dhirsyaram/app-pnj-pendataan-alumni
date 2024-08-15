package dts.pnj.pendataanalumni.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import dts.pnj.pendataanalumni.R;
import dts.pnj.pendataanalumni.data.response.ResultsItem;


public class DetailBeritaActivity extends ToolBarActivity {

    private TextView titleTextView;
    private TextView summaryTextView;
    private ImageView imageView;
    private TextView publishedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        setupToolbar(R.id.toolbarDetailBerita, "Detail Berita",true);
        toolbar.setNavigationOnClickListener(v -> {
            super.onBackPressed();
        });

        titleTextView = findViewById(R.id.tv_title_detail_news);
        summaryTextView = findViewById(R.id.tv_desc_detail_news);
        publishedTextView = findViewById(R.id.tv_published_detail);
        imageView = findViewById(R.id.iv_detail_news);

        // Get the article from the intent
        Intent intent = getIntent();
        ResultsItem article = intent.getParcelableExtra("article");

        if (article != null) {
            titleTextView.setText(article.getTitle());
            summaryTextView.setText(article.getSummary());
            publishedTextView.setText(article.getPublishedAt());
            Picasso.get().load(article.getImageUrl()).into(imageView);
        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button is disabled in this screen", Toast.LENGTH_SHORT).show();
    }
}
