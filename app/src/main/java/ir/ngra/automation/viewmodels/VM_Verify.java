package ir.ngra.automation.viewmodels;

import android.app.Activity;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_SettingInfo;
import ir.ngra.automation.models.MD_Token;
import ir.ngra.automation.models.MR_Primary;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Verify extends VM_Primary {

    private String phoneNumber;
    private String code;

    //______________________________________________________________________________________________ VM_Verify
    public VM_Verify(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Verify


    //______________________________________________________________________________________________ sendNumber
    public void sendNumber() {

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
    //______________________________________________________________________________________________ sendNumber



    //______________________________________________________________________________________________ verifyNumber
    public void verifyNumber() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .login(AutomationApp.client_id_value,
                        AutomationApp.client_secret_value,
                        AutomationApp.grant_type_value_Login_Code,
                        getPhoneNumber(),
                        getCode(),
                        authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MD_Token>() {
            @Override
            public void onResponse(Call<MD_Token> call, Response<MD_Token> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    if (AutomationApp.getAutomationApp(getContext()).saveToken(response.body()))
                        getLoginInformation();
                } else
                    sendActionToObservable(StaticValues.ML_ResponseError);
            }

            @Override
            public void onFailure(Call<MD_Token> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ verifyNumber




    //______________________________________________________________________________________________ getLoginInformation
    public void getLoginInformation() {


        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getSettingInfo(
                        authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MD_SettingInfo>() {
            @Override
            public void onResponse(Call<MD_SettingInfo> call, Response<MD_SettingInfo> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    if (AutomationApp.getAutomationApp(getContext()).saveProfile(getContext(), response.body().getResult()))
                        sendActionToObservable(ObservableActions.gotoHome);
                } else
                    sendActionToObservable(StaticValues.ML_ResponseError);
            }

            @Override
            public void onFailure(Call<MD_SettingInfo> call, Throwable t) {
                onFailureRequest();
            }
        });

    }
    //______________________________________________________________________________________________ getLoginInformation



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


    //______________________________________________________________________________________________ getCode
    public String getCode() {
        return code;
    }
    //______________________________________________________________________________________________ getCode


    //______________________________________________________________________________________________ setCode
    public void setCode(String code) {
        this.code = code;
    }
    //______________________________________________________________________________________________ setCode


}
