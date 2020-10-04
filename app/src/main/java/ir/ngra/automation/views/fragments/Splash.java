package ir.ngra.automation.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.SplashBinding;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.viewmodels.VM_Splash;
import pl.droidsonroids.gif.GifImageView;

public class Splash extends FR_Latifi implements FR_Latifi.fragmentActions {


    private VM_Splash vm_splash;

    @BindView(R.id.gifImageViewLoading)
    GifImageView gifImageViewLoading;

    @BindView(R.id.linearLayoutRefresh)
    LinearLayout linearLayoutRefresh;

    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_splash = new VM_Splash(getActivity());
            SplashBinding binding = DataBindingUtil.inflate(inflater, R.layout.splash, container, false);
            binding.setSplash(vm_splash);
            setView(binding.getRoot());
            ButterKnife.bind(this, getView());
            setOnClicks();
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView



    //______________________________________________________________________________________________ onCreateView
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(Splash.this, vm_splash);
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ getActionFromObservable
    @Override
    public void getActionFromObservable(Byte action) {

        if (action.equals(ObservableActions.goToLogin)) {
            getNavController().navigate(R.id.action_splash_to_login);
        }

    }
    //______________________________________________________________________________________________ getActionFromObservable


    //______________________________________________________________________________________________ actionWhenFailureRequest
    @Override
    public void actionWhenFailureRequest() {

    }
    //______________________________________________________________________________________________ actionWhenFailureRequest


    //______________________________________________________________________________________________ OnBackPress
    @Override
    public void OnBackPress() {

    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {

        linearLayoutRefresh.setOnClickListener(v -> {
            gifImageViewLoading.setVisibility(View.VISIBLE);
            linearLayoutRefresh.setVisibility(View.GONE);
            vm_splash.checkToken();
        });
    }
    //______________________________________________________________________________________________ setOnClicks


}
