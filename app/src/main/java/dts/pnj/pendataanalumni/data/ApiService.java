package dts.pnj.pendataanalumni.data;

import dts.pnj.pendataanalumni.data.response.ArticlesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("articles/")
    Call<ArticlesResponse> getArticles(@Query("limit") int limit);
}
