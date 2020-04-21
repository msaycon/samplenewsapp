package apps.exam.myapplication.di.module;

import javax.inject.Singleton;

import apps.exam.myapplication.helpers.NetworkHelper;
import apps.exam.myapplication.repository.retrofit.RepoService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by msaycon on 21,Apr,2020
 */
@Module
public class AppModule {
    private static final String BASE_URL = "https://newsapi.org/v2/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetworkHelper.getClient())
                .build();
    }

    @Singleton
    @Provides
    static RepoService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }
}
