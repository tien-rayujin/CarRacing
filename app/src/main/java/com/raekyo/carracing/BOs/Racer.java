package com.raekyo.carracing.BOs;

import java.io.Serializable;

public class Racer implements Serializable {
    private int img;
    private String name;
    private int moneyBet;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoneyBet() {
        return moneyBet;
    }

    public void setMoneyBet(int moneyBet) {
        this.moneyBet = moneyBet;
    }

    public Racer(int img, String name, int moneyBet) {
        this.img = img;
        this.name = name;
        this.moneyBet = moneyBet;
    }
}
