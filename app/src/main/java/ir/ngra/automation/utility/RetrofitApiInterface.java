package ir.ngra.automation.utility;

import ir.ngra.automation.models.MD_SettingInfo;
import ir.ngra.automation.models.MD_Token;
import ir.ngra.automation.models.MR_EditTime;
import ir.ngra.automation.models.MR_FunctionalityReport;
import ir.ngra.automation.models.MR_Hi;
import ir.ngra.automation.models.MR_Mission;
import ir.ngra.automation.models.MR_Primary;
import ir.ngra.automation.models.MR_TodayArrivalAndDeparture;
import ir.ngra.automation.models.MR_WorkVacation;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApiInterface {

    String Version = "/api/v1";

    @FormUrlEncoded
    @POST(Version + "/application/hi")
    Call<MR_Hi> getHi
            (
                    @Field("client_id") String client_id,
                    @Field("client_secret") String client_secret,
                    @Field("name") String name
            );


    @FormUrlEncoded
    @POST("/token")
    Call<MD_Token> getToken
            (
                    @Field("client_id") String client_id,
                    @Field("client_secret") String client_secret,
                    @Field("grant_type") String grant_type
            );


    @GET(Version + "/account/settinginfo")
    Call<MD_SettingInfo> getSettingInfo
            (
                    @Header("Authorization") String Authorization

            );


    @FormUrlEncoded
    @POST("/token")
    Call<MD_Token> getRefreshToken
            (
                    @Field("client_id") String client_id,
                    @Field("client_secret") String client_secret,
                    @Field("grant_type") String grant_type,
                    @Field("refresh_token") String refresh_token
            );


    @FormUrlEncoded
    @POST("/token")
    Call<MD_Token> login
            (
                    @Field("client_id") String client_id,
                    @Field("client_secret") String client_secret,
                    @Field("grant_type") String grant_type,
                    @Field("username") String PhoneNumber,
                    @Field("code") String code,
                    @Header("Authorization") String Authorization

            );


    @FormUrlEncoded
    @POST(Version + "/account/authrequest")
    Call<MR_Primary> LoginCode
            (
                    @Field("client_id") String client_id,
                    @Field("client_secret") String client_secret,
                    @Field("mobile") String PhoneNumber,
                    @Header("Authorization") String Authorization

            );


    @GET(Version + "/StandardAttendance/GetTodayArrivalAndDeparture")
    Call<MR_TodayArrivalAndDeparture> getTodayArrivalAndDeparture
            (
                    @Header("Authorization") String Authorization
            );


    @GET(Version + "/AttendanceCalcDailyItem/GetDailyItems")
    Call<MR_FunctionalityReport> getDailyItems
            (
                    @Header("Authorization") String Authorization
            );



    @FormUrlEncoded
    @POST(Version + "/AttendanceRequest/RequestLeave")
    Call<MR_Primary> RequestLeave
            (
                    @Field("From") String From,
                    @Field("To") String To,
                    @Field("Description") String Description,
                    @Header("Authorization") String Authorization

            );


    @FormUrlEncoded
    @POST(Version + "/AttendanceRequest/RequestMission")
    Call<MR_Primary> RequestMission
            (
                    @Field("From") String From,
                    @Field("To") String To,
                    @Field("Description") String Description,
                    @Header("Authorization") String Authorization

            );


    @GET(Version + "/AttendanceRequest/GetRequests")
    Call<MR_WorkVacation> getRequestsWorkVacation
            (
                    @Query("type") Byte type,
                    @Header("Authorization") String Authorization
            );



    @GET(Version + "/AttendanceRequest/GetRequests")
    Call<MR_Mission> getRequestsMission
            (
                    @Query("type") Byte type,
                    @Header("Authorization") String Authorization
            );


    @GET(Version + "/AttendanceRequest/GetRequests")
    Call<MR_EditTime> getRequestsEditTime
            (
                    @Query("type") Byte type,
                    @Header("Authorization") String Authorization
            );


}
