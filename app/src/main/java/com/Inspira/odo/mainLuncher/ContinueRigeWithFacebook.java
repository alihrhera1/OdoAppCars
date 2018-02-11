package com.Inspira.odo.mainLuncher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.CheckValidation;
import com.facebook.FacebookSdk;

public class ContinueRigeWithFacebook extends AppCompatActivity {
    EditText Mobile_Number ,passworred ,Confirm_Password;
    Button Continue ;
    Bundle bundle ;
    String user_name  ,email;
    SharedPreferencesManager sharedPreferencesManager ;
    CheckValidation checkValidation ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        sharedPreferencesManager= new SharedPreferencesManager(ContinueRigeWithFacebook.this);
        checkValidation= new CheckValidation(this);
                bundle=getIntent().getExtras();
        if(bundle!=null){
            user_name = bundle.getString("user_name");
            email= bundle.getString("email");
        }
        setContentView(R.layout.activity_continue_rige_with_facebook);
        Mobile_Number=(EditText)findViewById(R.id.Mobile_Number);
        passworred=(EditText)findViewById(R.id.passworred);
        Continue=(Button)findViewById(R.id.Continue);
        Confirm_Password=(EditText)findViewById(R.id.Confirm_Password);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoginManager.getInstance().logOut();
//                Intent intent = new Intent(ContinueRigeWithFacebook.this,LogInActivity.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),user_name,Toast.LENGTH_SHORT).show();
                if(user_name!=null&&!Mobile_Number.getText().toString().trim().equals("")&&email!=null &&passworred.getText().toString().trim().equals("")){
                    boolean checkPassword =checkValidation.ComfierPassord(getApplicationContext(),passworred ,Confirm_Password);
                    if(checkPassword==true){

                    }else {

                        Toast.makeText(getApplicationContext(),getString(R.string.Password_Not_matching),Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
