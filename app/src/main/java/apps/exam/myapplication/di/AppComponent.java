package apps.exam.myapplication.di;

import android.app.Application;

import javax.inject.Singleton;

import apps.exam.myapplication.base.BaseApplication;
import apps.exam.myapplication.di.module.ActivityBindingModule;
import apps.exam.myapplication.di.module.AppModule;
import apps.exam.myapplication.di.module.ContextModule;
import apps.exam.myapplication.di.viewmodel.ViewModelModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by msaycon on 21,Apr,2020
 */
@Singleton
@Component(modules = {ContextModule.class, AppModule.class, ViewModelModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
