package com.zaimutest777.zaim.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilham Shihnazarow  on 9/7/2021
 */

public class MapNavigatorList {
    @SerializedName("title")
    private String title;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("summText")
    private String summText;
    @SerializedName("betText")
    private String betText;
    @SerializedName("url")
    private String url;

    @SerializedName("score")
    private String score;

    @SerializedName("mastercard")
    private String mastercard;
    @SerializedName("mir")
    private String mir;
    @SerializedName("visa")
    private String visa;
    @SerializedName("qiwi")
    private String qiwi;
    @SerializedName("yandex")
    private String yandex;
    @SerializedName("cash")
    private String cash;

    @SerializedName("description")
    private String description;




    public MapNavigatorList(
            String title,
            String imageUrl,
            String summText,
            String betText,
            String url,
            String score,
            String mastercard,
            String mir,
            String visa,
            String qiwi,
            String yandex,
            String cash,
            String description) {

        this.title = title;
        this.imageUrl = imageUrl;
        this.summText = summText;
        this.betText = betText;
        this.url = url;
        this.score = score;
        this.mastercard = mastercard;
        this.mir = mir;
        this.visa = visa;
        this.qiwi = qiwi;
        this.yandex = yandex;
        this.cash = cash;
        this.description = description;


    }

    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getSummText() { return summText; }
    public String getBetText() { return betText; }
    public String getUrl() { return url; }

    public String getScore() { return score; }
    public String getMastercard() { return mastercard; }
    public String getMir() { return mir; }
    public String getVisa() { return visa; }
    public String getQiwi() { return qiwi; }
    public String getYandex() { return yandex; }
    public String getCash() { return cash; }

    public String getDescription() { return description; }


}
