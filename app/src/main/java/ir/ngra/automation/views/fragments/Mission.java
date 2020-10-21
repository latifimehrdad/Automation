package ir.ngra.automation.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import butterknife.ButterKnife;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.MissionBinding;
import ir.ngra.automation.viewmodels.VM_Mission;
import ir.ngra.automation.views.activity.MainActivity;

public class Mission extends Primary implements Primary.fragmentActions {


    private VM_Mission vm_mission;


    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_mission = new VM_Mission(getActivity());
            MissionBinding binding = DataBindingUtil.inflate(inflater, R.layout.mission, container, false);
            binding.setMission(vm_mission);
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
        setPublishSubjectFromObservable(Mission.this, vm_mission);
        MainActivity.showTitle(getContext(), getResources().getString(R.string.missions), getResources().getDrawable(R.drawable.ic_businessman));
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
    @Override
    public void OnBackPress() {
        removeCallBackAndBack();
    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {


    }
    //______________________________________________________________________________________________ setOnClicks

}