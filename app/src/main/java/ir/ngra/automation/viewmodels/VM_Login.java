package ir.ngra.automation.viewmodels;

import android.app.Activity;
import android.os.Handler;

import androidx.databinding.Bindable;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.utility.ObservableActions;

public class VM_Login extends VM_Latifi {


    private String nationalCode;

    //______________________________________________________________________________________________ VM_Login
    public VM_Login(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Login



    //______________________________________________________________________________________________ sendPersonalCode
    public void sendPersonalCode() {

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            setResponseMessage("کد ارسال شد");
            sendActionToObservable(ObservableActions.gotoVerify);
        },2000);
    }
    //______________________________________________________________________________________________ sendPersonalCode




    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
