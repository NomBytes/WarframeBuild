package com.example.nombi.warframebuild.character;

/**
 * A character to create
 * @author Calvin
 * @version 17.05.02
 */

abstract class Character {

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
    private String myBase;
    private String myCharName;
    private String myCompanionType;

    //////////////////////Constructor/////////////////////

    /**
     * Creates a new character
     * @param theAtts The integer attributes a character holds
     * @param theSpeed The sprint speed a warframe holds
     * @param theBase The base type of character
     * @param theName The Name of the character
     * @param theCompanionType The base companion type
     */
    public Character(int[] theAtts, double theSpeed, String theBase, String theName,
              String theCompanionType, String theType) {
        initializeStats(theAtts, theSpeed);
        myBase = theBase;
        myCharName = theName;
        myCompanionType = theCompanionType;
        myType = theType;
    }

    /////////////Constructor Helper Methods/////////////////
    private void initializeStats(int[] theAtts, double theSpeed) {
        myHealth = theAtts[0];
        myShields = theAtts[1];
        myArmor = theAtts[2];
        myPower = theAtts[3];
        mySprintSpeed = theSpeed;
        myPwrRange = theAtts[4];
        myPwrStrength = theAtts[5];
        myPwrDuration = theAtts[6];
        myPwrEfficency = theAtts[7];
    }

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
