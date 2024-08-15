package dts.pnj.pendataanalumni.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dts.pnj.pendataanalumni.R;
import dts.pnj.pendataanalumni.activity.MainActivity;
import dts.pnj.pendataanalumni.data.ApiService;
import dts.pnj.pendataanalumni.data.NewsAdapter;
import dts.pnj.pendataanalumni.data.response.ArticlesResponse;
import dts.pnj.pendataanalumni.data.response.ResultsItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeritaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsAdapter adapter;
    private List<ResultsItem> articleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_berita, container, false);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setToolbarTitle("Berita");
        }

        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.rv_data_news);
        articleList = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NewsAdapter(getContext(), articleList);
        recyclerView.setAdapter(adapter);

        loadNewsArticles();

        return view;
    }

    private void loadNewsArticles() {
        // Show ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spaceflightnewsapi.net/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        api.getArticles(10).enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
                // Hide ProgressBar
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    ArticlesResponse apiResponse = response.body();
                    articleList.clear();
                    articleList.addAll(apiResponse.getResults());
                    adapter.notifyDataSetChanged();
                } else {
                    // Handle case where response is not successful
                    Log.d("Berita Fragment", "Response failed: "+response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticlesResponse> call, @NonNull Throwable t) {
                // Hide ProgressBar
                progressBar.setVisibility(View.GONE);

                Log.d("Berita Fragment", "Error: "+t.getMessage());
            }
        });
    }
}
