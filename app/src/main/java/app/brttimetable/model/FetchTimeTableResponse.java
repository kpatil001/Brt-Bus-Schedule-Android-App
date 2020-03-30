package app.brttimetable.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchTimeTableResponse {

    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;
    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

}