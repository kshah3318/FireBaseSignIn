package com.karan.firebasesignin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
      private FirebaseAuth firebaseAuth;
    private EditText etUserName,etEmail,etPassword;
    private Button sign_in,sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        etUserName=(EditText)findViewById(R.id.etUserName);
        etEmail=(EditText)findViewById(R.id.etEmailAddress);
        etPassword=(EditText)findViewById(R.id.etPassword);
        sign_in=(Button)findViewById(R.id.btn_signin);
        sign_up=(Button)findViewById(R.id.btn_signup);
        CheckedLoggedIn();
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private  void CheckedLoggedIn()
    {
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),SignIn.class));
        }
    }

}
