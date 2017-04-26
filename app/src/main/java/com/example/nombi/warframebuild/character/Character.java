package com.example.nombi.warframebuild.character;

/**
 * Created by Calvin on 4/26/2017.
 */

public abstract class Character {

    //
    private int myHealth;
    private int myShields;
    private int myArmor;
    private int myPower;
    private double mySprintSpeed;

    private int myPwrRange;
    private int myPwrStrength;
    private int myPwrDuration;
    private int myPwrEfficency;

    private String myType;
    private String myCharName;

///////////////Getters and Setters/////////////////
    public int getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(int myHealth) {
        this.myHealth = myHealth;
    }

    public int getMyShields() {
        return myShields;
    }

    public void setMyShields(int myShields) {
        this.myShields = myShields;
    }

    public int getMyArmor() {
        return myArmor;
    }

    public void setMyArmor(int myArmor) {
        this.myArmor = myArmor;
    }

    public int getMyPower() {
        return myPower;
    }

    public void setMyPower(int myPower) {
        this.myPower = myPower;
    }

    public double getMySprintSpeed() {
        return mySprintSpeed;
    }

    public void setMySprintSpeed(double mySprintSpeed) {
        this.mySprintSpeed = mySprintSpeed;
    }

    public int getMyPwrRange() {
        return myPwrRange;
    }

    public void setMyPwrRange(int myPwrRange) {
        this.myPwrRange = myPwrRange;
    }

    public int getMyPwrStrength() {
        return myPwrStrength;
    }

    public void setMyPwrStrength(int myPwrStrength) {
        this.myPwrStrength = myPwrStrength;
    }

    public int getMyPwrDuration() {
        return myPwrDuration;
    }

    public void setMyPwrDuration(int myPwrDuration) {
        this.myPwrDuration = myPwrDuration;
    }

    public int getMyPwrEfficency() {
        return myPwrEfficency;
    }

    public void setMyPwrEfficency(int myPwrEfficency) {
        this.myPwrEfficency = myPwrEfficency;
    }

    public String getMyType() {
        return myType;
    }

    public void setMyType(String myType) {
        this.myType = myType;
    }

    public String getMyCharName() {
        return myCharName;
    }

    public void setMyCharName(String myCharName) {
        this.myCharName = myCharName;
    }
}
