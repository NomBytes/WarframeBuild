package com.example.nombi.warframebuild;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    /**
     * login button
     */
    Button login;
    /**
     * register button.
     */
    Button register;
    /**
     * email button
     */
    EditText email;
    /**
     * paswrod button.
     */
    EditText password;
    /**
     * sucessful login
     */
    boolean loginSucess;
    /**
     * shared preferences
     */
    private SharedPreferences mSharedPreferences;
    /**
     * ysed for writing toshard preferences.
     */
    SharedPreferences.Editor mEditor;
    /**
     * getting email.
     */
    private Intent mIntent;
    /**
     * URL for retreiving users.
     */
    private static String URL;
    /**
     * logs in.
     */
    private final static String USER_LOGIN
            = "http://cssgate.insttech.washington.edu/~_450bteam13/login.php?";

    /**
     * creates activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //loginSucess = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);
        mIntent = new Intent(getApplicationContext(),MenuActivity.class);
        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        if(mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN),true)){

            Intent i = new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(mIntent);
            finish();

        }

        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                URL = "http://cssgate.insttech.washington.edu/~_450bteam13/adduser.php?";
                register(v);
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                URL = "http://cssgate.insttech.washington.edu/~_450bteam13/login.php?";
                login(v);
                if(loginSucess){

                    //setContentView(R.layout.activity_menu);
                }

            }
        });
    }

    /**
     * registes usr.
     * @param v
     */
    public void register(View v){
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        if(!(email.toString().isEmpty()) &&
                !(password.toString().isEmpty())){

                mEditor.putString("email",email.getText().toString());
                mEditor.commit();
                mIntent.putExtra("email",email.getText().toString());
        }
        /*
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        */

        String URL = buildUserURL(v);
        //Log.d("url",URL);
        addUser(URL);



    }

    /**
     * launces
     * @param v
     */
    public void launch(View v){
        DialogFragment fragment = null;


    }

    /**
     * incharge of logging in and writing to local.
     * @param view
     */
    public void login(View view){
        this.email = (EditText)findViewById(R.id.email);
        this.password = (EditText)findViewById(R.id.password);
        String URL = buildUserURL(view);
        DialogFragment fragment = null;
        loginUser(URL);
        if(!(email.toString().isEmpty()) &&
                !(password.toString().isEmpty())){

            mEditor.putString("email",email.getText().toString());
            mEditor.commit();
            mIntent.putExtra("email",email.getText().toString());
        }


       /* if(!validate(email.toString())){
            fragment = new UserDialogFragment();
            fragment.show(getSupportFragmentManager(), "launch");
        }*/

    }

    /**
     * logs in user.
     * @param url
     */
    public void loginUser(String url){
        LoginUserTask task = new LoginUserTask();
        task.execute(new String[]{url.toString()});
    }

    /**
     * regisets user.
     * @param url
     */
    public void addUser(String url) {
        AddUserTask task = new AddUserTask();
        task.execute(new String[]{url.toString()});

// Takes you back to the previous fragment by popping the current fragment out.



    }

    /**
     * build's url.
     * @param v
     * @return
     */
    private String buildUserURL(View v) {

        StringBuilder sb = new StringBuilder(URL);

        try {

            String e = email.getText().toString();
            sb.append("email=");
            sb.append(e);


            String p = password.getText().toString();
            sb.append("&password=");
            sb.append(URLEncoder.encode(p, "UTF-8"));


            Log.i("Adduser", sb.toString());

        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    /**
     * innderclass for registerin.
     */
    private class AddUserTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * executes url
         * @param urls
         * @return
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for(String u: urls){
                Log.d("doinBackground",u);
            }

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
                    response = "Unable to add user, Reason: "
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
            Log.d("result", result);

            /*List<user> userList = new ArrayList<user>();
            result = user.parseUserJSON(result, userList);
            // Something wrong with the JSON returned.
            if (result != null) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }*/

            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if (status.equals("success")) {

                    Toast.makeText(getApplicationContext(), "user successfully added!"
                            , Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to add user: "
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

    /**
     * logs in user.
     */
    private class LoginUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * executes url.
         * @param urls
         * @return
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;


            for(String u: urls){
                Log.d("LogindoinBackground",u);
            }

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
                    response = "Unable to login user, Reason: "
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
         * alsoe checks on the login.
         *
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            Log.d("result", result);

            List<user> userList = new ArrayList<user>();
           /* result = user.parseUserJSON(result, userList);
            // Something wrong with the JSON returned.
            if (result != null) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }*/

            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if (status.equals("success")) {
                    //loginSucess = true;

                    mSharedPreferences
                            .edit()
                            .putBoolean(getString(R.string.LOGGEDIN), true)
                            .commit();

                    Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                    //i.putExtra("email",email.getText());
                    startActivity(mIntent);
                    finish();
                    Toast.makeText(getApplicationContext(), " successfully login!"
                            , Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong info"
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
