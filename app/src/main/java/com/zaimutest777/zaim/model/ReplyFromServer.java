package com.zaimutest777.zaim.model;

import com.google.gson.annotations.SerializedName;

public class ReplyFromServer {

    @SerializedName("flag")
    public String flag;
    @SerializedName("url")
    public String url;
    @SerializedName("ip")
    public String ip;

    public ReplyFromServer(
            String flag,
            String url,
            String ip
    )
    {
        this.flag = flag;
        this.url = url;
        this.ip = ip;
    }

    public String getFlag() { return flag; }
    public String getUrls() { return url; }
    public String getIp() { return ip; }
}