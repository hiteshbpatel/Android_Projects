package com.preghealth.lmnop.pregnancyhealth;

/**
 * Created by Chitkara Neetu on 5/12/2016.
 */
public class Dayscore {

    String date;
    int score;
    String scorestring;

    public Dayscore(){}

    public Dayscore(String date, int score) {
        this.date = date;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
