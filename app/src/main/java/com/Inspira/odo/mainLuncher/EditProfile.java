package com.Inspira.odo.mainLuncher;


import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.sellerUi.ContinuingRegSeler;


public class EditProfile extends Fragment {

    View  rooteiew ;
    SharedPreferencesManager sharedPreferencesManager ;
    Button creatSelerAcout ;
    EditText fName, phoneNo, password, email;
    String NameUser ;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        String user =   sharedPreferencesManager.getUserType();
        if(user.equals("buyer")){

            rooteiew = inflater.inflate(R.layout.fragment_buper, container, false);
            NameUser ="buyer";

        }else if (user.equals("seller")){

            rooteiew = inflater.inflate(R.layout.fragment_seller, container, false);
            NameUser="seller" ;


        }
        getActivity().setTitle(R.string.Edit_Profile);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);

         creatSelerAcout =(Button)rooteiew.findViewById(R.id.creatSelerAcout);
         fName =(EditText) rooteiew.findViewById(R.id.fname);
         phoneNo = (EditText)rooteiew.findViewById(R.id.phone_no);
         password = (EditText)rooteiew.findViewById(R.id.password);
         email = (EditText)rooteiew.findViewById(R.id.email);
         creatSelerAcout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(NameUser.equals("buyer")){
                     if(!fName.getText().toString().trim().equals("")&&
                             !phoneNo.getText().toString().trim().equals("")&&
                             !password.getText().toString().trim().equals("")&&
                             !email.getText().toString().trim().equals("")){
                         String name =getDatafromEditText(fName);
                         String  phone = getDatafromEditText(phoneNo);
                         String  passwrd= getDatafromEditText(password);
                         String emaile = getDatafromEditText(email);
                         if(fName.getText().toString().trim()!=null&&!name.isEmpty()&&!phone.isEmpty()&&!passwrd.isEmpty() &&!emaile.isEmpty()){

                         }else {
                             Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                         }
                     }else {

                         Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                     }



                 }else if (NameUser.equals("seller")){

                      if(!fName.getText().toString().trim().equals("")&&
                             !phoneNo.getText().toString().trim().equals("")&&
                             !password.getText().toString().trim().equals("")&&
                             !email.getText().toString().trim().equals("")){
                         String name =getDatafromEditText(fName);
                         String  phone = getDatafromEditText(phoneNo);
                         String  passwrd= getDatafromEditText(password);
                         String emaile = getDatafromEditText(email);
                         if(fName.getText().toString().trim()!=null&&!name.isEmpty()&&!phone.isEmpty()&&!passwrd.isEmpty() &&!emaile.isEmpty()){
                             Intent intent = new Intent(getActivity(), ContinuingRegSeler.class);
//                        intent
                             startActivity(intent);

                         }else {
                             Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                         }
                     }else {

                         Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                     }


                 }

             }
         });



        return  rooteiew;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().setTitle(R.string.Edit_Profile);

    }



    private String getDatafromEditText(EditText editText){
        String text="";
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError(getString(R.string.enter_data));
        }else {
            text=editText.getText().toString();
            if(text.contains("")){

                editText.setError("This can't contain a space ");
            }else{
                return text;}

        }
        return text;

    }

}
