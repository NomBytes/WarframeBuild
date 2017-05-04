package com.example.nombi.warframebuild;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button register;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login(v);

            }
        });
    }

    public void login(View view){
        this.email = (EditText)findViewById(R.id.email);
        this.password = (EditText)findViewById(R.id.password);
        DialogFragment fragment = null;

        if(!validate(email.toString())){
            fragment = new UserDialogFragment();
            fragment.show(getSupportFragmentManager(), "launch");
        }

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



    }
    public void launch(View v){
        DialogFragment fragment = null;


    }
}
