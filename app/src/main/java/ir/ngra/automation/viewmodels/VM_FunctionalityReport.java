package ir.ngra.automation.viewmodels;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_FunctionalityReport;
import ir.ngra.automation.models.MR_FunctionalityReport;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_FunctionalityReport extends VM_Primary {


    private List<MD_FunctionalityReport> md_functionalityReports;


    //______________________________________________________________________________________________ VM_FunctionalityReport
    public VM_FunctionalityReport(Activity activity) {
        setContext(activity);
    }
    //______________________________________________________________________________________________ VM_FunctionalityReport



    //______________________________________________________________________________________________ getDailyItems
    public void getDailyItems() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getDailyItems(authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_FunctionalityReport>() {
            @Override
            public void onResponse(Call<MR_FunctionalityReport> call, Response<MR_FunctionalityReport> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_functionalityReports = response.body().getResult();
                    sendActionToObservable(ObservableActions.getFunctionalityReport);
                    notifyChange();
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_FunctionalityReport> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ getDailyItems


    //______________________________________________________________________________________________ getMd_functionalityReports
    public List<MD_FunctionalityReport> getMd_functionalityReports() {
        if (md_functionalityReports == null)
            md_functionalityReports = new ArrayList<>();
        return md_functionalityReports;
    }
    //______________________________________________________________________________________________ getMd_functionalityReports


}
