package com.rabiya.deawooexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launch extends AppCompatActivity implements View.OnClickListener{
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        login= (Button) findViewById(R.id.login);
        signup= (Button) findViewById(R.id.signup);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.login : i = new Intent(this,Login.class);startActivity(i);break;
            case R.id.signup : i = new Intent(this,SignIn.class);startActivity(i);break;
            default:break;

        }
    }
}
