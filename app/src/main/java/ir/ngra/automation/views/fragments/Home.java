package ir.ngra.automation.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.HomeBinding;

import ir.ngra.automation.viewmodels.VM_Home;

public class Home extends Primary implements Primary.fragmentActions {

    private VM_Home vm_home;
    private boolean doubleExitApplication = false;

    @BindView(R.id.linearLayoutWorkVacation)
    LinearLayout linearLayoutWorkVacation;

    @BindView(R.id.layoutCircle)
    LinearLayout layoutCircle;

    @BindView(R.id.linearLayoutMission)
    LinearLayout linearLayoutMission;


    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_home = new VM_Home(getActivity());
            HomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.home, container, false);
            binding.setHome(vm_home);
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
        setPublishSubjectFromObservable(Home.this, vm_home);
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ getActionFromObservable
    @Override
    public void getActionFromObservable(Byte action) {


    }
    //______________________________________________________________________________________________ getActionFromObservable


    //______________________________________________________________________________________________ actionWhenFailureRequest
    @Override
    public void actionWhenFailureRequest() {

    }
    //______________________________________________________________________________________________ actionWhenFailureRequest


    //______________________________________________________________________________________________ OnBackPress
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void OnBackPress() {


        if (doubleExitApplication)
            System.exit(1);
        else {
            layoutCircle.setAlpha(0.1f);
            doubleExitApplication = true;
            showToast(
                    getResources().getString(R.string.doubleExit),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getDrawable(R.drawable.ic_exit),
                    getResources().getColor(R.color.colorPrimary));
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                doubleExitApplication = false;
                layoutCircle.setAlpha(1);
            }, 4000);
        }
    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {

        linearLayoutWorkVacation.setOnClickListener(v -> getNavController().navigate(R.id.action_home_to_workVacation));


        linearLayoutMission.setOnClickListener(v -> getNavController().navigate(R.id.action_home_to_mission));

    }
    //______________________________________________________________________________________________ setOnClicks


}
