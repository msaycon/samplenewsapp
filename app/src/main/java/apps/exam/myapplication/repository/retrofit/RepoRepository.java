package apps.exam.myapplication.repository.retrofit;

import javax.inject.Inject;

import apps.exam.myapplication.repository.data.Response;
import io.reactivex.Single;

public class RepoRepository {

    private final RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<Response> getNewsHeadlines(String country, String category, String apiKey) {
        return repoService.getNewsHeadlines(country, category, apiKey);
    }
}
