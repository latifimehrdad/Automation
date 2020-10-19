package ir.ngra.automation.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.mlcode.latifiarchitecturelibrary.activity.Activity_Latifi;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.ActivityMainBinding;
import ir.ngra.automation.viewmodels.VM_Main;
import ir.ngra.automation.views.application.AutomationApp;

public class MainActivity extends Activity_Latifi {


    private VM_Main vm_main;
    private NavController navController;
    private boolean preLogin = false;

    @BindView(R.id.imageViewMenu)
    ImageView imageViewMenu;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.simpleDraweeViewProfile)
    SimpleDraweeView simpleDraweeViewProfile;

    @BindView(R.id.textViewUserName)
    TextView textViewUserName;

    @BindView(R.id.ml_ButtonEditProfile)
    ML_Button ml_ButtonEditProfile;

    ConstraintLayout constraintLayout;


    //______________________________________________________________________________________________ onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm_main = new VM_Main(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(vm_main);
        constraintLayout = findViewById(R.id.constraintLayout);
        FR_Latifi.constraintLayout = constraintLayout;
        ButterKnife.bind(this);
        setListener();
        setProfile();
    }
    //______________________________________________________________________________________________ onCreate


    //______________________________________________________________________________________________ setProfile
    private void setProfile() {

        vm_main.getUtility().setRoundImage(simpleDraweeViewProfile, getResources().getColor(R.color.colorAccent), 3, 30, 30, 0, 0);
        String name = AutomationApp.getAutomationApp(this).getUserName();
        if (name == null || name.equalsIgnoreCase(""))
            textViewUserName.setText(getString(R.string.nameLastName));
        else
            textViewUserName.setText(name);
    }
    //______________________________________________________________________________________________ setProfile


    //______________________________________________________________________________________________ setListener
    @SuppressLint("RtlHardcoded")
    private void setListener() {

        ml_ButtonEditProfile.setOnClickListener(v ->{

        });


        imageViewMenu.setOnClickListener(v -> {
            drawer_layout.openDrawer(Gravity.RIGHT , true);
        });

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



/*
    //______________________________________________________________________________________________ attachBaseContext
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    //______________________________________________________________________________________________ attachBaseContext
*/


}