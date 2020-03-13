package com.iiitdmk.solasta.data;

import com.google.gson.annotations.SerializedName;

public class EventsData {

    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("info")
    private String mInfo;
    @SerializedName("subTitle")
    private String mSubTitle;
    @SerializedName("eventRules")
    private String mEventRules;
    @SerializedName("eventPrize")
    private String mEventPrize;
    @SerializedName("event_register")
    private String mEvent_register;

    public EventsData(String mImageUrl, String mInfo, String mSubTitle, String mEventRules, String mEventPrize,String mEvent_register ) {
        this.mImageUrl = mImageUrl;
        this.mInfo = mInfo;
        this.mSubTitle = mSubTitle;
        this.mEventRules = mEventRules;
        this.mEventPrize = mEventPrize;
        this.mEvent_register = mEvent_register;
    }


    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getPrize() {
        return mEventPrize;
    }

    public void setPrize(String mEventPrize) {
        mEventPrize = mEventPrize;
    }

    public String getEventRegister() {
        return mEvent_register;
    }

    public void setEventRegister(String mEventRegister) {
        mEventRegister = mEventRegister;
    }


    public String getRules() {
        return mEventRules;
    }

    public void setRules(String mEventRules) {
        mEventRules = mEventRules;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }
    }
