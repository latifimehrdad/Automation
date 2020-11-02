package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MD_FunctionalityReport {


    @SerializedName("workingBoundary")
    private String workingBoundary;

    @SerializedName("neighbourhood")
    private String neighbourhood;

    @SerializedName("city")
    private String city;

    @SerializedName("province")
    private String province;

    @SerializedName("project")
    private String project;

    @SerializedName("date")
    private Date date;

    @SerializedName("issueYearMonth")
    private String issueYearMonth;

    @SerializedName("attendanceState")
    private Byte attendanceState;

    @SerializedName("leave")
    private Double leave;

    @SerializedName("mission")
    private Double mission;

    @SerializedName("absenceOfWork")
    private Double absenceOfWork;

    @SerializedName("lackOfWork")
    private Double lackOfWork;

    @SerializedName("overWorkPayTime")
    private Double overWorkPayTime;

    @SerializedName("holidayPayTime")
    private Double holidayPayTime;

    @SerializedName("nightPayTime")
    private Double nightPayTime;

    @SerializedName("mainWork")
    private Double mainWork;

    @SerializedName("neighbourhoodBoundaryCategory")
    private Double neighbourhoodBoundaryCategory;

    public MD_FunctionalityReport(String workingBoundary, String neighbourhood, String city, String province, String project, Date date, String issueYearMonth, Byte attendanceState, Double leave, Double mission, Double absenceOfWork, Double lackOfWork, Double overWorkPayTime, Double holidayPayTime, Double nightPayTime, Double mainWork, Double neighbourhoodBoundaryCategory) {
        this.workingBoundary = workingBoundary;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.province = province;
        this.project = project;
        this.date = date;
        this.issueYearMonth = issueYearMonth;
        this.attendanceState = attendanceState;
        this.leave = leave;
        this.mission = mission;
        this.absenceOfWork = absenceOfWork;
        this.lackOfWork = lackOfWork;
        this.overWorkPayTime = overWorkPayTime;
        this.holidayPayTime = holidayPayTime;
        this.nightPayTime = nightPayTime;
        this.mainWork = mainWork;
        this.neighbourhoodBoundaryCategory = neighbourhoodBoundaryCategory;
    }

    public String getWorkingBoundary() {
        return workingBoundary;
    }

    public void setWorkingBoundary(String workingBoundary) {
        this.workingBoundary = workingBoundary;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIssueYearMonth() {
        return issueYearMonth;
    }

    public void setIssueYearMonth(String issueYearMonth) {
        this.issueYearMonth = issueYearMonth;
    }

    public Byte getAttendanceState() {
        return attendanceState;
    }

    public void setAttendanceState(Byte attendanceState) {
        this.attendanceState = attendanceState;
    }

    public Double getLeave() {
        return leave;
    }

    public void setLeave(Double leave) {
        this.leave = leave;
    }

    public Double getMission() {
        return mission;
    }

    public void setMission(Double mission) {
        this.mission = mission;
    }

    public Double getAbsenceOfWork() {
        return absenceOfWork;
    }

    public void setAbsenceOfWork(Double absenceOfWork) {
        this.absenceOfWork = absenceOfWork;
    }

    public Double getLackOfWork() {
        return lackOfWork;
    }

    public void setLackOfWork(Double lackOfWork) {
        this.lackOfWork = lackOfWork;
    }

    public Double getOverWorkPayTime() {
        return overWorkPayTime;
    }

    public void setOverWorkPayTime(Double overWorkPayTime) {
        this.overWorkPayTime = overWorkPayTime;
    }

    public Double getHolidayPayTime() {
        return holidayPayTime;
    }

    public void setHolidayPayTime(Double holidayPayTime) {
        this.holidayPayTime = holidayPayTime;
    }

    public Double getNightPayTime() {
        return nightPayTime;
    }

    public void setNightPayTime(Double nightPayTime) {
        this.nightPayTime = nightPayTime;
    }

    public Double getMainWork() {
        return mainWork;
    }

    public void setMainWork(Double mainWork) {
        this.mainWork = mainWork;
    }

    public Double getNeighbourhoodBoundaryCategory() {
        return neighbourhoodBoundaryCategory;
    }

    public void setNeighbourhoodBoundaryCategory(Double neighbourhoodBoundaryCategory) {
        this.neighbourhoodBoundaryCategory = neighbourhoodBoundaryCategory;
    }
}
