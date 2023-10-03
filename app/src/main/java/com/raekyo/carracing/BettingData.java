package com.raekyo.carracing;

import java.io.Serializable;

public class BettingData implements Serializable {
    private int currency;
    private int betCar1;
    private int betCar2;
    private int betCar3;

    public BettingData(int currency, int betCar1, int betCar2, int betCar3) {
        this.currency = currency;
        this.betCar1 = betCar1;
        this.betCar2 = betCar2;
        this.betCar3 = betCar3;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getBetCar1() {
        return betCar1;
    }

    public void setBetCar1(int betCar1) {
        this.betCar1 = betCar1;
    }

    public int getBetCar2() {
        return betCar2;
    }

    public void setBetCar2(int betCar2) {
        this.betCar2 = betCar2;
    }

    public int getBetCar3() {
        return betCar3;
    }

    public void setBetCar3(int betCar3) {
        this.betCar3 = betCar3;
    }
}

