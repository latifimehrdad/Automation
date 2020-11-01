package ir.ngra.automation.viewmodels;

import android.app.Activity;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MR_Primary;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Login extends VM_Primary {


    private String phoneNumber;

    //______________________________________________________________________________________________ VM_Login
    public VM_Login(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Login



    //______________________________________________________________________________________________ getLoginCode
    public void getLoginCode() {

        setPhoneNumber(getUtility().persianToEnglish(getPhoneNumber()));

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .LoginCode(AutomationApp.client_id_value,
                        AutomationApp.client_secret_value,
                        getPhoneNumber(),
                        authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_Primary>() {
            @Override
            public void onResponse(Call<MR_Primary> call, Response<MR_Primary> response) {
                setResponseMessage(checkResponse(response, false));
                if (getResponseMessage() == null) {
                    setResponseMessage(getResponseMessages(response.body().getMessages()));
                    sendActionToObservable(ObservableActions.gotoVerify);
                } else {
                    sendActionToObservable(StaticValues.ML_ResponseError);
                }
            }

            @Override
            public void onFailure(Call<MR_Primary> call, Throwable t) {
                onFailureRequest();
            }
        });

    }
    //______________________________________________________________________________________________ getLoginCode




    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
