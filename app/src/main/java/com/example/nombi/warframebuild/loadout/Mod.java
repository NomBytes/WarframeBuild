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



    public Mod(String myName, int myRarity, int myType, int myBaseCost, int myMaxLevel, int myPolarity) {
        this.myName = myName;
        this.myRarity = myRarity;
        this.myType = myType;
        this.myBaseCost = myBaseCost;
        this.myMaxLevel = myMaxLevel;
        this.myPolarity = myPolarity;
    }


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
