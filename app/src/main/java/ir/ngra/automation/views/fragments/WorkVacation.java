package ir.ngra.automation.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.WorkVacationBinding;
import ir.ngra.automation.models.MD_WorkVacation;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.viewmodels.VM_WorkVacation;
import ir.ngra.automation.views.adapter.AP_WorkVacation;

public class WorkVacation extends FR_Latifi implements FR_Latifi.fragmentActions {


    private VM_WorkVacation vm_workVacation;
    private RecyclerViewSkeletonScreen skeletonScreen;
    private AP_WorkVacation ap_workVacation;

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
            setOnClicks();
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



    //______________________________________________________________________________________________ getWorkVacationList
    private void getWorkVacationList() {

        textViewNoRequest.setVisibility(View.GONE);
        ap_workVacation = new AP_WorkVacation(vm_workVacation.getMd_workVacationList());
        recyclerViewWorkVacation.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        skeletonScreen = Skeleton.bind(recyclerViewWorkVacation)
                .adapter(ap_workVacation)
                .load(R.layout.adapter_work_vacation)
                .shimmer(true)      // whether show shimmer animation.                      default is true
                .count(10)          // the recycler view item count.                        default is 10
                .color(R.color.ML_recyclerLoading)       // the shimmer color.                                   default is #a2878787
                .angle(20)          // the shimmer angle.                                   default is 20;
                .duration(1200)     // the shimmer animation duration.                      default is 1000;
                .frozen(false)
                .show();
        vm_workVacation.getWorkVacation();
    }
    //______________________________________________________________________________________________ getWorkVacationList



    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {

        ml_ButtonNew.setOnClickListener(v -> getNavController().navigate(R.id.action_workVacation_to_newWorkVacation));

    }
    //______________________________________________________________________________________________ setOnClicks



    //______________________________________________________________________________________________ setAdapter
    private void setAdapter() {

        skeletonScreen.hide();
        if (vm_workVacation.getMd_workVacationList().size() > 0) {
            ap_workVacation = new AP_WorkVacation(vm_workVacation.getMd_workVacationList());
            recyclerViewWorkVacation.setAdapter(ap_workVacation);
            textViewNoRequest.setVisibility(View.GONE);
        } else {
            textViewNoRequest.setVisibility(View.VISIBLE);
        }
    }
    //______________________________________________________________________________________________ setAdapter



}