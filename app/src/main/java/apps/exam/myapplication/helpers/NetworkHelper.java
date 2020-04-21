package apps.exam.myapplication.helpers;

import apps.exam.myapplication.base.BaseApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class NetworkHelper {
    public static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.tag(BaseApplication.LOG_TAG).d(message));
        logging.level(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
}
