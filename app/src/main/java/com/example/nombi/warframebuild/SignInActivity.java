package com.example.nombi.warframebuild;

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
    Button login;
    Button register;
    EditText email;
    EditText password;
    boolean loginSucess;

    private static String URL;

    private final static String USER_LOGIN
            = "http://cssgate.insttech.washington.edu/~_450bteam13/login.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);
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

                }

            }
        });
    }


    public boolean validate(String s){

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(s);
        boolean matchFound = m.matches();

        return matchFound;
    }

    public void register(View v){
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        String URL = buildUserURL(v);
        Log.d("url",URL);
        addUser(URL);



    }
    public void launch(View v){
        DialogFragment fragment = null;


    }
    public void login(View view){
        this.email = (EditText)findViewById(R.id.email);
        this.password = (EditText)findViewById(R.id.password);
        String URL = buildUserURL(view);
        DialogFragment fragment = null;
        loginUser(URL);

       /* if(!validate(email.toString())){
            fragment = new UserDialogFragment();
            fragment.show(getSupportFragmentManager(), "launch");
        }*/

    }
    public void loginUser(String url){
        LoginUserTask task = new LoginUserTask();
        task.execute(new String[]{url.toString()});
    }

    public void addUser(String url) {
        AddUserTask task = new AddUserTask();
        task.execute(new String[]{url.toString()});

// Takes you back to the previous fragment by popping the current fragment out.



    }
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
    private class AddUserTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

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
    private class LoginUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

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
                    loginSucess = true;
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
