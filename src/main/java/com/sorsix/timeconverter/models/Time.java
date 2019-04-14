package com.sorsix.timeconverter.models;

public class Time {
    private long epoch;
    private String skopje;

    public Time(long epoch, String skopje) {
        this.epoch = epoch;
        this.skopje = skopje;
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public String getSkopje() {
        return skopje;
    }

    public void setSkopje(String skopje) {
        this.skopje = skopje;
    }
}
