package com.sorsix.timeconverter.models;

public final class Time {
    private long unixEpoch;
    private String skopjeDate;

    public Time(long unixEpoch, String skopjeDate) {
        this.unixEpoch = unixEpoch;
        this.skopjeDate = skopjeDate;
    }

    public long getUnixEpoch() {
        return unixEpoch;
    }

    public void setUnixEpoch(long unixEpoch) {
        this.unixEpoch = unixEpoch;
    }

    public String getSkopjeDate() {
        return skopjeDate;
    }

    public void setSkopjeDate(String skopjeDate) {
        this.skopjeDate = skopjeDate;
    }
}
