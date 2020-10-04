package ir.ngra.automation.viewmodels;

import android.app.Activity;

import androidx.databinding.Bindable;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;

public class VM_Login extends VM_Latifi {


    private String nationalCode;

    //______________________________________________________________________________________________ VM_Login
    public VM_Login(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Login


    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
