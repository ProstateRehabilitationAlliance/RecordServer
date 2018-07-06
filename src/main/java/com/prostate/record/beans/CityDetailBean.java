package com.prostate.record.beans;

import java.io.Serializable;

public class CityDetailBean implements Serializable {

    private String cityName;

    private String cityId;

    private String parentCityName;

    private String parentCityId;

    private String grandfatherCityName;

    private String grandfatherCityId;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getParentCityName() {
        return parentCityName;
    }

    public void setParentCityName(String parentCityName) {
        this.parentCityName = parentCityName;
    }

    public String getParentCityId() {
        return parentCityId;
    }

    public void setParentCityId(String parentCityId) {
        this.parentCityId = parentCityId;
    }

    public String getGrandfatherCityName() {
        return grandfatherCityName;
    }

    public void setGrandfatherCityName(String grandfatherCityName) {
        this.grandfatherCityName = grandfatherCityName;
    }

    public String getGrandfatherCityId() {
        return grandfatherCityId;
    }

    public void setGrandfatherCityId(String grandfatherCityId) {
        this.grandfatherCityId = grandfatherCityId;
    }
}
