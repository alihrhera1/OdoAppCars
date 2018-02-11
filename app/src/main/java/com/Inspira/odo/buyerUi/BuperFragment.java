package com.Inspira.odo.buyerUi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.CheckValidation;
import com.Inspira.odo.mainLuncher.PhoneAuthActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import static com.facebook.FacebookSdk.getApplicationContext;


public class BuperFragment extends Fragment {


    Button creatSelerAcout ;
    EditText fName, phoneNo, password, email ,Confirm_Password;
    SharedPreferencesManager sharedPreferencesManager ;
    CheckValidation checkValidation ;
    private FirebaseAuth auth;
    FirebaseHepler firebaseHepler ;
    CheckBox Trms ;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;

    private static final String TAG = "PhoneAuthActivity";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        firebaseHepler= new FirebaseHepler(getActivity());
         final View rooteView= inflater.inflate(R.layout.fragment_buper, container, false);
        sharedPreferencesManager= new SharedPreferencesManager(getApplicationContext());
        checkValidation= new CheckValidation(getContext());
        if (sharedPreferencesManager.isLoggedIn()) {
             Intent intent = new Intent(getActivity(), NavigationDrawerBuyer.class);
            startActivity(intent);
            getActivity().finish();
        }
        creatSelerAcout =(Button)rooteView.findViewById(R.id.creatSelerAcout);
        fName =(EditText) rooteView.findViewById(R.id.fname);
        phoneNo = (EditText)rooteView.findViewById(R.id.phone_no);
        password = (EditText)rooteView.findViewById(R.id.password);
        email = (EditText)rooteView.findViewById(R.id.email);
        Confirm_Password =(EditText)rooteView.findViewById(R.id.Confirm_Password);
        Trms=(CheckBox)rooteView.findViewById(R.id.Trms);
        creatSelerAcout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Trms.isChecked()){
                    if(!fName.getText().toString().trim().equals("")&&
                            !phoneNo.getText().toString().trim().equals("")&&
                            !password.getText().toString().trim().equals("")&&
                            !email.getText().toString().trim().equals("")&&
                            !Confirm_Password.getText().toString().equals("")){
                        String name =fName.getText().toString();
                        String  phone = phoneNo.getText().toString();
                        String  passwrd= password.getText().toString();
                        String emaile =email.getText().toString();
                        if (TextUtils.isEmpty(emaile)) {
                            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(passwrd)) {
                            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (password.length() < 6) {
                            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(fName.getText().toString().trim()!=null&&!name.isEmpty()&&!phone.isEmpty()&&!passwrd.isEmpty() &&!emaile.isEmpty()){
                            boolean checkedEmail = checkValidation.Emailvalidate(email);
                            boolean checkPassword =checkValidation.ComfierPassord(getApplicationContext(),password ,Confirm_Password);
                        if(checkedEmail==true){
                            if(checkPassword==true){
                                if (!validatePhoneNumber()) {
                                    return;
                                }else {


                                    Intent intent = new Intent(getActivity(),PhoneAuthActivity.class);
                                    intent.putExtra("phoneNumber",phoneNo.getText().toString().trim());
                                    intent.putExtra("fullName",fName.getText().toString().trim());
                                    intent.putExtra("email",email.getText().toString().trim());
                                    intent.putExtra("password",Confirm_Password.getText().toString().trim());
                                    intent.putExtra("UserType","buyer");
//                                    intent.putExtra("phoneNo",phoneNo.getText().toString().trim());
                                    getActivity().startActivity(intent);
                                }





                            }else {
                                Toast.makeText(getApplicationContext(),getString(R.string.Password_Not_matching),Toast.LENGTH_SHORT).show();

                            }

                        }else {
                            Toast.makeText(getApplicationContext(),getString(R.string.Invalid_Not_Email),Toast.LENGTH_SHORT).show();

                        }


                        }else {
                            Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                        }
                    }else {

                        Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(getContext(),"Check trim box",Toast.LENGTH_SHORT).show();

                }



            }
        });
        return rooteView;
    }


    private boolean validatePhoneNumber() {
        String phoneNumber = phoneNo.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNo.setError("Invalid phone number.");
            return false;
        }
        return true;
    }


    private String getDatafromEditText(EditText editText){
        String text="";
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError(getString(R.string.enter_data));
        }else {
            text=editText.getText().toString();
            if(text.contains("")){


                editText.setError(getString(R.string.enter_data));
            }else{
                return text;}

        }
        return text;

    }

}
