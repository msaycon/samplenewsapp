package apps.exam.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import apps.exam.myapplication.R;
import apps.exam.myapplication.base.BaseActivity;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavController.OnDestinationChangedListener {

    @BindView(R.id.toolBar)
    Toolbar mToolbar;

    private NavController mNavController;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mNavController.addOnDestinationChangedListener(this);
        NavigationUI.setupWithNavController(mToolbar, mNavController);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

    }
}
