package ir.ngra.automation.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import ir.ngra.automation.views.customs.ML_Button;
import pl.droidsonroids.gif.GifImageView;

public class Splash extends FR_Latifi implements FR_Latifi.fragmentActions {


    private VM_Splash vm_splash;

    @BindView(R.id.gifImageViewLoading)
    GifImageView gifImageViewLoading;

    @BindView(R.id.ml_ButtonReTry)
    ML_Button ml_ButtonReTry;

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
        gifImageViewLoading.setVisibility(View.VISIBLE);
        ml_ButtonReTry.setVisibility(View.GONE);
        vm_splash.callHI();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ getActionFromObservable
    @Override
    public void getActionFromObservable(Byte action) {

        if (action.equals(ObservableActions.goToLogin)) {
            getNavController().navigate(R.id.action_splash_to_login);
            return;
        }

        if (action.equals(ObservableActions.gotoHome)) {

            return;
        }

        if (action.equals(ObservableActions.gotoUpdate)) {

            Bundle bundle = new Bundle();
            bundle.putString(getResources().getString(R.string.ML_ApplicationId), "ir.ngra.automation");
            bundle.putString(getResources().getString(R.string.ML_AppName), getContext().getResources().getString(R.string.app_name));
            bundle.putString(getResources().getString(R.string.ML_UpdateUrl), vm_splash.getMd_hi().getApplicationUrl());
            bundle.putString(getResources().getString(R.string.ML_UpdateFileName), vm_splash.getMd_hi().getFileName());
            getNavController().navigate(R.id.action_splash_to_update, bundle);

        }

    }
    //______________________________________________________________________________________________ getActionFromObservable


    //______________________________________________________________________________________________ actionWhenFailureRequest
    @Override
    public void actionWhenFailureRequest() {
        gifImageViewLoading.setVisibility(View.GONE);
        ml_ButtonReTry.setVisibility(View.VISIBLE);
    }
    //______________________________________________________________________________________________ actionWhenFailureRequest


    //______________________________________________________________________________________________ OnBackPress
    @Override
    public void OnBackPress() {

    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {

        ml_ButtonReTry.setOnClickListener(v -> {
            gifImageViewLoading.setVisibility(View.VISIBLE);
            ml_ButtonReTry.setVisibility(View.GONE);
            vm_splash.callHI();
        });
    }
    //______________________________________________________________________________________________ setOnClicks


}
