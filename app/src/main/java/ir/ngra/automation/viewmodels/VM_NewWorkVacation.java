package ir.ngra.automation.viewmodels;

import android.app.Activity;

import java.util.ArrayList;


import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.models.MD_SpinnerItem;


public class VM_NewWorkVacation extends VM_Latifi {


    private ArrayList<MD_SpinnerItem> workVacationTypeList;

    private ArrayList<MD_SpinnerItem> substituteList;


    //______________________________________________________________________________________________ VM_WorkVacation
    public VM_NewWorkVacation(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_WorkVacation


    //______________________________________________________________________________________________ getWorkVacationType
    public void getWorkVacationType() {
        workVacationTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            workVacationTypeList.add(new MD_SpinnerItem(i, "item " + i, ""));
        }
        substituteList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            substituteList.add(new MD_SpinnerItem(i, "item " + i, ""));
        }
    }
    //______________________________________________________________________________________________ getWorkVacationType



    //______________________________________________________________________________________________ getWorkVacationTypeList
    public ArrayList<MD_SpinnerItem> getWorkVacationTypeList() {
        if (workVacationTypeList == null)
            workVacationTypeList = new ArrayList<>();

        return workVacationTypeList;
    }
    //______________________________________________________________________________________________ getWorkVacationTypeList




    //______________________________________________________________________________________________ getSubstituteList
    public ArrayList<MD_SpinnerItem> getSubstituteList() {
        if (substituteList == null)
            substituteList = new ArrayList<>();

        return substituteList;
    }
    //______________________________________________________________________________________________ getSubstituteList



}
