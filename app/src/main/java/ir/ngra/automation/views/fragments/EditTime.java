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

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.EditTimeBinding;
import ir.ngra.automation.utility.AttendanceType;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.viewmodels.VM_EditTime;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.adapter.AP_EditTime;
import ir.ngra.automation.views.application.AutomationApp;

public class EditTime extends Primary implements Primary.fragmentActions {


    private VM_EditTime vm_editTime;

    private Byte editTimeType;

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
            vm_editTime = new VM_EditTime(getActivity());
            EditTimeBinding binding = DataBindingUtil.inflate(inflater, R.layout.edit_time, container, false);
            binding.setMission(vm_editTime);
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
        setPublishSubjectFromObservable(EditTime.this, vm_editTime);
        if (getArguments() != null) {
            editTimeType = getArguments().getByte(getResources().getString(R.string.ML_EditTime), AttendanceType.ArrivalAndDeparture);
            setTitle();
            checkEditType();
        }

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


    //______________________________________________________________________________________________ setTitle
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setTitle() {

        if (editTimeType.equals(AttendanceType.ArrivalAndDeparture)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeAttendanceTime), getResources().getDrawable(R.drawable.ic_edit_time));
        } else if (editTimeType.equals(AttendanceType.Arrival)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeArrivalTime), getResources().getDrawable(R.drawable.ic_edit_time));
        } else if (editTimeType.equals(AttendanceType.Departure)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeDepartureTime), getResources().getDrawable(R.drawable.ic_edit_time));
        }
    }
    //______________________________________________________________________________________________ setTitle


    //______________________________________________________________________________________________ checkEditType
    private void checkEditType() {
        if (editTimeType.equals(AttendanceType.ArrivalAndDeparture))
            getEditTimeList();
        else {
            String value = getVariableFromNavigation(getResources().getString(R.string.ML_EditTime));
            if (value != null) {
                setVariableToNavigation(getResources().getString(R.string.ML_EditTime), null);
                if (value.equalsIgnoreCase("Back"))
                    removeCallBackAndBack();
                else getEditTimeList();
            } else {
                getEditTimeList();
/*                Bundle bundle = new Bundle();
                bundle.putByte(getResources().getString(R.string.ML_EditTime), editTimeType);
                gotoFragment(R.id.action_editTime_to_newEditTime, bundle);*/
            }
        }
    }
    //______________________________________________________________________________________________ checkEditType


    //______________________________________________________________________________________________ getEditTimeList
    private void getEditTimeList() {

        textViewNoRequest.setVisibility(View.GONE);
        setRecyclerLoading(recyclerViewMission, R.layout.adapter_work_vacation_loading);
        vm_editTime.getEditTime(editTimeType);
    }
    //______________________________________________________________________________________________ getEditTimeList


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicksAndListener() {

        ml_ButtonNew.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putByte(getResources().getString(R.string.ML_EditTime), editTimeType);
            gotoFragment(R.id.action_editTime_to_newEditTime, bundle);;
        });

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
        if (vm_editTime.getMd_EditTimeList().size() > 0) {
            AP_EditTime adapter = new AP_EditTime(vm_editTime.getMd_EditTimeList());
            recyclerViewMission.setAdapter(adapter);
            textViewNoRequest.setVisibility(View.GONE);
        } else {
            textViewNoRequest.setVisibility(View.VISIBLE);
        }
    }
    //______________________________________________________________________________________________ setAdapter


}