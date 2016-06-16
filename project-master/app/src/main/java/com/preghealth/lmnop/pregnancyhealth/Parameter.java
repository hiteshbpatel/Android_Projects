package com.preghealth.lmnop.pregnancyhealth;

/**
 * Created by Chitkara Neetu on 5/12/2016.
 */
public class Parameter {

    int id;
    String name;
    String unit;
    double target;
    double max;
    Integer imgid;
    double actual;


    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public Parameter(int id, String name, String unit, int target, int max, Integer imgid, int actual) {
        this.id=id;
        this.name = name;
        this.unit = unit;
        this.target = target;
        this.max = max;
        this.imgid = imgid;
        this.actual=actual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }
}
