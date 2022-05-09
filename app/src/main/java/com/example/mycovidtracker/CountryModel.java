package com.example.mycovidtracker;

public class CountryModel {
    private String id;
    private String country;
    private String cases;
    private String todayCases;
    private String deaths;
    private String todayDeath;
    private String recovered;
    private String active;
    private String critical;
    private String flag;

    public CountryModel(String id,String country, String cases, String todayCases, String deaths, String todayDeath, String recovered, String active, String critical, String flag) {
        this.id = id;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeath = todayDeath;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CountryModel() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeath = todayDeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
