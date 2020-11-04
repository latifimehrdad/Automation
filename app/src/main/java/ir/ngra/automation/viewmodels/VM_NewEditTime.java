package ir.ngra.automation.viewmodels;

import android.app.Activity;

import ir.ngra.automation.views.application.AutomationApp;

public class VM_NewEditTime extends VM_Primary {


    private String description;

    private String fromDate;

    private String toDate;


    //______________________________________________________________________________________________ VM_EditTime
    public VM_NewEditTime(Activity activity) {
        setContext(activity);
    }
    //______________________________________________________________________________________________ VM_EditTime



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

        this.fromDate = AutomationApp.getAutomationApp(getContext()).getUtilityComponent()
                .getApplicationUtility()
                .solarDateToGregorian(fromDate)
                .getDateString();

        this.fromDate = this.fromDate + "T" + fromTime;
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

        this.toDate = AutomationApp.getAutomationApp(getContext()).getUtilityComponent()
                .getApplicationUtility()
                .solarDateToGregorian(toDate)
                .getDateString();

        this.toDate = this.toDate + "T" + toTime;
    }
    //______________________________________________________________________________________________ setToDate

}
