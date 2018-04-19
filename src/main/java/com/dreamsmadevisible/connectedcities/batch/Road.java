package com.dreamsmadevisible.connectedcities.batch;

public class Road {

    private String cityA;
    private String cityB;

    public Road() {
    }

    public Road(String cityA, String cityB) {
        this.cityA = cityA;
        this.cityB = cityB;
    }

    public void setcityA(String cityA) {
        this.cityA = cityA;
    }

    public String getcityA() {
        return cityA;
    }

    public String getcityB() {
        return cityB;
    }

    public void setcityB(String cityB) {
        this.cityB = cityB;
    }

    @Override
    public String toString() {
        return "cityA: " + cityA + ", cityB: " + cityB;
    }
}
