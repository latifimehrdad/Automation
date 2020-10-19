package ir.ngra.automation.viewmodels;

import android.app.Activity;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.models.MD_WorkVacation;
import ir.ngra.automation.utility.ObservableActions;

public class VM_WorkVacation extends VM_Latifi {


    private List<MD_WorkVacation> md_workVacationList;

    //______________________________________________________________________________________________ VM_WorkVacation
    public VM_WorkVacation(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_WorkVacation



    //______________________________________________________________________________________________ getWorkVacation
    public void getWorkVacation() {

        md_workVacationList = new ArrayList<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            for (int i = 0; i < 5; i++)
                md_workVacationList.add(new MD_WorkVacation(i));

            sendActionToObservable(ObservableActions.getWorkVacationList);
        }, 5000);

    }
    //______________________________________________________________________________________________ getWorkVacation



    //______________________________________________________________________________________________ getMd_workVacationList
    public List<MD_WorkVacation> getMd_workVacationList() {
        if (md_workVacationList == null)
            md_workVacationList = new ArrayList<>();
        return md_workVacationList;
    }
    //______________________________________________________________________________________________ getMd_workVacationList


}
