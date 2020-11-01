package ir.ngra.automation.viewmodels;

import android.app.Activity;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_DailyItems;
import ir.ngra.automation.models.MR_DailyItems;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Home extends VM_Latifi {


    private MD_DailyItems md_dailyItems;

    //______________________________________________________________________________________________ VM_Home
    public VM_Home(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Home



    //______________________________________________________________________________________________ getTodayEntrance
    public void getTodayEntrance() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getTodayEntrance(authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_DailyItems>() {
            @Override
            public void onResponse(Call<MR_DailyItems> call, Response<MR_DailyItems> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_dailyItems = response.body().getResult();
                    notifyChange();
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_DailyItems> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ getTodayEntrance



    //______________________________________________________________________________________________ getMd_todayEntrance
    public MD_DailyItems getMd_dailyItems() {
        if (md_dailyItems == null)
            md_dailyItems = new MD_DailyItems(null, null);
        return md_dailyItems;
    }
    //______________________________________________________________________________________________ getMd_todayEntrance


}
