package com.larebshaikh.mirino;

public class MosqueDetails {
    private String MosqueName;
    private String MosqueArea;
    private String MosqueLongitude;
    private String MosqueLatitude;
    private String Fajr;
    private String Zohar;
    private String Asr;
    private String Maghrib;
    private String Isha;
    private String Juma;

    public MosqueDetails() {
    }

    public MosqueDetails(String mosqueName, String mosqueArea,  String fajr,
                         String zohar, String asr, String maghrib, String isha, String juma) {
        MosqueName = mosqueName;
        MosqueArea = mosqueArea;
        Fajr = fajr;
        Zohar = zohar;
        Asr = asr;
        Maghrib = maghrib;
        Isha = isha;
        Juma = juma;
    }

    public String getMosqueName() {
        return MosqueName;
    }

    public void setMosqueName(String mosqueName) {
        MosqueName = mosqueName;
    }

    public String getMosqueArea() {
        return MosqueArea;
    }

    public void setMosqueArea(String mosqueArea) {
        MosqueArea = mosqueArea;
    }

    public String getMosqueLongitude() {
        return MosqueLongitude;
    }

    public void setMosqueLongitude(String mosqueLongitude) {
        MosqueLongitude = mosqueLongitude;
    }

    public String getMosqueLatitude() {
        return MosqueLatitude;
    }

    public void setMosqueLatitude(String mosqueLatitude) {
        MosqueLatitude = mosqueLatitude;
    }

    public String getFajr() {
        return Fajr;
    }

    public void setFajr(String fajr) {
        Fajr = fajr;
    }

    public String getZohar() {
        return Zohar;
    }

    public void setZohar(String zohar) {
        Zohar = zohar;
    }

    public String getAsr() {
        return Asr;
    }

    public void setAsr(String asr) {
        Asr = asr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public void setMaghrib(String maghrib) {
        Maghrib = maghrib;
    }

    public String getIsha() {
        return Isha;
    }

    public void setIsha(String isha) {
        Isha = isha;
    }

    public String getJuma() {
        return Juma;
    }

    public void setJuma(String juma) {
        Juma = juma;
    }
}
