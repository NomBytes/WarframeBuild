package com.example.nombi.warframebuild;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nombi on 5/11/2017.
 */

public class user implements Serializable {
    String email;
    String password;

    public static final String EMAIL = "email", PASSWORD = "password";

    public user(String email, String password) {
        this.email = email;
        this.password = password;

    }

    /**
     * gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets password
     * @return passwrod
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets users and pareses it into a string
     * @param userJSON
     * @param userList
     * @return
     */
    public static String parseUserJSON(String userJSON, List<user> userList) {
        user u = null;
        String reason = null;
        Log.d("userJson parse user",userJSON);
        //userJSON = "[" + userJSON +"]";
        if (userJSON != null) {
            try {
                JSONArray arr = new JSONArray(userJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    u = new user(obj.getString(u.EMAIL), obj.getString(u.PASSWORD));
                    Log.d("emailin parse",u.EMAIL);
                    userList.add(u);
                }
            } catch (JSONException e) {
                Log.d("JSON",userJSON);
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }


}
