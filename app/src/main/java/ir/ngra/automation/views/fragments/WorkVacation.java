package ir.ngra.automation.views.fragments;

import android.os.Bundle;
import android.util.Log;
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
import ir.ngra.automation.databinding.WorkVacationBinding;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.viewmodels.VM_WorkVacation;
import ir.ngra.automation.views.adapter.AP_WorkVacation;

public class WorkVacation extends Primary implements Primary.fragmentActions {


    private VM_WorkVacation vm_workVacation;

    @BindView(R.id.ml_ButtonNew)
    ML_Button ml_ButtonNew;

    @BindView(R.id.textViewNoRequest)
    TextView textViewNoRequest;

    @BindView(R.id.recyclerViewWorkVacation)
    RecyclerView recyclerViewWorkVacation;


    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_workVacation = new VM_WorkVacation(getActivity());
            WorkVacationBinding binding = DataBindingUtil.inflate(inflater, R.layout.work_vacation, container, false);
            binding.setWorkVacation(vm_workVacation);
            setView(binding.getRoot());
            ButterKnife.bind(this, getView());
            setOnClicksAndListener();
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onCreateView
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(WorkVacation.this, vm_workVacation);
        getWorkVacationList();

    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ getActionFromObservable
    @Override
    public void getActionFromObservable(Byte action) {

        if (action.equals(ObservableActions.getWorkVacationList)) {
            //setAdapter();
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



    //______________________________________________________________________________________________ getWorkVacationList
    private void getWorkVacationList() {

        textViewNoRequest.setVisibility(View.GONE);
        setRecyclerLoading(recyclerViewWorkVacation, R.layout.adapter_work_vacation_loading);
        vm_workVacation.getWorkVacation();
    }
    //______________________________________________________________________________________________ getWorkVacationList



    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicksAndListener() {

        ml_ButtonNew.setOnClickListener(v -> getNavController().navigate(R.id.action_workVacation_to_newWorkVacation));

        recyclerViewWorkVacation.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy  >= 2) {
                    if (ml_ButtonNew.getVisibility() == View.VISIBLE) {
                        ml_ButtonNew.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom));
                        ml_ButtonNew.setVisibility(View.GONE);
                    }
                    // Scrolling up
                } else if (dy <= -2){
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
        if (vm_workVacation.getMd_workVacationList().size() > 0) {
            AP_WorkVacation ap_workVacation = new AP_WorkVacation(vm_workVacation.getMd_workVacationList());
            recyclerViewWorkVacation.setAdapter(ap_workVacation);
            textViewNoRequest.setVisibility(View.GONE);
        } else {
            textViewNoRequest.setVisibility(View.VISIBLE);
        }
    }
    //______________________________________________________________________________________________ setAdapter



}