package com.Inspira.odo.mainLuncher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SinInRegis extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    EditText number_phone  ,passwordlog;
    SharedPreferencesManager sharedPreferencesManager ;
    Button login ;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_in_regis);
        login= (Button)findViewById(R.id.login);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        sharedPreferencesManager = new SharedPreferencesManager(this);
        number_phone=(EditText)findViewById(R.id.number_phone);
        passwordlog=(EditText)findViewById(R.id.passwordlog);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = number_phone.getText().toString().trim() ;
                String passer =passwordlog.getText().toString().trim() ;
                 final String Phone="+2"+ number;
                Toast.makeText(SinInRegis.this,Phone, Toast.LENGTH_SHORT).show();

                if(!number_phone.getText().toString().trim().equals("")&&number!=null&&
                !passwordlog.getText().toString().trim().equals("")&&passer!=null){
                    mDatabase.child("Users").child(Phone).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Toast.makeText(SinInRegis.this,dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();

                            if(dataSnapshot.exists()){
                                Toast.makeText(SinInRegis.this,  dataSnapshot.child("userType").getValue().toString()+"  userType", Toast.LENGTH_SHORT).show();
                                sharedPreferencesManager.setLogin(true);
                                String fullName = dataSnapshot.child("fullname").getValue().toString();
                                String email =dataSnapshot.child("email").getValue().toString();
                                String userType= dataSnapshot.child("userType").getValue().toString();
                                String password= dataSnapshot.child("password").getValue().toString();
                                if(dataSnapshot.child("companyType").exists()){
                                    sharedPreferencesManager.setPartType((dataSnapshot.child("companyType").getValue().toString()));
                                }
                                sharedPreferencesManager.setUser_Email(email);
                                sharedPreferencesManager.setUser_Name(fullName);
                                sharedPreferencesManager.setUserType(userType);
                                sharedPreferencesManager.setUser_Phoe(Phone);
                                if(password.equals(passwordlog.getText().toString())){

                                    if(userType.equals("buyer")){

                                        Intent intent = new Intent(SinInRegis.this, NavigationDrawerBuyer.class);
                                        startActivity(intent);
                                        finish();

                                    }else if (userType.equals("seller")){
                                        Intent intent = new Intent(SinInRegis.this, NavigationDrawerSeler.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }else {
                                    Toast.makeText(SinInRegis.this,"Registration First", Toast.LENGTH_SHORT).show();

                                }

                            }else {
                                Toast.makeText(SinInRegis.this,"Registration First", Toast.LENGTH_SHORT).show();

                            }




                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    private  void CheckUser(){


    }


    @Override
    public void onBackPressed() {
         Intent intent = new Intent(SinInRegis.this,   LogInActivity.class);
         startActivity(intent);
        finish();
    }
}
