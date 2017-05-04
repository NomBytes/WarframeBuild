package com.example.nombi.warframebuild.loadout;

import com.example.nombi.warframebuild.character.Warframe;

/**
 * A loadout for a Warframe.
 * @author Calvin
 * @version 17.05.04
 */
public class WarframeLoadout {

    private String myLoadoutName; /* The name of the loadout. */

    private Warframe myWarframe;  /* The Warframe that the loadout is based on. */

    private Mod[] myMods;         /* The mods that are installed on the loadout */
    private int[] myPolarities;   /* The polarity slots the loadout has. */

    private int[] myUpdatedAtts;  /* The attributes the Warframe has after accounting for mods */
    private int myCapacity;       /* The number of points the loadout has */

    /**
     * Creates a new Warframe loadout.
     * @param theWarframe The Warframe the loadout is based on.
     */
    public WarframeLoadout(Warframe theWarframe) {
        setupLoadout(theWarframe);
    }

    /**
     * Helper method to initialize a new Warframe Layout.
     * @param theWarframe The Warframe the loadout is based on.
     */
    private void setupLoadout(Warframe theWarframe) {
        myLoadoutName = "Default Loadout";
        myWarframe = theWarframe;
        myMods = new Mod[10];
        myUpdatedAtts = new int[8];
        myCapacity = 30;
        myPolarities = new int[10];

        //initializing UpdatedAtts array.
        myUpdatedAtts[0] = 0;
        myUpdatedAtts[1] = 0;
        myUpdatedAtts[2] = 0;
        myUpdatedAtts[3] = 0;
        myUpdatedAtts[4] = 0;
        myUpdatedAtts[5] = 0;
        myUpdatedAtts[6] = 0;
        myUpdatedAtts[7] = 0;

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
