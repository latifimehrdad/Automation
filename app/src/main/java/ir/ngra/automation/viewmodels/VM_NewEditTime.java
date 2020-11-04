package ir.ngra.automation.viewmodels;

import android.app.Activity;

import ir.ngra.automation.R;
import ir.ngra.automation.models.MR_Primary;
import ir.ngra.automation.utility.AttendanceType;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_NewEditTime extends VM_Primary {


    private String description;

    private String fromDate;

    private String toDate;


    //______________________________________________________________________________________________ VM_EditTime
    public VM_NewEditTime(Activity activity) {
        setContext(activity);
    }
    //______________________________________________________________________________________________ VM_EditTime


    //______________________________________________________________________________________________ requestEditTime
    public void requestEditTime(Byte attendanceType) {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);


        if (attendanceType.equals(AttendanceType.Arrival))
            setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                    .getRetrofitApiInterface()
                    .RequestArrival(getFromDate(), getDescription(), authorization));
        else if (attendanceType.equals(AttendanceType.Departure))
            setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                    .getRetrofitApiInterface()
                    .RequestDeparture(getToDate(), getDescription(), authorization));
        else if (attendanceType.equals(AttendanceType.ArrivalAndDeparture))
            setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                    .getRetrofitApiInterface()
                    .RequestArrivalAndDeparture(getFromDate(), getToDate(), getDescription(), authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_Primary>() {
            @Override
            public void onResponse(Call<MR_Primary> call, Response<MR_Primary> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    setResponseMessage(getResponseMessages(response.body().getMessages()));
                    sendActionToObservable(ObservableActions.getEditTime);
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_Primary> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ requestEditTime


    //______________________________________________________________________________________________ getSubstituteList
    public String getDescription() {
        return description;
    }
    //______________________________________________________________________________________________ getSubstituteList


    //______________________________________________________________________________________________ getSubstituteList
    public void setDescription(String description) {
        this.description = description;
    }
    //______________________________________________________________________________________________ getSubstituteList


    //______________________________________________________________________________________________ getFromDate
    public String getFromDate() {
/*        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm");
        Date from = null;
        try {
            from = format.parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return fromDate;
    }
    //______________________________________________________________________________________________ getFromDate


    //______________________________________________________________________________________________ setFromDate
    public void setFromDate(String fromDate, String fromTime) {

        if (fromDate != null && fromTime != null) {
            this.fromDate = AutomationApp.getAutomationApp(getContext()).getUtilityComponent()
                    .getApplicationUtility()
                    .solarDateToGregorian(fromDate)
                    .getDateString();

            this.fromDate = this.fromDate + "T" + fromTime;
        }
    }
    //______________________________________________________________________________________________ setFromDate


    //______________________________________________________________________________________________ getToDate
    public String getToDate() {
/*        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm");
        Date to = null;
        try {
            to = format.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return toDate;
    }
    //______________________________________________________________________________________________ getToDate


    //______________________________________________________________________________________________ setToDate
    public void setToDate(String toDate, String toTime) {

        if (toDate != null && toTime != null) {
            this.toDate = AutomationApp.getAutomationApp(getContext()).getUtilityComponent()
                    .getApplicationUtility()
                    .solarDateToGregorian(toDate)
                    .getDateString();

            this.toDate = this.toDate + "T" + toTime;
        }
    }
    //______________________________________________________________________________________________ setToDate

}
