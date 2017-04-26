package com.example.nombi.warframebuild.weapon;

/**
 * Created by Calvin on 4/26/2017.
 */

public abstract class Weapon {

    // Basic modifiers
    private double myAccuracy;
    private double myCritChance;
    private double myCritMultiplier;
    private double myFireRate;
    private int myMagSize;
    private String myNoise;
    private double myReloadSpeed;
    private double myStatusChance;

    //Base damage modifiers
    private double myImpact;
    private double myPuncture;
    private double mySlash;

    //Basic elemental modifiers
    private double myCold;
    private double myElectricity;
    private double myHeat;

    //Combined elemental modifiers
    private double myToxin;
    private double myBlast;
    private double myCorrosive;
    private double myMagnetic;
    private double myRadiation;
    private double myViral;

    public double getMyAccuracy() {
        return myAccuracy;
    }

    public void setMyAccuracy(double myAccuracy) {
        this.myAccuracy = myAccuracy;
    }

    public double getMyCritChance() {
        return myCritChance;
    }

    public void setMyCritChance(double myCritChance) {
        this.myCritChance = myCritChance;
    }

    public double getMyCritMultiplier() {
        return myCritMultiplier;
    }

    public void setMyCritMultiplier(double myCritMultiplier) {
        this.myCritMultiplier = myCritMultiplier;
    }

    public double getMyFireRate() {
        return myFireRate;
    }

    public void setMyFireRate(double myFireRate) {
        this.myFireRate = myFireRate;
    }

    public int getMyMagSize() {
        return myMagSize;
    }

    public void setMyMagSize(int myMagSize) {
        this.myMagSize = myMagSize;
    }

    public String getMyNoise() {
        return myNoise;
    }

    public void setMyNoise(String myNoise) {
        this.myNoise = myNoise;
    }

    public double getMyReloadSpeed() {
        return myReloadSpeed;
    }

    public void setMyReloadSpeed(double myReloadSpeed) {
        this.myReloadSpeed = myReloadSpeed;
    }

    public double getMyStatusChance() {
        return myStatusChance;
    }

    public void setMyStatusChance(double myStatusChance) {
        this.myStatusChance = myStatusChance;
    }

    public double getMyImpact() {
        return myImpact;
    }

    public void setMyImpact(double myImpact) {
        this.myImpact = myImpact;
    }

    public double getMyPuncture() {
        return myPuncture;
    }

    public void setMyPuncture(double myPuncture) {
        this.myPuncture = myPuncture;
    }

    public double getMySlash() {
        return mySlash;
    }

    public void setMySlash(double mySlash) {
        this.mySlash = mySlash;
    }

    public double getMyCold() {
        return myCold;
    }

    public void setMyCold(double myCold) {
        this.myCold = myCold;
    }

    public double getMyElectricity() {
        return myElectricity;
    }

    public void setMyElectricity(double myElectricity) {
        this.myElectricity = myElectricity;
    }

    public double getMyHeat() {
        return myHeat;
    }

    public void setMyHeat(double myHeat) {
        this.myHeat = myHeat;
    }

    public double getMyToxin() {
        return myToxin;
    }

    public void setMyToxin(double myToxin) {
        this.myToxin = myToxin;
    }

    public double getMyBlast() {
        return myBlast;
    }

    public void setMyBlast(double myBlast) {
        this.myBlast = myBlast;
    }

    public double getMyCorrosive() {
        return myCorrosive;
    }

    public void setMyCorrosive(double myCorrosive) {
        this.myCorrosive = myCorrosive;
    }

    public double getMyMagnetic() {
        return myMagnetic;
    }

    public void setMyMagnetic(double myMagnetic) {
        this.myMagnetic = myMagnetic;
    }

    public double getMyRadiation() {
        return myRadiation;
    }

    public void setMyRadiation(double myRadiation) {
        this.myRadiation = myRadiation;
    }

    public double getMyViral() {
        return myViral;
    }

    public void setMyViral(double myViral) {
        this.myViral = myViral;
    }
}
