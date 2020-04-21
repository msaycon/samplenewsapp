package apps.exam.myapplication.di.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import apps.exam.myapplication.ui.MainViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by msaycon on 21,Apr,2020
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindListViewModel(MainViewModel mainViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
