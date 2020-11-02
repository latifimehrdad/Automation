package ir.ngra.automation.views.fragments;

import android.annotation.SuppressLint;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.FunctionalityReportBinding;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.viewmodels.VM_FunctionalityReport;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.adapter.AP_FunctionalityReport;

public class FunctionalityReport extends Primary implements Primary.fragmentActions {


    private VM_FunctionalityReport vm_functionalityReport;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.textViewNoItemForShow)
    TextView textViewNoItemForShow;


    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_functionalityReport = new VM_FunctionalityReport(getContext());
            FunctionalityReportBinding binding = DataBindingUtil.inflate(inflater, R.layout.functionality_report, container, false);
            binding.setFunctionality(vm_functionalityReport);
            setView(binding.getRoot());
            ButterKnife.bind(this, getView());
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onCreateView
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(FunctionalityReport.this, vm_functionalityReport);
        MainActivity.showTitle(getContext(), getResources().getString(R.string.functionalityReport), getResources().getDrawable(R.drawable.ic_functionality_report));
        getFunctionalityReport();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ getActionFromObservable
    @Override
    public void getActionFromObservable(Byte action) {

        if (action.equals(ObservableActions.getFunctionalityReport))
            setAdapter();
    }
    //______________________________________________________________________________________________ getActionFromObservable


    //______________________________________________________________________________________________ actionWhenFailureRequest
    @Override
    public void actionWhenFailureRequest() {
        noItemForShow();
    }
    //______________________________________________________________________________________________ actionWhenFailureRequest


    //______________________________________________________________________________________________ OnBackPress
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void OnBackPress() {
        removeCallBackAndBack();
    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setAdapter
    private void setAdapter() {

        if (vm_functionalityReport.getMd_functionalityReports().size() == 0) {
            noItemForShow();
        } else {
            AP_FunctionalityReport adapter = new AP_FunctionalityReport(vm_functionalityReport.getMd_functionalityReports());
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    }
    //______________________________________________________________________________________________ setAdapter


    //______________________________________________________________________________________________ getFunctionalityReport
    private void getFunctionalityReport() {

        textViewNoItemForShow.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        setRecyclerLoading(recyclerView, R.layout.adapter_work_vacation_loading);
        vm_functionalityReport.getDailyItems();
    }
    //______________________________________________________________________________________________ getFunctionalityReport


    //______________________________________________________________________________________________ noItemForShow
    private void noItemForShow() {
        textViewNoItemForShow.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
    //______________________________________________________________________________________________ noItemForShow

}
