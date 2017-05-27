package com.example.nombi.warframebuild.loadout;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * A mod changes stats of a particular object.
 * @author Calvin
 * @version 17.05.04
 */
public class Mod implements Serializable {
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

    public static final String MOD_NAME = "mod_name", BASE_COST = "base_cost",
    MAX_LEVEL = "max_level", POLARITY = "polarity", ATT = "attribute",
    EFFECT = "effect", AURA = "aura", RARITY = "rarity";

    /**
     * Creates a new mod.
     * @param myName Name of the mod
     * @param myRarity Rarity of the mod
     * @param myType Type of mod
     * @param myBaseCost The base cost of the mod
     * @param myMaxLevel The max level of the mod
     * @param myPolarity The polarity of the mod
     */
    public Mod(String myName, int myRarity, int myType, int myBaseCost, int myMaxLevel, int myPolarity) {
        this.myName = myName;
        this.myRarity = myRarity;
        this.myType = myType;
        this.myBaseCost = myBaseCost;
        this.myMaxLevel = myMaxLevel;
        this.myPolarity = myPolarity;
    }

    /**
     * Calcuates the cost of the mod, given the polarity and mod level.
     * Auras "cost" will actually add to the capacity rather than decrease it.
     * @param theSlotPolarity
     * @param theModLevel
     * @return
     */
    public int calculateCost(int theSlotPolarity, int theModLevel) {
        int result = myBaseCost + theModLevel;
        if (theSlotPolarity == myPolarity) {
            if (myType == 0) { //Normal mod
                result = result / 2;
            } else {           //Aura mod
                result = result * 2;
            }
        } else if (theSlotPolarity != 0) {
            if (myType == 0) { //Normal mod
                result = (int) (result * 1.25);
            } else {           //Aura mod
                result = (int) (result * 0.8);
            }
        }
        return result;
    }

    //Getters (once created, the mods are not supposed to be editable. The current level of the mod
    //is stored in the loadout class, but the fields here are never supposed to change)
    public String getMyName() {
        return myName;
    }

    public int getMyRarity() {
        return myRarity;
    }

    public int getMyType() {
        return myType;
    }

    public int getMyBaseCost() {
        return myBaseCost;
    }

    public int getMyMaxLevel() {
        return myMaxLevel;
    }

    public int getMyPolarity() {
        return myPolarity;
    }
    /*
    CREATE TABLE Modification (
            mod_name VARCHAR (32) NOT NULL PRIMARY KEY,
    base_cost INT NOT NULL,
    max_level INT NOT NULL,
    polarity  INT NOT NULL,
    attribute INT NOT NULL,
    effect DECIMAL(9, 2) NOT NULL,
    aura BOOLEAN NOT NULL,
    rarity INT NOT NULL
    );
    */
    public static String parseCourseJSON(String courseJSON, List<Mod> ModList) {
        String reason = null;

        Log.d("courseJSON", courseJSON);
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);

                    Mod m = new Mod(obj.getString(MOD_NAME),obj.getInt(RARITY),obj.getInt(BASE_COST),obj.getInt(MAX_LEVEL),
                            obj.getInt(BASE_COST),obj.getInt(POLARITY));

                    ModList.add(m);

                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }



}
