package com.Inspira.odo.mainLuncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.data.Model.CompanyOnMap;
import com.Inspira.odo.database.CarsDataBase;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class PhoneAuthActivity extends AppCompatActivity implements
        View.OnClickListener {

    EditText  mVerificationField;
    Button   mVerifyButton;
    FirebaseHepler firebaseHepler ;
    private FirebaseAuth mAuth;
     private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId,Phone ,fullName,email ,password;
    String  fName, passwordd, emaill ,latitude,longitude, companyName,company_address ,your_Type_requse ,area;

     SharedPreferencesManager sharedPreferencesManager ;
       String UserType ;
    CompanyOnMap companyOnMap ;
    Map<String, Map<String,Map<String,String >>  >  workingOns = new HashMap<>();
    private static final String TAG = "PhoneAuthActivity";
    Bundle bundle ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_phone);
        sharedPreferencesManager= new SharedPreferencesManager(PhoneAuthActivity.this);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            Phone="+2"+bundle.getString("phoneNumber");
            fullName=bundle.getString("fullName");
            email=bundle.getString("email");
            password= bundle.getString("password");
            UserType=bundle.getString("UserType");

/// seller data
            latitude=bundle.getString("latitude");
            longitude=bundle.getString("longitude");
            fName=bundle.getString("fName");
             passwordd=bundle.getString("password");
            emaill=bundle.getString("email");
            companyName= bundle.getString("companyName");
            company_address= bundle.getString("company_address");
            your_Type_requse=bundle.getString("your_Type_requse");
            area= bundle.getString("area");
            companyOnMap = new CompanyOnMap(longitude,latitude);

        }
        workingOns= CarsDataBase.commenData(CarsDataBase.readAllReadLater(PhoneAuthActivity.this,"cars"));

        firebaseHepler= new FirebaseHepler(PhoneAuthActivity.this);
         mVerificationField = (EditText) findViewById(R.id.field_verification_code);
         mVerifyButton = (Button) findViewById(R.id.button_verify_phone);
          mVerifyButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                Toast.makeText(PhoneAuthActivity.this, "PhoneAuthCredential", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.w(TAG, "onVerificationFailed", e);
                    Toast.makeText(PhoneAuthActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                 } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
//                mResendToken = token;
            }
        };

        Toast.makeText(PhoneAuthActivity.this,Phone,Toast.LENGTH_LONG).show();
        Toast.makeText(PhoneAuthActivity.this,email,Toast.LENGTH_LONG).show();
        Toast.makeText(PhoneAuthActivity.this,password,Toast.LENGTH_LONG).show();
        Toast.makeText(PhoneAuthActivity.this,fullName +"  fullName",Toast.LENGTH_LONG).show();

            startPhoneNumberVerification(Phone);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PhoneAuthActivity.this,"signInWithCredential:success",Toast.LENGTH_LONG).show();
                            FirebaseUser user = task.getResult().getUser();

                            if(UserType.equals("buyer")){
                                sharedPreferencesManager.setLogin(true);
                                sharedPreferencesManager.setUser_Name(fullName);
                                sharedPreferencesManager.setUser_Phoe(Phone);
                                sharedPreferencesManager.setUserType( UserType);
                                sharedPreferencesManager.setCheckFacebookLogin(true);
                                firebaseHepler.addNewByerUser(Phone,fullName,email,password,user.getUid());
                                startActivity(new Intent(PhoneAuthActivity.this, NavigationDrawerBuyer.class));
                                finish();
                            }else if(UserType.equals("seller")){
                                sharedPreferencesManager.setLogin(true);
                                sharedPreferencesManager.setUser_Name(fName);
                                sharedPreferencesManager.setUser_Phoe(Phone);
                                sharedPreferencesManager.setUserType( UserType);
                                sharedPreferencesManager.setPartType(your_Type_requse);
                                sharedPreferencesManager.setCheckFacebookLogin(true);
                                firebaseHepler.addNewSeller(Phone, fName,emaill,passwordd,companyName,company_address ,area,companyOnMap ,
                                        your_Type_requse,workingOns ,user.getUid());
                                startActivity(new Intent(PhoneAuthActivity.this, NavigationDrawerSeler.class));
                                finish();
                            }

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                mVerificationField.setError("Invalid code.");
                            }
                        }
                    }
                });
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }




    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            startActivity(new Intent(PhoneAuthActivity.this, NavigationDrawerBuyer.class));
//            finish();
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_verify_phone:
                String code = mVerificationField.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    mVerificationField.setError("Cannot be empty.");
                    return;
                }

                verifyPhoneNumberWithCode(mVerificationId, code);
                break;

        }

    }

}