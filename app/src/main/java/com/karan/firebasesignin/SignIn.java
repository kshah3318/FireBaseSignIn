package com.karan.firebasesignin;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;
public class SignIn extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    Button signout;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        firebaseAuth=FirebaseAuth.getInstance();
        signout=(Button)findViewById(R.id.btn_signout);
        username=(TextView)findViewById(R.id.disp_username);
        //Again Check if the user is already logged in or not
        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        //Fetch the display name of the current user
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            username.setText("Welcome"+""+user.getDisplayName());
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
