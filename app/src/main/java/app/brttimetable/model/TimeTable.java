package app.brttimetable.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeTable {

    @SerializedName("stationName")
    @Expose
    private String stationName;
    @SerializedName("time")
    @Expose
    private String time;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}