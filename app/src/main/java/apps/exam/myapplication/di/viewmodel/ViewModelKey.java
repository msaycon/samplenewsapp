package apps.exam.myapplication.di.viewmodel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

/**
 * Created by msaycon on 21,Apr,2020
 */
@Documented
@MapKey
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
