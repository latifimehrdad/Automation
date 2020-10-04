package ir.ngra.automation.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.LoginBinding;
import ir.ngra.automation.viewmodels.VM_Login;
import ir.ngra.automation.views.customs.ML_Button;
import ir.ngra.automation.views.customs.ML_EditText;


public class Login extends FR_Latifi implements FR_Latifi.fragmentActions{


    private VM_Login vm_login;

    @BindView(R.id.linearLayoutRefresh)
    ML_Button linearLayoutRefresh;

    @BindView(R.id.ML_EditTextTest)
    ML_EditText ML_EditTextTest;



    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_login = new VM_Login(getActivity());
            LoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.login, container, false);
            binding.setLogin(vm_login);
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
        setPublishSubjectFromObservable(Login.this, vm_login);
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

    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {


        linearLayoutRefresh.setOnClickListener(v -> {
            if (!ML_EditTextTest.checkValidation()) {
                ML_EditTextTest.setErrorLayout("متن نمایید");
            }
/*            if (vm_login.getNationalCode() == null || vm_login.getNationalCode().equalsIgnoreCase("")) {
                ML_EditTextTest.setErrorLayout("کد را وارد نمایید");
            } else {
                Toast.makeText(getContext(), vm_login.getNationalCode(), Toast.LENGTH_SHORT).show();
            }*/
            //vm_login.notifyChange();
        });
    }
    //______________________________________________________________________________________________ setOnClicks



}
