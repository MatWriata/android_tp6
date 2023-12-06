package com.example.tp6;

public class Balls {
    float weight;

    float radius;
    float cx;
    float cy;

    public Balls(float weight,float radius, float cx, float cy) {
        this.weight = weight;
        this.radius = radius;
        this.cx = cx;
        this.cy = cy;
    }

    public float getWeigh() {
        return weight;
    }

    public void setWeigh(float weight) {
        this.weight = weight;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }
}
