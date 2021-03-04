package com.rabiya.deawooexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{


    private EditText Email,Password;
    private TextView signup;
    private Button Login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        Login= (Button) findViewById(R.id.Login);
        Login.setOnClickListener(this);

        signup=(TextView)findViewById(R.id.signup);
        signup.setOnClickListener(this);

        Email=(EditText)findViewById(R.id.Email);
        Password=(EditText)findViewById(R.id.Password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup:
                startActivity(new Intent(this,SignIn.class));
                break;

            case R.id.Login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String useremail=Email.getText().toString().trim();
        String pass=Password.getText().toString().trim();

        if(useremail.isEmpty()){
            Email.setError("Email is Required!");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){

            Email.setError("Please enter a valid email");
            Email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            Password.setError("Password is Required!");
            Password.requestFocus();
            return;
        }
        if(pass.length() < 6){
            Password.setError("Min password length should be 6 characters!");
            Password.requestFocus();
            return;

        }

        mAuth.signInWithEmailAndPassword(useremail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this,Dash.class));
                }else {
                    Toast.makeText(Login.this,"Faild to Login!",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
