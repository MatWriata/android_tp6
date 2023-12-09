package com.example.tp6;

public class Balls {
    private float weight;

    private float radius;
    private float cx;
    private float cy;



    public Balls(float weight, float radius, float cx, float cy) {
        this.weight = weight;
        this.radius = radius;
        this.cx = cx;
        this.cy = cy;
    }

    // Dans la classe Balls
    public boolean collidesWith(Balls otherBall) {
        float distance = (float) Math.sqrt(Math.pow(this.cx - otherBall.getCx(), 2) + Math.pow(this.cy - otherBall.getCy(), 2));
        return distance < this.radius + otherBall.getRadius();
    }



    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
