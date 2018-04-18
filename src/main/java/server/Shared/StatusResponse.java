package server.Shared;

import com.google.gson.Gson;

public class StatusResponse {

    private String status = "";
    private ExtraInfoStatusResponse extraInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ExtraInfoStatusResponse getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ExtraInfoStatusResponse extraInfo) {
        this.extraInfo = extraInfo;
    }
}

