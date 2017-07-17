package com.karan.firebasesignin;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
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
               CallSignIn(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSignUp(etEmail.getText().toString(),etPassword.getText().toString());
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
    private void CallSignUp(String email,String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(!task.isSuccessful())
               {
                   Toast.makeText(MainActivity.this,"Signup Failed",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   userProfile();
                   Toast.makeText(MainActivity.this,"Signup Successful",Toast.LENGTH_SHORT).show();
               }

            }
        });

    }
    private  void CallSignIn(String email,String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(!task.isSuccessful())
               {
                   Toast.makeText(MainActivity.this,"FAILED", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Intent i=new Intent(MainActivity.this,SignIn.class);
                   finish();
                   startActivity(i);
               }
            }
        });
    }
    private void userProfile() {
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            UserProfileChangeRequest profileUpdates=new UserProfileChangeRequest.Builder().setDisplayName(etUserName.getText().toString().trim()).build();
            user.updateProfile(profileUpdates).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Log.d("TESTING","User Profile Updated");
                    }
                }
            });
        }
    }
}
