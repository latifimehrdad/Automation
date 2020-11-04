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
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.viewmodels.VM_NewEditTime;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.application.AutomationApp;

public class NewEditTime extends Primary implements Primary.fragmentActions {


    private VM_NewEditTime vm_New_editTime;

    private Byte editTimeType;

    @BindView(R.id.ml_EditTextArrivalDate)
    ML_EditText ml_EditTextArrivalDate;

    @BindView(R.id.ml_EditTextDepartureDate)
    ML_EditText ml_EditTextDepartureDate;

    @BindView(R.id.ml_EditTextArrivalTime)
    ML_EditText ml_EditTextArrivalTime;

    @BindView(R.id.ml_EditTextDepartureTime)
    ML_EditText ml_EditTextDepartureTime;

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

        ml_ButtonSend.stopLoading();

        if (action.equals(ObservableActions.getEditTime)) {
            setVariableToNavigation(getResources().getString(R.string.ML_EditTime), "Done");
            removeCallBackAndBack();
        }
    }
    //______________________________________________________________________________________________ getActionFromObservable


    //______________________________________________________________________________________________ actionWhenFailureRequest
    @Override
    public void actionWhenFailureRequest() {
        ml_ButtonSend.stopLoading();
    }
    //______________________________________________________________________________________________ actionWhenFailureRequest


    //______________________________________________________________________________________________ OnBackPress
    @Override
    public void OnBackPress() {
        setVariableToNavigation(getResources().getString(R.string.ML_EditTime), "Back");
        removeCallBackAndBack();
    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setTitleAndInputs
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setTitleAndInputs() {

        if (editTimeType.equals(AttendanceType.ArrivalAndDeparture)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeAttendanceTime), getResources().getDrawable(R.drawable.ic_edit_time));
            ml_EditTextArrivalDate.setVisibility(View.VISIBLE);
            ml_EditTextArrivalTime.setVisibility(View.VISIBLE);
            ml_EditTextDepartureDate.setVisibility(View.VISIBLE);
            ml_EditTextDepartureTime.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.VISIBLE);
        } else if (editTimeType.equals(AttendanceType.Arrival)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeArrivalTime), getResources().getDrawable(R.drawable.ic_edit_time));
            ml_EditTextArrivalDate.setVisibility(View.VISIBLE);
            ml_EditTextArrivalTime.setVisibility(View.VISIBLE);
            ml_EditTextDepartureDate.setVisibility(View.GONE);
            ml_EditTextDepartureTime.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
            Date date = new Date();
            AutomationApp.getAutomationApp(getContext()).setDate(ml_EditTextArrivalDate, date, getResources().getString(R.string.arrivalDate));
        } else if (editTimeType.equals(AttendanceType.Departure)) {
            MainActivity.showTitle(getContext(), getResources().getString(R.string.changeDepartureTime), getResources().getDrawable(R.drawable.ic_edit_time));
            ml_EditTextArrivalDate.setVisibility(View.GONE);
            ml_EditTextArrivalTime.setVisibility(View.GONE);
            ml_EditTextDepartureDate.setVisibility(View.VISIBLE);
            ml_EditTextDepartureTime.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.GONE);
            Date date = new Date();
            AutomationApp.getAutomationApp(getContext()).setDate(ml_EditTextDepartureDate, date, getResources().getString(R.string.departureDate));
        }
    }
    //______________________________________________________________________________________________ setTitleAndInputs


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {

        ml_EditTextArrivalDate.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseDate(
                        getContext(),
                        ml_EditTextArrivalDate,
                        getResources().getString(R.string.arrivalDate)));

        ml_EditTextDepartureDate.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseDate(
                        getContext(),
                        ml_EditTextDepartureDate,
                        getResources().getString(R.string.departureDate)));

        ml_EditTextArrivalTime.setOnClickListener(v ->
                AutomationApp.getAutomationApp(getContext()).chooseTime(
                        getContext(),
                        ml_EditTextArrivalTime,
                        getResources().getString(R.string.chooseArrivalTime),
                        getResources().getString(R.string.arrivalTime)));

        ml_EditTextDepartureTime.setOnClickListener(v ->
                AutomationApp.getAutomationApp(
                        getContext()).chooseTime(getContext(),
                        ml_EditTextDepartureTime,
                        getResources().getString(R.string.chooseDepartureTime),
                        getResources().getString(R.string.departureTime)));

        ml_ButtonSend.setOnClickListener(v -> sendRequest());

    }
    //______________________________________________________________________________________________ setOnClicks


    //______________________________________________________________________________________________ sendRequest()
    private void sendRequest() {

        if (checkValidation()) {
            ml_ButtonSend.startLoading();
            if (ml_EditTextArrivalDate.getAdditionalValue() != null && ml_EditTextArrivalTime.getAdditionalValue() != null)
                vm_New_editTime.setFromDate(ml_EditTextArrivalDate.getAdditionalValue().toString(), ml_EditTextArrivalTime.getAdditionalValue().toString());
            if (ml_EditTextDepartureDate.getAdditionalValue() != null && ml_EditTextDepartureTime.getAdditionalValue() != null)
                vm_New_editTime.setToDate(ml_EditTextDepartureDate.getAdditionalValue().toString(), ml_EditTextDepartureTime.getAdditionalValue().toString());
            vm_New_editTime.requestEditTime(editTimeType);

        }
    }
    //______________________________________________________________________________________________ sendRequest()


    //______________________________________________________________________________________________ checkValidation
    private boolean checkValidation() {

        if (editTimeType.equals(AttendanceType.ArrivalAndDeparture)) {

            if (!ml_EditTextArrivalDate.checkValidation())
                ml_EditTextArrivalDate.setErrorLayout(getResources().getString(R.string.errorEmptyArrivalDate));

            if (!ml_EditTextArrivalTime.checkValidation())
                ml_EditTextArrivalTime.setErrorLayout(getResources().getString(R.string.errorEmptyArrivalTime));

            if (!ml_EditTextDepartureDate.checkValidation())
                ml_EditTextDepartureDate.setErrorLayout(getResources().getString(R.string.errorEmptyDepartureDate));

            if (!ml_EditTextDepartureTime.checkValidation())
                ml_EditTextDepartureTime.setErrorLayout(getResources().getString(R.string.errorEmptyDepartureTime));


            return ml_EditTextArrivalDate.checkValidation() &&
                    ml_EditTextArrivalTime.checkValidation() &&
                    ml_EditTextDepartureDate.checkValidation() &&
                    ml_EditTextDepartureTime.checkValidation();
        } else if (editTimeType.equals(AttendanceType.Arrival)) {

            if (!ml_EditTextArrivalDate.checkValidation())
                ml_EditTextArrivalDate.setErrorLayout(getResources().getString(R.string.errorEmptyArrivalDate));

            if (!ml_EditTextArrivalTime.checkValidation())
                ml_EditTextArrivalTime.setErrorLayout(getResources().getString(R.string.errorEmptyArrivalTime));


            return ml_EditTextArrivalDate.checkValidation() &&
                    ml_EditTextArrivalTime.checkValidation();
        } else {

            if (!ml_EditTextArrivalDate.checkValidation())
                ml_EditTextArrivalDate.setErrorLayout(getResources().getString(R.string.errorEmptyArrivalDate));

            if (!ml_EditTextArrivalTime.checkValidation())
                ml_EditTextArrivalTime.setErrorLayout(getResources().getString(R.string.errorEmptyArrivalTime));


            return ml_EditTextDepartureDate.checkValidation() &&
                    ml_EditTextDepartureTime.checkValidation();
        }

    }
    //______________________________________________________________________________________________ checkValidation


}
