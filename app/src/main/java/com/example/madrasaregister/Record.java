package com.example.madrasaregister;

import androidx.annotation.NonNull;

public class Record {
    private Integer stdId;
    private String date;
    private Integer sabak;
    private Integer sabki;
    private Integer manzil;
    private Integer id;

    public Record(Integer id, Integer stdId, String date, Integer sabak, Integer sabki, Integer manzil) {
        this.id = id;
        this.stdId = stdId;
        this.date = date;
        this.sabak = sabak;
        this.sabki = sabki;
        this.manzil = manzil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStdId() {
        return stdId;
    }

    public void setStdId(Integer stdId) {
        this.stdId = stdId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSabak() {
        return sabak;
    }

    public void setSabak(Integer sabak) {
        this.sabak = sabak;
    }

    public Integer getSabki() {
        return sabki;
    }

    public void setSabki(Integer sabki) {
        this.sabki = sabki;
    }

    public Integer getManzil() {
        return manzil;
    }

    public void setManzil(Integer manzil) {
        this.manzil = manzil;
    }

    @NonNull
    public String toString()
    {
        return getId().toString();
    }
}
