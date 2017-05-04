package com.example.nombi.warframebuild.character;

/**
 * A base warframe object
 * @author Calvin
 * @version 17.05.02
 */

public class Warframe extends Character {

    public Warframe (int[] theAtts, double theSpeed, String theBase, String theName) {
        super(theAtts, theSpeed, theBase, theName, "N/A", "Warframe");
    }
}
