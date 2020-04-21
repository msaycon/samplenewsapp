package apps.exam.myapplication.di.module;

import apps.exam.myapplication.ui.MainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {
    @ContributesAndroidInjector
    abstract MainFragment provideMainFragment();
}
