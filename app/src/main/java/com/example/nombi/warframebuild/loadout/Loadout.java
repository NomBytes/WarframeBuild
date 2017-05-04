package com.example.nombi.warframebuild.loadout;

import com.example.nombi.warframebuild.character.*;
import com.example.nombi.warframebuild.weapon.*;

/**
 * A abstract class that represents the loadout of a particular object.
 * @author Calvin
 * @version 17.05.04
 */

abstract class Loadout {

    private String myType;
    private Mod[] myMods;
    private int myCapcity;
    private int[] myPolarities;

    private Weapon myWeapon;
    private Character myCharacter;
    
    
}
