package ir.ngra.automation.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.MissionBinding;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.viewmodels.VM_Mission;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.adapter.AP_Mission;

public class Mission extends Primary implements Primary.fragmentActions {


    private VM_Mission vm_mission;


    @BindView(R.id.ml_ButtonNew)
    ML_Button ml_ButtonNew;

    @BindView(R.id.textViewNoRequest)
    TextView textViewNoRequest;

    @BindView(R.id.recyclerViewMission)
    RecyclerView recyclerViewMission;


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
            setOnClicksAndListener();

        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onCreateView
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(Mission.this, vm_mission);
        MainActivity.showTitle(getContext(), getResources().getString(R.string.missions), getResources().getDrawable(R.drawable.ic_businessman));
        getMissionList();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ getActionFromObservable
    @Override
    public void getActionFromObservable(Byte action) {
        if (action.equals(ObservableActions.getMissionList)) {
            setAdapter();
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
        removeCallBackAndBack();
    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ getMissionList
    private void getMissionList() {

        textViewNoRequest.setVisibility(View.GONE);
        setRecyclerLoading(recyclerViewMission, R.layout.adapter_work_vacation_loading);
        vm_mission.getMission();
    }
    //______________________________________________________________________________________________ getMissionList


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicksAndListener() {

        ml_ButtonNew.setOnClickListener(v -> gotoFragment(R.id.action_mission_to_newMission, null));

        recyclerViewMission.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy >= 2) {
                    if (ml_ButtonNew.getVisibility() == View.VISIBLE) {
                        ml_ButtonNew.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom));
                        ml_ButtonNew.setVisibility(View.GONE);
                    }
                    // Scrolling up
                } else if (dy <= -2) {
                    if (ml_ButtonNew.getVisibility() == View.GONE) {
                        ml_ButtonNew.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom));
                        ml_ButtonNew.setVisibility(View.VISIBLE);
                    }
                    // Scrolling down
                }
            }

        });

    }
    //______________________________________________________________________________________________ setOnClicks


    //______________________________________________________________________________________________ setAdapter
    private void setAdapter() {

        stopLoadingRecycler();
        if (vm_mission.getMd_MissionList().size() > 0) {
            AP_Mission adapter = new AP_Mission(vm_mission.getMd_MissionList());
            recyclerViewMission.setAdapter(adapter);
            textViewNoRequest.setVisibility(View.GONE);
        } else {
            textViewNoRequest.setVisibility(View.VISIBLE);
        }
    }
    //______________________________________________________________________________________________ setAdapter


}