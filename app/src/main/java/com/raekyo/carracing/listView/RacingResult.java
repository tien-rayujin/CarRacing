package com.raekyo.carracing.listView;

import java.io.Serializable;

public class RacingResult implements Serializable {
    private int round;
    private int totalMoney;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public RacingResult(int round, int totalMoney) {
        this.round = round;
        this.totalMoney = totalMoney;
    }
}
