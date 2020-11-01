package ir.ngra.automation.utility;

import ir.ngra.automation.models.MD_SettingInfo;
import ir.ngra.automation.models.MD_Token;
import ir.ngra.automation.models.MR_Hi;
import ir.ngra.automation.models.MR_Primary;
import ir.ngra.automation.models.MR_DailyItems;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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


    @GET(Version + "/AttendanceRequest/GetTodayEntrance")
    Call<MR_DailyItems> getTodayEntrance
            (
                    @Header("Authorization") String Authorization
            );


}
