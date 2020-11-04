package ir.ngra.automation.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_EditText;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.NewEditTimeBinding;
import ir.ngra.automation.utility.AttendanceType;
import ir.ngra.automation.viewmodels.VM_NewEditTime;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.application.AutomationApp;

public class NewEditTime extends Primary implements Primary.fragmentActions {


    private VM_NewEditTime vm_New_editTime;

    private Byte editTimeType;

    @BindView(R.id.ml_EditTextStartDate)
    ML_EditText ml_EditTextStartDate;

    @BindView(R.id.ml_EditTextEndDate)
    ML_EditText ml_EditTextEndDate;

    @BindView(R.id.ml_EditTextStartTime)
    ML_EditText ml_EditTextStartTime;

    @BindView(R.id.ml_EditTextEndTime)
    ML_EditText ml_EditTextEndTime;

    @BindView(R.id.ml_EditTextDescription)
    ML_EditText ml_EditTextDescription;

    @BindView(R.id.ml_ButtonSend)
    ML_Button ml_ButtonSend;

    @BindView(R.id.linearLayout6)
    LinearLayout linearLayout6;


    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_New_editTime = new VM_NewEditTime(getActivity());
            NewEditTimeBinding binding = DataBindingUtil.inflate(inflater, R.layout.new_edit_time, container, false);
            binding.setEditTime(vm_New_editTime);
            setView(binding.getRoot());
            ButterKnife.bind(this, getView());
            setOnClicks();
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onCreateView
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(NewEditTime.this, vm_New_editTime);
        if (getArguments() != null) {
            editTimeType = getArguments().getByte(getResources().getString(R.string.ML_EditTime), AttendanceType.ArrivalAndDeparture);
            setTitleAndInputs();
        }
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


    //______________________________________________________________________________________________ setTitleAndInputs
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setTitleAndInputs() {

        if (editTimeType.equals(AttendanceType.ArrivalAndDeparture)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeAttendanceTime), getResources().getDrawable(R.drawable.ic_edit_time));
            ml_EditTextStartDate.setVisibility(View.VISIBLE);
            ml_EditTextStartTime.setVisibility(View.VISIBLE);
            ml_EditTextEndDate.setVisibility(View.VISIBLE);
            ml_EditTextEndTime.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.VISIBLE);
        } else if (editTimeType.equals(AttendanceType.Arrival)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeArrivalTime), getResources().getDrawable(R.drawable.ic_edit_time));
            ml_EditTextStartDate.setVisibility(View.VISIBLE);
            ml_EditTextStartTime.setVisibility(View.VISIBLE);
            ml_EditTextEndDate.setVisibility(View.GONE);
            ml_EditTextEndTime.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
            Date date = new Date();
            AutomationApp.getAutomationApp(getContext()).setDate(ml_EditTextStartDate,date ,getResources().getString(R.string.arrivalDate));
        } else if (editTimeType.equals(AttendanceType.Departure)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeDepartureTime), getResources().getDrawable(R.drawable.ic_edit_time));
            ml_EditTextStartDate.setVisibility(View.GONE);
            ml_EditTextStartTime.setVisibility(View.GONE);
            ml_EditTextEndDate.setVisibility(View.VISIBLE);
            ml_EditTextEndTime.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.GONE);
            Date date = new Date();
            AutomationApp.getAutomationApp(getContext()).setDate(ml_EditTextEndDate,date ,getResources().getString(R.string.departureDate));
        }
    }
    //______________________________________________________________________________________________ setTitleAndInputs


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {

        ml_EditTextStartDate.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseDate(
                        getContext(),
                        ml_EditTextStartDate,
                        getResources().getString(R.string.arrivalDate)));

        ml_EditTextEndDate.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseDate(
                        getContext(),
                        ml_EditTextEndDate,
                        getResources().getString(R.string.departureDate)));

        ml_EditTextStartTime.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseTime(
                        getContext(),
                        ml_EditTextStartTime,
                        getResources().getString(R.string.chooseArrivalTime),
                        getResources().getString(R.string.arrivalTime)));

        ml_EditTextEndTime.setOnClickListener(v ->
                AutomationApp.getAutomationApp(
                        getContext()).chooseTime(getContext(),
                        ml_EditTextEndTime,
                        getResources().getString(R.string.chooseDepartureTime),
                        getResources().getString(R.string.departureTime)));

        ml_ButtonSend.setOnClickListener(v -> sendRequest());

    }
    //______________________________________________________________________________________________ setOnClicks


    //______________________________________________________________________________________________ sendRequest()
    private void sendRequest() {

        if (checkValidation()) {
            ml_ButtonSend.startLoading();
/*            vm_New_workVacation.setFromDate(ml_EditTextStartDate.getAdditionalValue().toString(), ml_EditTextStartTime.getAdditionalValue().toString());
            vm_New_workVacation.setToDate(ml_EditTextEndDate.getAdditionalValue().toString(), ml_EditTextEndTime.getAdditionalValue().toString());
            vm_New_workVacation.requestLeave();*/

        }
    }
    //______________________________________________________________________________________________ sendRequest()


    //______________________________________________________________________________________________ checkValidation
    private boolean checkValidation() {

        if (!ml_EditTextStartDate.checkValidation())
            ml_EditTextStartDate.setErrorLayout(getResources().getString(R.string.errorEmptyStartDate));

        if (!ml_EditTextStartTime.checkValidation())
            ml_EditTextStartTime.setErrorLayout(getResources().getString(R.string.errorEmptyStartTime));

        if (!ml_EditTextEndDate.checkValidation())
            ml_EditTextEndDate.setErrorLayout(getResources().getString(R.string.errorEmptyEndDate));

        if (!ml_EditTextEndTime.checkValidation())
            ml_EditTextEndTime.setErrorLayout(getResources().getString(R.string.errorEmptyEndTime));


        return ml_EditTextStartDate.checkValidation() &&
                ml_EditTextStartTime.checkValidation() &&
                ml_EditTextEndDate.checkValidation() &&
                ml_EditTextEndTime.checkValidation();
    }
    //______________________________________________________________________________________________ checkValidation


}
