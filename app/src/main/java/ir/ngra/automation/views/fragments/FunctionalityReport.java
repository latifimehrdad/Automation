package ir.ngra.automation.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import ir.ngra.automation.R;
import ir.ngra.automation.databinding.FunctionalityReportBinding;
import ir.ngra.automation.viewmodels.VM_FunctionalityReport;
import ir.ngra.automation.views.activity.MainActivity;

public class FunctionalityReport extends Primary implements Primary.fragmentActions {


    private VM_FunctionalityReport vm_functionalityReport;

    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_functionalityReport = new VM_FunctionalityReport(getContext());
            FunctionalityReportBinding binding = DataBindingUtil.inflate(inflater, R.layout.functionality_report, container, false);
            binding.setFunctionality(vm_functionalityReport);
            setView(binding.getRoot());
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
        removeCallBackAndBack();
    }
    //______________________________________________________________________________________________ OnBackPress


}
