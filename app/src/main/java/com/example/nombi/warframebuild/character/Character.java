package com.example.nombi.warframebuild.character;

/**
 * A character to create
 * @author Calvin
 * @version 17.05.02
 */

abstract class Character {

    //
    private double myHealth;
    private double myShields;
    private double myArmor;
    private double myPower;
    private double mySprintSpeed;

    private double myPwrRange;
    private double myPwrStrength;
    private double myPwrDuration;
    private double myPwrEfficency;

    private String myType;
    private String myBase;
    private String myCharName;
    private String myCompanionType;

    //////////////////////Constructor/////////////////////

    /**
     * Creates a new character
     * @param theAtts The integer attributes a character holds
     * @param theBase The base type of character
     * @param theName The Name of the character
     * @param theCompanionType The base companion type
     */
    public Character(double[] theAtts, String theBase, String theName,
              String theCompanionType, String theType) {
        initializeStats(theAtts);
        myBase = theBase;
        myCharName = theName;
        myCompanionType = theCompanionType;
        myType = theType;
    }

    /////////////Constructor Helper Methods/////////////////
    private void initializeStats(double[] theAtts) {
        myHealth = theAtts[0];
        myShields = theAtts[1];
        myArmor = theAtts[2];
        myPower = theAtts[3];
        mySprintSpeed = theAtts[4];
        myPwrRange = theAtts[5];
        myPwrStrength = theAtts[6];
        myPwrDuration = theAtts[7];
        myPwrEfficency = theAtts[8];
    }

///////////////Getters and Setters/////////////////
    public double getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(int myHealth) {
        this.myHealth = myHealth;
    }

    public double getMyShields() {
        return myShields;
    }

    public void setMyShields(int myShields) {
        this.myShields = myShields;
    }

    public double getMyArmor() {
        return myArmor;
    }

    public void setMyArmor(int myArmor) {
        this.myArmor = myArmor;
    }

    public double getMyPower() {
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

    public double getMyPwrRange() {
        return myPwrRange;
    }

    public void setMyPwrRange(int myPwrRange) {
        this.myPwrRange = myPwrRange;
    }

    public double getMyPwrStrength() {
        return myPwrStrength;
    }

    public void setMyPwrStrength(int myPwrStrength) {
        this.myPwrStrength = myPwrStrength;
    }

    public double getMyPwrDuration() {
        return myPwrDuration;
    }

    public void setMyPwrDuration(int myPwrDuration) {
        this.myPwrDuration = myPwrDuration;
    }

    public double getMyPwrEfficency() {
        return myPwrEfficency;
    }

    public void setMyPwrEfficency(int myPwrEfficency) {
        this.myPwrEfficency = myPwrEfficency;
    }

    public String getMyType() {
        return myBase;
    }

    public void setMyType(String myType) {
        this.myBase = myType;
    }

    public String getMyCharName() {
        return myCharName;
    }

    public void setMyCharName(String myCharName) {
        this.myCharName = myCharName;
    }

    public String getMyCompanionType() { return myCompanionType; }

    public void setMyCompanionType(String myCompanionType) { this.myCompanionType = myCompanionType; }

}
