package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MD_SettingInfo extends MR_Primary {

    //______________________________________________________________________________________________ Value
    @SerializedName("result")
    ModelProfileSetting result;
    //______________________________________________________________________________________________ Value


    public MD_SettingInfo(ArrayList<MD_Message> messages) {
        super(messages);
    }

    public ModelProfileSetting getResult() {
        return result;
    }

    public void setResult(ModelProfileSetting result) {
        this.result = result;
    }


    public class ModelProfileSetting {

        //______________________________________________________________________________________________ Value
        @SerializedName("name")
        String name;

        @SerializedName("lastName")
        String lastName;

        @SerializedName("gender")
        Integer gender;

        @SerializedName("isProfileCompleted")
        Boolean isProfileCompleted;

        @SerializedName("isAddressCompleted")
        Boolean isAddressCompleted;

        //______________________________________________________________________________________________ Value


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public Boolean getProfileCompleted() {
            return isProfileCompleted;
        }

        public void setProfileCompleted(Boolean profileCompleted) {
            isProfileCompleted = profileCompleted;
        }

        public Boolean getAddressCompleted() {
            return isAddressCompleted;
        }

        public void setAddressCompleted(Boolean addressCompleted) {
            isAddressCompleted = addressCompleted;
        }

    }
}
