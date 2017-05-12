package com.example.nombi.warframebuild;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.nombi.warframebuild.character.Warframe;

public class MenuActivity extends AppCompatActivity implements
        WarframeFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WarframeFragment warframeFragment = new WarframeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, warframeFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Warframe warframe) {
        if(warframe != null){
            Log.d("insideOnlistFragment", "warframe isn't nul");
        }
        WarframeDetailFragment detailFragment
                = WarframeDetailFragment.getWarframeDetailFragment(warframe);

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}
