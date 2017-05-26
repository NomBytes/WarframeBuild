package com.example.nombi.warframebuild.loadout;

import com.example.nombi.warframebuild.character.Warframe;

/**
 * A loadout for a Warframe.
 * @author Calvin
 * @version 17.05.04
 */
public class WarframeLoadout {

    private String myLoadoutName; /* The name of the loadout. */
    private String myAuthor;      /* The author of the loadout */

    private Warframe myWarframe;  /* The Warframe that the loadout is based on. */
    private Boolean myReactor;    /* Reactor install toggle */

    private Mod[] myMods;         /* The mods that are installed on the loadout */
    private int[] myPolarities;   /* The polarity slots the loadout has. */

    private double[] myUpdatedAtts;  /* The attributes the Warframe has after accounting for mods */
    private int myCapacity;       /* The number of points the loadout has */

    /**
     * Creates a new Warframe loadout.
     * @param theWarframe The Warframe the loadout is based on.
     */
    public WarframeLoadout(Warframe theWarframe, String theName) {
        setupLoadout(theWarframe, theName);
    }

    /**
     * Helper method to initialize a new Warframe Layout.
     * @param theWarframe The Warframe the loadout is based on.
     */
    private void setupLoadout(Warframe theWarframe, String theName) {
        myLoadoutName = theName;
        myWarframe = theWarframe;
        myReactor = false;
        myMods = new Mod[10];
        myUpdatedAtts = new double[9];
        myCapacity = 30;
        myPolarities = new int[10];

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
        myPolarities[2] = 0;
        myPolarities[3] = 0;
        myPolarities[4] = 0;
        myPolarities[5] = 0;
        myPolarities[6] = 0;
        myPolarities[7] = 0;
        myPolarities[8] = 0;
        myPolarities[9] = 0;
    }

    /**
     * Calculates remaining capacity.
     * @return remamining capacity.
     */
    public int calculateRemainingCapacity() {
        int result = myCapacity;
        for (int i = 1; i < 10; i++) {
            if (myMods[i] != null) {
                myCapacity -= myMods[i].calculateCost(myPolarities[i], 0);
            }
        }
        return result;
    }

    /**
     * Checks the mod change to make sure that there is enough capacity to make the desired mod change.
     * @param theMod the new Mod to put in.
     * @param theLevel the Level of the mod to put in.
     * @param theSlot the slot the new mod would go.
     * @return whether the changed mod could be placed without errors.
     */
    public boolean validateModChange(Mod theMod, int theLevel, int theSlot) {
        int testCapacity = this.calculateRemainingCapacity();
        if (myMods[theSlot] != null) {
            testCapacity += myMods[theSlot].calculateCost(myPolarities[theSlot], 0);
        }
        testCapacity -= theMod.calculateCost(myPolarities[theSlot], theLevel);
        return testCapacity >= 0;
    }


    /**
     * Udpates the max capacity of the Warframe when changes to reactor and aura mods are made.
     * @return the updated max capacity of the loadout.
     */
    public int updateMaxCapacity() {
        int result = 30;
        if (myReactor) {
            result = result * 2;
        }
        if (myMods[0] != null) {
            result = result + myMods[0].calculateCost(myPolarities[0], 0);
        }
        myCapacity = result;
        return result;
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

}
