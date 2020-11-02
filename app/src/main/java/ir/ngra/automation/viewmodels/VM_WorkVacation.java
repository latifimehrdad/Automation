package ir.ngra.automation.viewmodels;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_WorkVacation;
import ir.ngra.automation.models.MR_WorkVacation;
import ir.ngra.automation.utility.AttendanceType;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_WorkVacation extends VM_Latifi {


    private List<MD_WorkVacation> md_workVacationList;

    //______________________________________________________________________________________________ VM_WorkVacation
    public VM_WorkVacation(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_WorkVacation



    //______________________________________________________________________________________________ getWorkVacation
    public void getWorkVacation() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getRequestsWorkVacation(AttendanceType.Vacation, authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_WorkVacation>() {
            @Override
            public void onResponse(Call<MR_WorkVacation> call, Response<MR_WorkVacation> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_workVacationList = response.body().getResult();
                    sendActionToObservable(ObservableActions.getWorkVacationList);
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_WorkVacation> call, Throwable t) {
                onFailureRequest();
            }
        });

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
