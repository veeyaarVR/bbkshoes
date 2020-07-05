package com.veeyaar.myscanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionResponse {

    @SerializedName("odata.metadata")
    @Expose
    public String odataMetadata;
    @SerializedName("SessionId")
    @Expose
    public String sessionId;
    @SerializedName("Version")
    @Expose
    public String version;
    @SerializedName("SessionTimeout")
    @Expose
    public Integer sessionTimeout;
}
