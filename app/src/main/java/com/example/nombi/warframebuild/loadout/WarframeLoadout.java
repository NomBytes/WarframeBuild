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

}
