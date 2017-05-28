package com.example.nombi.warframebuild.character;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A base warframe object
 * @author Calvin
 * @version 17.05.02
 */

public class Warframe extends Character {

    public static final double[] DEFAULTASH = {450, 300, 65, 150, 1.15, 100, 100, 100, 100};

    public static final String CHARA ="wfChar",
            HEALTH = "wfHealth_float", SHEILDS = "wfShields_float", ARMOR ="wfArmor_float",
            POWER = "wfPower_float", SPEED = "wfSpeed_float", RANGE = "wfRange_float",
            STRENGTH ="wfStrength_float", DURATION= "wfDuration_float", EFFICENCY = "wfEfficency_float",
            COMPTYPE = "wfCompType", BASE = "wfBase",TYPE = "wfType";


    /**
     * Creates a new warframe.
     * @param theAtts The attributes of the Warframe.
     * @param theBase The base warframe (Ash Prime's base is Ash, etc.)
     * @param theName The full name of the Warframe
     */
    public Warframe (double[] theAtts, String theBase, String theName) {
        super(theAtts, theBase, theName, "N/A", "Warframe");
    }

    public Warframe() {
        super(DEFAULTASH, "Ash", "Ash", "N/A", "Warframe");
        
    }

    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns course list if success.
     * @param courseJSON
     * @return reason or null if successful.
     */
    public static String parseCourseJSON(String courseJSON, List<Warframe> warframeList) {
        String reason = null;

        Log.d("courseJSON", courseJSON);
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    double[] atts = {obj.getDouble(Warframe.HEALTH),obj.getDouble(Warframe.SHEILDS),
                            obj.getDouble(Warframe.ARMOR),obj.getDouble(Warframe.POWER)
                            ,obj.getDouble(Warframe.SPEED), obj.getDouble(Warframe.RANGE),
                            obj.getDouble(Warframe.STRENGTH), obj.getDouble(Warframe.DURATION),
                            obj.getDouble(Warframe.EFFICENCY)};

                    Warframe warframes = new Warframe(atts, obj.getString(Warframe.COMPTYPE),//where n/a gets pass
                            obj.getString(Warframe.CHARA));
                    warframeList.add(warframes);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }



}
