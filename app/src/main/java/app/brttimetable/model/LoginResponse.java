package app.brttimetable.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.brttimetable.model.ResponseMessage;

public class LoginResponse {
    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }
}
