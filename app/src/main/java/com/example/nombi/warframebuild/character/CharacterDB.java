package com.example.nombi.warframebuild.character;

/**
 * Temporary database that contains the characters (mainly Warframes)
 * to use their base stats for creating loadouts.
 * @author Calvin
 * @version 17.05.11
 */
public final class CharacterDB {

    /*
     * Array of warframes' stats
     */
    public static final double[][] WarframeStats = {
            {450, 300, 65, 150, 1.15, 100, 100, 100, 100}, //Ash
            {450, 300, 150, 150, 1.2, 100, 100, 100, 100}, //Ash Prime
            {300, 300, 450, 225, 0.9, 100, 100, 100, 100}, //Atlas
            {300, 300, 15, 225, 1.1, 100, 100, 100, 100},  //Banshee
            {300, 300, 65, 262.5, 1.15, 100, 100, 100, 100}, //Banshee Prime
            {300, 300, 350, 225, 1, 100, 100, 100, 100}, //Chroma
            {300, 300, 100, 225, 1.1, 100, 100, 100, 100}, //Ember
            {300, 375, 125, 225, 1.1, 100, 100, 100, 100}, //Ember Prime
            {300, 300, 100, 225, 1.15, 100, 100, 100, 100}, //Equinox
            {300, 300, 225, 150, 1, 100, 100, 100, 100}, //Excalibur
            {300, 300, 250, 150, 1, 100, 100, 100, 100}, //Excalibur Prime
            {300, 450, 300, 150, 0.95, 100, 100, 100, 100}, //Frost
            {300, 525, 300, 150, 0.95, 100, 100, 100, 100}//Frost Prime
    };

    public static final Warframe[] Warframes =
            {new Warframe(WarframeStats[0], "Ash", "Ash"),
             new Warframe(WarframeStats[1], "Ash", "Ash Prime"),
             new Warframe(WarframeStats[2], "Atlas", "Atlas"),
             new Warframe(WarframeStats[3], "Banshee", "Banshee"),
             new Warframe(WarframeStats[4], "Banshee", "Banshee Prime"),
             new Warframe(WarframeStats[5], "Chroma", "Chroma"),
             new Warframe(WarframeStats[6], "Ember", "Ember"),
             new Warframe(WarframeStats[7], "Ember", "Ember Prime"),
             new Warframe(WarframeStats[8], "Equinox", "Equinox"),
             new Warframe(WarframeStats[9], "Excalibur", "Excalibur"),
             new Warframe(WarframeStats[10], "Excalibur", "Exclibur Prime"),
             new Warframe(WarframeStats[11], "Frost", "Frost"),
             new Warframe(WarframeStats[12], "Frost", "Frost Prime")};
}
