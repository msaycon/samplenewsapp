package apps.exam.myapplication.repository.retrofit;

import apps.exam.myapplication.repository.data.Response;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepoService {

    @GET("top-headlines")
    Single<Response> getNewsHeadlines(@Query("country") String country,
                                      @Query("category") String category,
                                      @Query("apiKey") String apiKey);
}
