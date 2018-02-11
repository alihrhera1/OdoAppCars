package com.Inspira.odo.buyerUi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.CheckValidation;
import com.Inspira.odo.mainLuncher.PhoneAuthActivity;
import com.facebook.FacebookSdk;

import static com.facebook.FacebookSdk.getApplicationContext;

public class BuyerWithFacebook extends Fragment {
    EditText Mobile_Number ,passworred ,Confirm_Password;
    Button Continue ;
    Bundle bundle ;
    String user_name  ,email;
    SharedPreferencesManager sharedPreferencesManager ;
    CheckValidation checkValidation ;
    View roote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         roote = inflater.inflate(R.layout.fragment_buyer_with_facebook, container, false);
        user_name = getArguments().getString("user_name");
        email=  getArguments().getString("email");
        FacebookSdk.sdkInitialize(getApplicationContext());
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        checkValidation= new CheckValidation(getApplicationContext());
        Mobile_Number=(EditText)roote.findViewById(R.id.Mobile_Number);
        passworred=(EditText)roote.findViewById(R.id.passworred);
        Continue=(Button)roote.findViewById(R.id.Continue);
        Confirm_Password=(EditText)roote.findViewById(R.id.Confirm_Password);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),user_name,Toast.LENGTH_SHORT).show();
                if(!Mobile_Number.getText().toString().trim().equals("") &&!passworred.getText().toString().trim().equals("")&&email!=null&&user_name!=null){
                    boolean checkPassword =checkValidation.ComfierPassord(getApplicationContext(),passworred ,Confirm_Password);
                    if(checkPassword==true){
                        String   phone = Mobile_Number.getText().toString().trim();
                        String pass =passworred.getText().toString().trim();
                        if(email!=null){

                            Intent intent = new Intent(getActivity(),PhoneAuthActivity.class);
                            intent.putExtra("phoneNumber",Mobile_Number.getText().toString().trim());
                            intent.putExtra("fullName",user_name);
                            intent.putExtra("email",email);
                            intent.putExtra("password",Confirm_Password.getText().toString().trim());
                            intent.putExtra("UserType","buyer");
//                                    intent.putExtra("phoneNo",phoneNo.getText().toString().trim());
                            getActivity().startActivity(intent);
                        }


                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Password_Not_matching),Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  roote ;
    }


}
