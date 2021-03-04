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
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    private TextView forlogin;
    private EditText username,email,password,conpassword;
    private FirebaseAuth mAuth;
    private Button sign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        forlogin=(TextView)findViewById(R.id.forlogin);
        forlogin.setOnClickListener(this);
        sign =(Button) findViewById(R.id.sign);
        sign.setOnClickListener(this);
        username=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        conpassword=(EditText)findViewById(R.id.conpassword);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.forlogin:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.sign:
                rigesterUser();
                break;

        }

    }

    private void rigesterUser() {

        final String name = username.getText().toString().trim();
        final String useremail = email.getText().toString().trim();
        String pass= password.getText().toString().trim();
        String conpass = conpassword.getText().toString().trim();

        if(name.isEmpty()){
            username.setError("Name is Required!");
            username.requestFocus();
            return;
        }
        if(useremail.isEmpty()){
            email.setError("Email is Required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){

            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            password.setError("Password is Required!");
            password.requestFocus();
            return;
        }

        if(pass.length() < 6){
            password.setError("Min password length should be 6 characters!");
            password.requestFocus();
            return;

        }
        if(conpass.isEmpty()){
            conpassword.setError("Confirm Password!");
            conpassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(useremail,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Regist regist = new Regist(name,useremail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(regist).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignIn.this,"Registered Successfully!",Toast.LENGTH_LONG).show();

                                    }else {
                                        Toast.makeText(SignIn.this,"Failed to Register! Try Again!",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                            startActivity(new Intent(SignIn.this,Dash.class));
                        }else {
                            Toast.makeText(SignIn.this,"Failed to Register! Try Again!",Toast.LENGTH_LONG).show();


                        }
                    }
                });


    }
}

