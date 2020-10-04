package ir.ngra.automation.views.application;

import android.app.Application;
import android.content.Context;

import ir.mlcode.latifiarchitecturelibrary.application.APP_Latifi;

public class AutomationApp extends APP_Latifi {

    private Context context;
    public String Host = "http://You Api Link";
//    private RetrofitApiInterface retrofitApiInterface;


    //______________________________________________________________________________________________ onCreate
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        setContext(context);
        setHost(Host);
    }
    //______________________________________________________________________________________________ onCreate



    //______________________________________________________________________________________________ getAutomationApp
    public static AutomationApp getAutomationApp(Context context) {
        return (AutomationApp) context.getApplicationContext();
    }
    //______________________________________________________________________________________________ getAutomationApp



}
