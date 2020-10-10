package ir.ngra.automation.views.application;

import android.content.Context;
import android.content.SharedPreferences;

import ir.mlcode.latifiarchitecturelibrary.application.APP_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_SettingInfo;
import ir.ngra.automation.models.MD_Token;
import ir.ngra.automation.utility.RetrofitApiInterface;

public class AutomationApp extends APP_Latifi {

    public static String Host = "http://2.144.243.200:70";
    public static String client_id_value = "vRIzEFYjpzYwHHSUbx/ODg==";
    public static String client_secret_value = "n5r+sej/lFv7xVhM9F7+kOG9yI64d/JIkGzl0NvgwMM=";
    public static String grant_type_value = "client_credentials";
    //    public static String grant_type_password = "password";
    public static String grant_type_value_Refresh_Token = "refresh_token";
    public static String grant_type_value_Login_Code = "login_code";


    private Context context;
    private RetrofitApiInterface retrofitApiInterface;


    //______________________________________________________________________________________________ onCreate
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        setContext(context);
        setHost(Host);
        configurationRetrofit();
    }
    //______________________________________________________________________________________________ onCreate



    //______________________________________________________________________________________________ configurationRetrofit
    private void configurationRetrofit() {
        retrofitApiInterface = getRetrofitComponent().getRetrofit().create(RetrofitApiInterface.class);
    }
    //______________________________________________________________________________________________ configurationRetrofit




    //______________________________________________________________________________________________ getAutomationApp
    public static AutomationApp getAutomationApp(Context context) {
        return (AutomationApp) context.getApplicationContext();
    }
    //______________________________________________________________________________________________ getAutomationApp



    //______________________________________________________________________________________________ getRetrofitApiInterface
    public RetrofitApiInterface getRetrofitApiInterface() {
        return retrofitApiInterface;
    }
    //______________________________________________________________________________________________ getRetrofitApiInterface




    //______________________________________________________________________________________________ saveToken
    public boolean saveToken(MD_Token MDToken) {

        SharedPreferences.Editor token = context
                .getSharedPreferences(context.getString(R.string.ML_SharePreferences), 0)
                .edit();

        token.putString(context.getString(R.string.ML_AccessToken), MDToken.getAccess_token());
        token.putString(context.getString(R.string.ML_TokenType), MDToken.getToken_type());
        token.putInt(context.getString(R.string.ML_ExpireSin), MDToken.getExpires_in());
        token.putString(context.getString(R.string.ML_PhoneNumber), MDToken.getPhoneNumber());
        token.putString(context.getString(R.string.ML_ClientId), MDToken.getClient_id());
        token.putString(context.getString(R.string.ML_Issued), MDToken.getIssued());
        token.putString(context.getString(R.string.ML_Expires), MDToken.getExpires());
        token.putString(context.getString(R.string.ML_RefreshToken), MDToken.getRefresh_token());
        token.apply();
        return true;

    }
    //______________________________________________________________________________________________ saveToken




    //______________________________________________________________________________________________ saveProfile
    public boolean saveProfile(
            Context context,
            MD_SettingInfo.ModelProfileSetting profile) {
        SharedPreferences.Editor token = context
                .getSharedPreferences(context.getString(R.string.ML_SharePreferences), 0)
                .edit();
        token.putString(context.getString(R.string.ML_Name), profile.getName());
        token.putString(context.getString(R.string.ML_lastName), profile.getLastName());
        token.putInt(context.getString(R.string.ML_Gender), profile.getGender());
        token.apply();
        return true;
    }
    //______________________________________________________________________________________________ saveProfile




    //______________________________________________________________________________________________ logOut
    public boolean logOut(Context context) {
        SharedPreferences.Editor token =
                context.getSharedPreferences(context.getString(R.string.ML_SharePreferences), 0).edit();

        token.putString(context.getString(R.string.ML_AccessToken), null);
        token.putString(context.getString(R.string.ML_TokenType), null);
        token.putInt(context.getString(R.string.ML_ExpireSin), 0);
        token.putString(context.getString(R.string.ML_PhoneNumber), null);
        token.putString(context.getString(R.string.ML_ClientId), null);
        token.putString(context.getString(R.string.ML_Issued), null);
        token.putString(context.getString(R.string.ML_Expires), null);
        token.apply();
        return true;
    }
    //______________________________________________________________________________________________ logOut


}
