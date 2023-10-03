package com.raekyo.carracing.listView;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class PlayHistory implements Serializable {
    private int car_img;
    private String round;
    private String money;

    public int getCar_img() {
        return car_img;
    }

    public void setCar_img(int car_img) {
        this.car_img = car_img;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public PlayHistory(int car_img, String round, String money) {
        this.car_img = car_img;
        this.round = round;
        this.money = money;
    }
}
