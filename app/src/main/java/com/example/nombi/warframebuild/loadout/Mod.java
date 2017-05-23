package com.example.nombi.warframebuild.loadout;

/**
 * A mod changes stats of a particular object.
 * @author Calvin
 * @version 17.05.04
 */

public class Mod {
    //Name and Rarity
    private String myName;
    private int myRarity;
    private int myType;

    //Cost-related
    private int myBaseCost;
    private int myMaxLevel;

    private int myPolarity;

    //Rarity
    private static int RARITY_COMMON    = 0;
    private static int RARITY_UNCOMMON  = 1;
    private static int RARITY_RARE      = 2;
    private static int RARITY_LEGENDARY = 3;

    //Polarity
    private static int POLARITY_NONE    = 0;
    private static int POLARITY_MADURAI = 1;
    private static int POLARITY_VAZARIN = 2;
    private static int POLARITY_NARAMON = 3;
    private static int POLARITY_ZENURIK = 4;


    //Mod type
    private static int MODTYPE_NORMAL   = 0;
    private static int MODTYPE_AURA     = 1;
}
