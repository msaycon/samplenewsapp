package apps.exam.myapplication.base;

import android.util.Log;

import androidx.annotation.NonNull;
import apps.exam.myapplication.di.AppComponent;
import apps.exam.myapplication.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class BaseApplication extends DaggerApplication {

    public static String LOG_TAG = "HEADLINES-";

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new DebugTree());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }

    private static class DebugTree extends Timber.DebugTree {
        @Override
        protected void log(int priority, String callingClass, @NonNull String message, Throwable t) {
            Log.println(priority, LOG_TAG + callingClass, message);
        }
    }

}
