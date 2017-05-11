package com.example.nombi.warframebuild;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        WarframeDetailFragment courseDetailFragment = new WarframeDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(WarframeDetailFragment.WARFRAME_SELECTED, warframe);
        courseDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace((R.id.fragment_container), courseDetailFragment)
                .addToBackStack(null)
                .commit();

    }

}
