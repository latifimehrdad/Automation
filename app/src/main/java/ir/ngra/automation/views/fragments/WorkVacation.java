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
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.WorkVacationBinding;
import ir.ngra.automation.viewmodels.VM_WorkVacation;
import ir.ngra.automation.views.customs.searchspinner.MLSpinnerDialog;

public class WorkVacation extends FR_Latifi implements FR_Latifi.fragmentActions {


    private VM_WorkVacation vm_workVacation;
    private MLSpinnerDialog spinnerWorkVacationType;
    private MLSpinnerDialog spinnerSubstitute;

/*    @BindView(R.id.ml_EditTextWorkVacation)
    ML_EditText ml_EditTextWorkVacation;

    @BindView(R.id.ml_EditTextSubstitute)
    ML_EditText ml_EditTextSubstitute;*/


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
        vm_workVacation.getWorkVacationType();
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

/*        ml_EditTextWorkVacation.setOnClickListener(v -> {

            if (spinnerWorkVacationType == null) {
                spinnerWorkVacationType = new MLSpinnerDialog(
                        getActivity(),
                        vm_workVacation.getWorkVacationTypeList(),
                        getResources().getString(R.string.searchWorkVacationType),
                        R.style.DialogAnimations_SmileWindow,
                        getResources().getString(R.string.Ignore));

                spinnerWorkVacationType.setCancellable(true); // for cancellable
                spinnerWorkVacationType.setShowKeyboard(false);// for open keyboard by default
                spinnerWorkVacationType.bindOnSpinnerListener((item, position) -> {
                    String text = getResources().getString(R.string.workVacationType) + System.getProperty("line.separator") + item;
                    ml_EditTextWorkVacation.setText(text);
                    ml_EditTextWorkVacation.removeError();
                });
            }

            spinnerWorkVacationType.showSpinnerDialog();

        });



        ml_EditTextSubstitute.setOnClickListener(v -> {

            if (spinnerSubstitute == null) {
                spinnerSubstitute = new MLSpinnerDialog(
                        getActivity(),
                        vm_workVacation.getWorkVacationTypeList(),
                        getResources().getString(R.string.searchSubstitute),
                        R.style.DialogAnimations_SmileWindow,
                        getResources().getString(R.string.Ignore));

                spinnerSubstitute.setCancellable(true); // for cancellable
                spinnerSubstitute.setShowKeyboard(false);// for open keyboard by default
                spinnerSubstitute.bindOnSpinnerListener((item, position) -> {
                    String text = getResources().getString(R.string.Substitute) + System.getProperty("line.separator") + item;
                    ml_EditTextSubstitute.setText(text);
                    ml_EditTextSubstitute.removeError();
                });
            }

            spinnerSubstitute.showSpinnerDialog();

        });*/
    }
    //______________________________________________________________________________________________ setOnClicks


}
