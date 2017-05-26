package com.example.nombi.warframebuild;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.nombi.warframebuild.character.Warframe;
import com.example.nombi.warframebuild.loadout.Mod;

public class MenuActivity extends AppCompatActivity implements
        WarframeFragment.OnListFragmentInteractionListener, /*LoadoutCreateFragment.OnFragmentInteractionListener,*/
        ModFragment.OnListModInteractionListener{

    Bundle args = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuInflater inflater = getMenuInflater();

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment LoadoutCreateFragment = new LoadoutCreateFragment();

                LoadoutCreateFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, LoadoutCreateFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    /**
     * ingflates stuff in the action bar.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_warframe; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_warframe, menu);
        return true;
    }

    /**
     *
     * @param item button being pressed
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }*/
        if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences =
                    getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                    .commit();
                    finish();

            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);

            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    /**
     * communicates with the warframe fragment when selecting an item on the list.
     * @param warframe
     */
    @Override
    public void onListFragmentInteraction(Warframe warframe) {
        if(warframe != null){
            Log.d("insideOnlistFragment", "warframe isn't nul");
        }


        WarframeDetailFragment detailFragment
                = WarframeDetailFragment.getWarframeDetailFragment(warframe);

        args.putSerializable(detailFragment.WARFRAME_SELECTED,warframe);


        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
    /*
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    */

    /**
     * allows to go back to the previous screen
     */
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();

    }

    /**
     * callls the detail fragment when mod item is pressed on the list.
     * @param m
     */
    @Override
    public void OnListModInteractionListener(Mod m) {
        ModDetailFragment detailFragment
                = ModDetailFragment.getModDetailFragment(m);


        args.putSerializable(detailFragment.MOD_SELECTED,m);



        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null);


        // Commit the transaction
        transaction.commit();

    }
}
