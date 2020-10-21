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

import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_EditText;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.NewWorkVacationBinding;
import ir.ngra.automation.viewmodels.VM_NewWorkVacation;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.application.AutomationApp;

public class NewWorkVacation extends Primary implements Primary.fragmentActions {


    private VM_NewWorkVacation vm_New_workVacation;

    @BindView(R.id.ml_EditTextWorkVacation)
    ML_EditText ml_EditTextWorkVacation;

    @BindView(R.id.ml_EditTextSubstitute)
    ML_EditText ml_EditTextSubstitute;

    @BindView(R.id.ml_EditTextStartDate)
    ML_EditText ml_EditTextStartDate;

    @BindView(R.id.ml_EditTextEndDate)
    ML_EditText ml_EditTextEndDate;

    @BindView(R.id.ml_EditTextStartTime)
    ML_EditText ml_EditTextStartTime;

    @BindView(R.id.ml_EditTextEndTime)
    ML_EditText ml_EditTextEndTime;

    @BindView(R.id.ml_ButtonSend)
    ML_Button ml_ButtonSend;


    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_New_workVacation = new VM_NewWorkVacation(getActivity());
            NewWorkVacationBinding binding = DataBindingUtil.inflate(inflater, R.layout.new_work_vacation, container, false);
            binding.setWorkVacation(vm_New_workVacation);
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
        setPublishSubjectFromObservable(NewWorkVacation.this, vm_New_workVacation);
        MainActivity.showTitle(getContext(), getResources().getString(R.string.newWorkVacation), getResources().getDrawable(R.drawable.ic_camping));
        vm_New_workVacation.getWorkVacationType();
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


        ml_EditTextWorkVacation.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).showSpinner(
                        getContext(),
                        ml_EditTextWorkVacation,
                        vm_New_workVacation.getWorkVacationTypeList(),
                        getResources().getString(R.string.searchWorkVacationType),
                        getResources().getString(R.string.workVacationType)));


        ml_EditTextSubstitute.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).showSpinner(
                        getContext(),
                        ml_EditTextSubstitute,
                        vm_New_workVacation.getSubstituteList(),
                        getResources().getString(R.string.searchSubstitute),
                        getResources().getString(R.string.substitute)));


        ml_EditTextStartDate.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseDate(
                        getContext(),
                        ml_EditTextStartDate,
                        getResources().getString(R.string.startDate)));

        ml_EditTextEndDate.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseDate(
                        getContext(),
                        ml_EditTextEndDate,
                        getResources().getString(R.string.endDate)));

        ml_EditTextStartTime.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseTime(
                        getContext(),
                        ml_EditTextStartTime,
                        getResources().getString(R.string.chooseStartTime),
                        getResources().getString(R.string.startTime)));

        ml_EditTextEndTime.setOnClickListener(v ->
                AutomationApp.getAutomationApp(
                        getContext()).chooseTime(getContext(),
                        ml_EditTextEndTime,
                        getResources().getString(R.string.chooseEndTime),
                        getResources().getString(R.string.endTime)));

        ml_ButtonSend.setOnClickListener(v -> sendRequest());

    }
    //______________________________________________________________________________________________ setOnClicks


    //______________________________________________________________________________________________ sendRequest()
    private void sendRequest() {

        if (checkValidation()) {

        }
    }
    //______________________________________________________________________________________________ sendRequest()


    //______________________________________________________________________________________________ checkValidation
    private boolean checkValidation() {

        if (!ml_EditTextWorkVacation.checkValidation())
            ml_EditTextWorkVacation.setErrorLayout(getResources().getString(R.string.errorEmptyWorkVacationType));

        if (!ml_EditTextSubstitute.checkValidation())
            ml_EditTextSubstitute.setErrorLayout(getResources().getString(R.string.errorEmptySubstitute));

        if (!ml_EditTextStartDate.checkValidation())
            ml_EditTextStartDate.setErrorLayout(getResources().getString(R.string.errorEmptyStartDate));

        if (!ml_EditTextStartTime.checkValidation())
            ml_EditTextStartTime.setErrorLayout(getResources().getString(R.string.errorEmptyStartTime));

        if (!ml_EditTextEndDate.checkValidation())
            ml_EditTextEndDate.setErrorLayout(getResources().getString(R.string.errorEmptyEndDate));

        if (!ml_EditTextEndTime.checkValidation())
            ml_EditTextEndTime.setErrorLayout(getResources().getString(R.string.errorEmptyEndTime));


        return ml_EditTextWorkVacation.checkValidation() &&
                ml_EditTextSubstitute.checkValidation() &&
                ml_EditTextStartDate.checkValidation() &&
                ml_EditTextStartTime.checkValidation() &&
                ml_EditTextEndDate.checkValidation() &&
                ml_EditTextEndTime.checkValidation();
    }
    //______________________________________________________________________________________________ checkValidation

}
