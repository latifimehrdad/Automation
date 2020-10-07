package ir.ngra.automation.viewmodels;

import android.app.Activity;
import android.os.Handler;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.utility.ObservableActions;

public class VM_Verify extends VM_Latifi {

    private String phoneNumber;

    //______________________________________________________________________________________________ VM_Verify
    public VM_Verify(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Verify


    //______________________________________________________________________________________________ sendNumber
    public void sendNumber() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            setResponseMessage("کد ارسال شد");
            sendActionToObservable(ObservableActions.gotoVerify);
        },2000);
    }
    //______________________________________________________________________________________________ sendNumber


    //______________________________________________________________________________________________ getPhoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }
    //______________________________________________________________________________________________ getPhoneNumber



    //______________________________________________________________________________________________ setPhoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //______________________________________________________________________________________________ setPhoneNumber



}
