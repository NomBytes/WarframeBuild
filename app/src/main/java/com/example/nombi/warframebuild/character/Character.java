package com.example.nombi.warframebuild.character;

import java.io.Serializable;

/**
 * A character to create
 * @author Calvin
 * @version 17.05.02
 */
abstract class Character implements Serializable{

    private double myHealth;        /* The health valueof the character */
    private double myShields;       /* The shields value of the character */
    private double myArmor;         /* The armor value of the character */
    private double myPower;         /* The power of the character */
    private double mySprintSpeed;   /* The sprint speed of the character */

    private double myPwrRange;      /* The Range of the character's abilities */
    private double myPwrStrength;   /* The Strength of the character's abilities */
    private double myPwrDuration;   /* The Duration of the character's abilities */
    private double myPwrEfficency;  /* The Efficiency (cost) of the character's abilities */

    private String myType;          /* The type of character */
    private String myBase;          /* The base type of the character */
    private String myCharName;      /* The name of the character */
    private String myCompanionType; /* The type of companion (ununsed) */

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

    /**
     * Initializes the attributes to their respective fields given a attribute array.
     * @param theAtts the Attribute array that holds the attributes of the character.
     */
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
