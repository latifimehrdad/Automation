package ir.ngra.automation.viewmodels;

import android.app.Activity;

import java.util.Date;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;

public class VM_Main extends VM_Latifi {

    private String sunDate;

    //______________________________________________________________________________________________ vm_Main
    public VM_Main(Activity context) {
        setContext(context);
        calSunDate();
    }
    //______________________________________________________________________________________________ vm_Main


    //______________________________________________________________________________________________ calSunDate
    private void calSunDate() {
        Date date = new Date();
        sunDate = getUtility().gregorianToSun(date).getFullStringSun();
        notifyChange();
    }
    //______________________________________________________________________________________________ calSunDate



    //______________________________________________________________________________________________ getSunDate
    public String getSunDate() {
        return sunDate;
    }
    //______________________________________________________________________________________________ getSunDate


    //______________________________________________________________________________________________ setSunDate
    public void setSunDate(String sunDate) {
        this.sunDate = sunDate;
    }
    //______________________________________________________________________________________________ setSunDate


}
