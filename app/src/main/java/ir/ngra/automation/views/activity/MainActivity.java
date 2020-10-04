package ir.ngra.automation.views.activity;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.activity.Activity_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.ActivityMainBinding;
import ir.ngra.automation.viewmodels.VM_Main;

public class MainActivity extends Activity_Latifi {


    private VM_Main vm_main;
    private NavController navController;
    private boolean preLogin = false;

    @BindView(R.id.imageViewMenu)
    ImageView imageViewMenu;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;


    //______________________________________________________________________________________________ onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm_main = new VM_Main(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(vm_main);
        ButterKnife.bind(this);
        setListener();
    }
    //______________________________________________________________________________________________ onCreate


    //______________________________________________________________________________________________ setListener
    @SuppressLint("RtlHardcoded")
    private void setListener() {

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {

            String fragment = "";
            if (destination.getLabel() != null)
                fragment = destination.getLabel().toString();

            if ((fragment.equalsIgnoreCase("Splash")) ||
                    (fragment.equalsIgnoreCase("Login")) ||
                    (fragment.equalsIgnoreCase("Verify")) ||
                    (fragment.equalsIgnoreCase("AppUpdate"))) {
                if (!preLogin) {
                    imageViewMenu.setVisibility(View.GONE);
                    preLogin = true;
                    lockDrawer();
                }

            } else {
                if (preLogin) {
                    imageViewMenu.setVisibility(View.VISIBLE);
                    unLockDrawer();
                    preLogin = false;
                }
            }

        });
    }
    //______________________________________________________________________________________________ setListener


    //______________________________________________________________________________________________ unLockDrawer
    public void unLockDrawer() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    //______________________________________________________________________________________________ unLockDrawer


    //______________________________________________________________________________________________ lockDrawer
    public void lockDrawer() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
    //______________________________________________________________________________________________ lockDrawer


}