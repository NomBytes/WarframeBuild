package com.example.nombi.warframebuild;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nombi.warframebuild.character.Warframe;
import com.example.nombi.warframebuild.dummy.DummyContent;
import com.example.nombi.warframebuild.loadout.Mod;
import com.example.nombi.warframebuild.loadout.WarframeLoadout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MenuActivity extends AppCompatActivity implements
        WarframeFragment.OnListFragmentInteractionListener,
        ModFragment.OnListModInteractionListener,
        LoadoutCreateFragment.addLoadout,
        PersonalLoadoutsFragment.personalLoadouts{
        String CREATE_TAG = "CREATE_LOADOUT";

         Bundle args = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuInflater inflater = getMenuInflater();
        Bundle extras = getIntent().getExtras();

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

       // if(!email.isEmpty()){
            args.putSerializable("email",email);
      //  }
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            Log.d("ONCREATE", "List");
            PersonalLoadoutsFragment fragment = new PersonalLoadoutsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment,fragment.TAG)
                    .commit();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoadoutCreateFragment LoadoutCreateFragment = new LoadoutCreateFragment();



                LoadoutCreateFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, LoadoutCreateFragment,
                        LoadoutCreateFragment.CREATE_TAG)
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

        if(id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, new WarframeLoadout("author").toString());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }
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

        detailFragment.setArguments(args);


        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
    /*
    @Override
    public void addLoadout(Uri uri) {

    }
    */

    /**
     * allows to go back to the previous screen
     */
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        LoadoutCreateFragment myFragment = (LoadoutCreateFragment)getSupportFragmentManager()
                .findFragmentByTag(LoadoutCreateFragment.CREATE_TAG);
        PersonalLoadoutsFragment fragment = (PersonalLoadoutsFragment)getSupportFragmentManager()
                .findFragmentByTag(PersonalLoadoutsFragment.TAG);
        if (myFragment != null && myFragment.isVisible()) {

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();

    }

    /**
     * callls the detail fragment when mod item is pressed on the list.
     * Alows us to view mod details
     *
     * @param m mod were viewing.
     */
    @Override
    public void OnListModInteractionListener(Mod m) {
        ModDetailFragment detailFragment
                = ModDetailFragment.getModDetailFragment(m);


        args.putSerializable(detailFragment.MOD_SELECTED,m);


        detailFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null);


        // Commit the transaction
        transaction.commit();

    }

    /**
     * for intereacting iwth loadout creator.
     * @param w
     */
    @Override
    public void addLoadout(WarframeLoadout w,String url) {
        AddLoadoutTask task = new AddLoadoutTask();
        PersonalLoadoutsFragment fragment = (PersonalLoadoutsFragment)getSupportFragmentManager()
                .findFragmentByTag(PersonalLoadoutsFragment.TAG);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();

        fragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        task.execute(new String[]{url.toString()});


        //WarframeDetailFragment detailFragment = WarframeDetailFragment.WARFRAME_SELECTED(w)

    }

    @Override
    public void personalLoadouts(String names) {
        /*ModDetailFragment detailFragment
                = ModDetailFragment.getModDetailFragment(m);*/


        //args.putSerializable(detailFragment.MOD_SELECTED,m);

        /*
        detailFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null);


        // Commit the transaction
        transaction.commit();*/

    }

    private class AddLoadoutTask extends AsyncTask<String,Void,String>{
        /**
         * prepares urls
         * @param urls
         * @return
         */
        @Override
        protected String doInBackground(String... urls) {
            Log.d("url", urls.toString());
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to add course, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * It checks to see if there was a problem with the URL(Network) which is when an
         * exception is caught. It tries to call the parse Method and checks to see if it was successful.
         * If not, it displays the exception.
         *
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.


            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if (status.equals("success")) {
                    Toast.makeText(getApplicationContext(), "load successfully added!"
                            , Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to add: "
                                    + jsonObject.get("error")
                            , Toast.LENGTH_LONG)
                            .show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
