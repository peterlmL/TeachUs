package com.ppcarrasco.teachus.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.crashlytics.android.Crashlytics;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ppcarrasco.teachus.R;
import com.ppcarrasco.teachus.data.Nodes;
import com.ppcarrasco.teachus.views.main.MainActivity;

import java.util.Arrays;

import io.fabric.sdk.android.Fabric;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            logged();
        } else {
            singIn();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode){
            if (resultCode == ResultCodes.OK){
                logged();
            } else{
                singIn();
            }
        }
    }

    private void singIn(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.drawable.teachuslogo)
                        .build(),
                RC_SIGN_IN);
    }
    private void logged(){

        DatabaseReference users = new Nodes().getUsers();
        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists())
                {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                }
                else
                {
                    startActivity(new Intent(LoginActivity.this, FormActivity.class));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
