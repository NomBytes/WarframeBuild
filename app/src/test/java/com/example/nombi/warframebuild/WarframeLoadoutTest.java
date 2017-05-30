package com.example.nombi.warframebuild;

import com.example.nombi.warframebuild.character.Warframe;
import com.example.nombi.warframebuild.loadout.Mod;
import com.example.nombi.warframebuild.loadout.WarframeLoadout;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class to test the WarframeLoadout class.
 * @author Calvin
 * @version 17.05.30
 */

public class WarframeLoadoutTest extends TestCase{

    private Mod auraMod;                                                     //Aura mod for testing
    private Mod otherMod;                                                    //Regular mod for testing

    final double[] ashStats = {450, 300, 65, 150, 1.15, 100, 100, 100, 100}; //Warframe stats for testing
    private Warframe warframe;                                               //Warframe for testing
    private WarframeLoadout loadout;                                         //Loadout for testing

    /**
     * Sets up the fields before each test.
     * @throws Exception on error.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        warframe = new Warframe(ashStats, "Ash", "Ash");
        auraMod = new Mod("aura",0,1,2,5,1);
        otherMod = new Mod("other", 0,0,4,15,2);
        loadout = new WarframeLoadout(warframe, "loadout", "tester");
    }

    /**
     * Makes all fields null.
     * @throws Exception on error.
     */
    @Override
    protected void tearDown() throws Exception {
        warframe = null;
        auraMod = null;
        otherMod = null;
        loadout = null;
    }

    /**
     * Tests the Capacity methods.
     */
    @Test
    public void testCapacity() {
        //initial capacity
        assertEquals("Error! initial capacity not 30!", 30, loadout.getMyCapacity());

        //adding a aura mod
        loadout.changeMod(auraMod, 0, 0);
        loadout.updateMaxCapacity();
        assertEquals("Error! aura mod doesn't add to capacity", 32, loadout.getMyCapacity());

        //Changing polarity to one that matches the mod
        loadout.setPolarity(0, 1);
        loadout.updateMaxCapacity();
        assertEquals("Error! matching polarity on aura doesn't add", 34, loadout.getMyCapacity());

        //changing the level of the mod
        loadout.changeMod(auraMod, 5, 0);
        loadout.updateMaxCapacity();
        assertEquals("Error! changing level doesn't affect capacity", 44, loadout.getMyCapacity());

        //adding a reactor to the loadout. shouldn't affect amount aura affects capacity.
        loadout.toggleReactor(true);
        loadout.updateMaxCapacity();
        assertEquals("Error! toggling reactor doesn't add capacity", 74, loadout.getMyCapacity());

        // changing polarity to one that doesn't match the mod and is polarized.
        loadout.setPolarity(0, 2);
        loadout.updateMaxCapacity();
        assertEquals("Error! polarized non-matching slot doesn't decrease aura effect", 65, loadout.getMyCapacity());

        // changing mod slot to no polarity
        loadout.setPolarity(0, 0);
        loadout.updateMaxCapacity();
        assertEquals("Error! polarized non-matching slot doesn't decrease aura effect", 67, loadout.getMyCapacity());
    }

    /**
     * Tests the calculateRemainingCapacity() method.
     */
    @Test
    public void testCalculateRemainingCapacity() {
        //adding a mod
        loadout.changeMod(otherMod, 0, 1);
        assertEquals("Adding a normal mod doesn't decrease remaining capacity", 26,
                loadout.calculateRemainingCapacity());
        //changing a level one of the mods

        loadout.changeMod(otherMod, 15, 1);
        assertEquals("Changing a mod's level doesn't change remaining capacity", 11,
                loadout.calculateRemainingCapacity());

        //changing level and then polarity to a non-matching one.
        loadout.changeMod(otherMod, 12, 1);
        loadout.setPolarity(1, 1);
        assertEquals("non-matching polarity doesn't increase cost", 10,
                loadout.calculateRemainingCapacity());

        //changing polarity to match the mod
        loadout.setPolarity(1, 2);
        assertEquals("matching polarity doesn't increase cost", 22,
                loadout.calculateRemainingCapacity());
    }

    /**
     * Tests the toggleReactor() method.
     */
    @Test
    public void testToggleReactor() {
        //try setting toggle on
        loadout.toggleReactor(true);
        loadout.updateMaxCapacity();
        assertEquals("Toggle didn't work", 60, loadout.getMyCapacity());

        //try turning off toggle when capacity remaining < 30
        loadout.changeMod(new Mod("other", 0,0,4,30,2), 30, 1);
        loadout.toggleReactor(false);
        assertEquals("Toggle didn't work", 60, loadout.getMyCapacity());
    }

    /**
     * Tests the changeMod() method
     */
    @Test
    public void testChangeMod() {
        //changing mods to one that should work
        loadout.changeMod(new Mod("oldMod", 0,0,4,30,2), 0, 1); //working mod
        assertEquals("Mod shouldn have changed to 'old mod'", "oldMod",
                loadout.getMyMods()[1].getMyName());

        //attempt mod with negative level
        loadout.changeMod(new Mod("newMod", 0,0,4,30,2), -1, 1);
        assertEquals("Mod shouldn't have changed(level < 0)", "oldMod",
                loadout.getMyMods()[1].getMyName());

        //attempt mod with level over max
        loadout.changeMod(new Mod("newMod", 0,0,4,10,2), 11, 1);
        assertEquals("Mod shouldn't have changed (lvl over max)", "oldMod",
                loadout.getMyMods()[1].getMyName());

        //attempt to add mod that is too big for loadout
        loadout.changeMod(new Mod("newMod", 0,0,4,50,2), 31, 1);
        assertEquals("Mod shouldn't have changed (too big)", "oldMod",
                loadout.getMyMods()[1].getMyName());

        //add the same mod after expanding the current capacity
        loadout.toggleReactor(true);
        loadout.changeMod(new Mod("newMod", 0,0,4,50,2), 31, 1);
        assertEquals("Mod should have been changed to 'newMod'", "newMod",
                loadout.getMyMods()[1].getMyName());
    }

    /**
     * Tests the clearMod() method.
     */
    @Test
    public void testClearMod() {
        loadout.changeMod(new Mod("newMod", 0,0,4,50,2), 31, 1);
        loadout.clearMod(1);
        assertNull("Mod not null", loadout.getMyMods()[1]);
    }

    /**
     * Tests the setPolarity() method.
     */
    @Test
    public void testSetPolarity() {
        //try setting polarity when conditions won't accept because of overflow
        loadout.toggleReactor(false);
        loadout.changeMod(new Mod("newMod", 0,0,4,26,2), 26, 1);
        loadout.setPolarity(1, 3);
        assertEquals("Polarity shouldn't have changed", 0, loadout.getMyPolarities()[1]);

        //set polarity to match mod, should work
        loadout.setPolarity(1, 2);
        assertEquals("Polarity should be at '2'", 2, loadout.getMyPolarities()[1]);

        //set polarity to a value outside of range
        loadout.setPolarity(1, 6);
        assertEquals("Polarity should be at '2', not 6", 2, loadout.getMyPolarities()[1]);
    }
}
