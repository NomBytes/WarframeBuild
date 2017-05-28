package com.example.nombi.warframebuild.loadout;

import com.example.nombi.warframebuild.character.Warframe;

import java.io.Serializable;

/**
 * A loadout for a Warframe.
 * @author Calvin
 * @version 17.05.04
 */
public class WarframeLoadout implements Serializable {

    private String myLoadoutName;    /* The name of the loadout. */
    private String myAuthor;         /* The author of the loadout */

    private Warframe myWarframe;     /* The Warframe that the loadout is based on. */
    private Boolean myReactor;       /* Reactor install toggle */

    private Mod[] myMods;            /* The mods that are installed on the loadout */
    private int[] myPolarities;      /* The polarity slots the loadout has. */

    private double[] myUpdatedAtts;  /* The attributes the Warframe has after accounting for mods */
    private int myCapacity;          /* The number of points the loadout has */
    private int[] myLevels;          /* The levels of each of the mods */

    /**
     * Creates a new Warframe loadout.
     * @param theWarframe The Warframe the loadout is based on.
     */
    public WarframeLoadout(Warframe theWarframe, String theName, String theAuthor) {
        setupLoadout(theWarframe, theName, theAuthor);
    }

    /**
     * Creates a new loadout with a null warframe and a default name.
     */
    public WarframeLoadout(String theAuthor) {
        setupLoadout(null, "New Loadout", theAuthor);
    }

    /**
     * Helper method to initialize a new Warframe Layout.
     * @param theWarframe The Warframe the loadout is based on.
     */
    private void setupLoadout(Warframe theWarframe, String theName, String theAuthor) {
        myLoadoutName = theName;
        myWarframe = theWarframe;
        myAuthor = theAuthor;
        myReactor = false;
        myMods = new Mod[2];
        myUpdatedAtts = new double[9];
        myCapacity = 30;
        myPolarities = new int[2];
        myLevels = new int[2];

        //initializing UpdatedAtts array.
        myUpdatedAtts[0] = theWarframe.getMyHealth();
        myUpdatedAtts[1] = theWarframe.getMyShields();
        myUpdatedAtts[2] = theWarframe.getMyArmor();
        myUpdatedAtts[3] = theWarframe.getMyPower();
        myUpdatedAtts[4] = theWarframe.getMySprintSpeed();
        myUpdatedAtts[5] = theWarframe.getMyPwrRange();
        myUpdatedAtts[6] = theWarframe.getMyPwrStrength();
        myUpdatedAtts[7] = theWarframe.getMyPwrDuration();
        myUpdatedAtts[8] = theWarframe.getMyPwrEfficency();

        //initializing Polarities array.
        myPolarities[0] = 0;
        myPolarities[1] = 0;
    }

    /**
     * Calculates remaining capacity.
     * @return remamining capacity.
     */
    public int calculateRemainingCapacity() {
        int result = myCapacity;
        for (int i = 1; i < 2; i++) {
            if (myMods[i] != null) {
                result -= myMods[i].calculateCost(myPolarities[i], myLevels[i]);
            }
        }
        return result;
    }

    /**
     * Checks the mod change to make sure that there is enough capacity to make the desired mod
     * change. This is done anytime any level or mod change is seen and is done before any mod
     * changes are committed.
     * @param theMod the new Mod to put in.
     * @param theLevel the Level of the mod to put in.
     * @param theSlot the slot the new mod would go.
     * @return whether the changed mod could be placed without errors.
     */
    public boolean validateModChange(Mod theMod, int theLevel, int theSlot) {
        int testCapacity = this.calculateRemainingCapacity();
        if (myMods[theSlot] != null) {
            testCapacity += myMods[theSlot].calculateCost(myPolarities[theSlot], myLevels[theSlot]);
        }
        testCapacity -= theMod.calculateCost(myPolarities[theSlot], theLevel);
        return (testCapacity >= 0) && (theSlot >= 0 && theSlot < 2);
    }

    /**
     * Changes/updates the mod at it's current slot.
     * @param theMod the new Mod being placed.
     * @param theLevel the level of the Mod being placed.
     * @param theSlot The slot the mod ia being placed in.
     */
    public void changeMod(Mod theMod, int theLevel, int theSlot) {
        if (validateModChange(theMod, theLevel, theSlot)) {
            myMods[theSlot] = theMod;
            myLevels[theSlot] = theLevel;
            calculateRemainingCapacity();
        }
    }

    /**
     * Clears a mod spot, making it empty.
     * @param theSlot the mod slot to clear.
     */
    public void clearMod(int theSlot) {
        myMods[theSlot] = null;
        myLevels[theSlot] = 0;
        calculateRemainingCapacity();

        if (theSlot == 0) { //Aura mods affect maximum capacity
            updateMaxCapacity();
        }
    }

    /**
     * Udpates the max capacity of the Warframe when changes to reactor and aura mods are made.
     * @return the updated max capacity of the loadout.
     */
    public int updateMaxCapacity() {
        int result = 30;
        //Checks if the reactor is installed.
        if (myReactor) {
            result = result * 2;
        }
        //Adds the value from the Aura mod if necessary,
        if (myMods[0] != null) {
            result = result + myMods[0].calculateCost(myPolarities[0], myLevels[0]);
        }
        myCapacity = result;
        return result;
    }

    /**
     * Turns off or on the reactor. It affects the capacity of the loadout.
     * @param theStatus
     */
    public void toggleReactor(boolean theStatus) {
        myReactor = theStatus;
        updateMaxCapacity();
    }

    /**
     * Checks to make sure that the toggle for reactor can be performed without causing errors.
     * @param theStatus the desired toggle for the reactor status
     * @return Whether the toggle reactor can be done without causing errors.
     */
    public boolean validateReactor(boolean theStatus) {
        if (theStatus == false && (myCapacity - calculateRemainingCapacity()) < 30) {
            return false;
        }
        return true;
    }

    /**
     * Sets the polarity of a selected slot.
     * @param theSlot the slot that is affected by the change
     * @param thePolarity the updated polarity of the given slot.
     */
    public void setPolarity(int theSlot, int thePolarity) {
        if (thePolarity >= 0 && thePolarity <= 4 && theSlot >= 0 && theSlot <= 9) {
            myPolarities[theSlot] = thePolarity;
        }
    }

    //Getters
    public String getMyLoadoutName() {
        return myLoadoutName;
    }

    public String getMyAuthor() {
        return myAuthor;
    }

    public Warframe getMyWarframe() {
        return myWarframe;
    }

    public Boolean getMyReactor() {
        return myReactor;
    }

    public Mod[] getMyMods() {
        return myMods;
    }

    public int[] getMyPolarities() {
        return myPolarities;
    }

    public double[] getMyUpdatedAtts() {
        return myUpdatedAtts;
    }

    public int getMyCapacity() {
        return myCapacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Loadout name: " + myLoadoutName + "\n");
        sb.append("Author: " + myAuthor + "\n");
        sb.append("Warframe Name: " + myWarframe.getMyCharName() + "\n");
        sb.append("Orokin Reactor Enabled?: " + myReactor + "\n");
        sb.append("First mod: " + myMods[0].getMyName() + ", Level: " + myLevels[0] + "\n");
        sb.append("Second mod: " + myMods[1].getMyName() + ", Level: " + myLevels[1] + "\n");
        return sb.toString();
    }
}
