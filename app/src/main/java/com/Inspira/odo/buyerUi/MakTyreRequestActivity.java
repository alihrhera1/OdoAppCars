package com.Inspira.odo.buyerUi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.LocaleHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MakTyreRequestActivity extends AppCompatActivity {
    EditText Size ;
    CheckBox Run_flot_tyres ;
    RadioButton one_Tyre  ,two_tyre ,four_tyre ;
    Button saveData;
    SharedPreferencesManager sharedPreferencesManager ;
    String carType ,carYear ,carePar ,carModle ,PHONE_number  ,TyrT;
    boolean Run_flot_tyrest = false ;
    OrderList orderList ;
    List<OrderImage> orderImages ;
    FirebaseHepler firebaseHepler ;
    RadioGroup  radioSexGroup ;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    String enginCapasty ,color ;
     Bundle bundle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mak_tyre_request);
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
//        setTitle(R.string.MakTyreRequest);
        String lange=  localeHelper.getLanguage(MakTyreRequestActivity.this);
         if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakTyreRequestActivity.this,NavigationDrawerBuyer.class);
                startActivity(intent);
            }
        });


        firebaseHepler= new FirebaseHepler(MakTyreRequestActivity.this);
        orderImages= new ArrayList<>();

        sharedPreferencesManager= new SharedPreferencesManager(MakTyreRequestActivity.this );
        carModle =sharedPreferencesManager.getCar_Modle();
        carYear =sharedPreferencesManager.getCar_Year() ;
        carType =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        bundle = getIntent().getExtras();
        if(bundle !=null){
             enginCapasty= bundle.getString("engineCanasta");
            color= bundle.getString("color");



        }
        carePar="Tyres";
        Size= (EditText)findViewById(R.id.Size);
        Run_flot_tyres=(CheckBox)findViewById(R.id.Run_flot_tyres);
        one_Tyre=(RadioButton)findViewById(R.id.one_Tyre);
        two_tyre=(RadioButton) findViewById(R.id.two_tyre);

        radioSexGroup = (RadioGroup) findViewById(R.id.radioG);
        four_tyre=(RadioButton)findViewById(R.id.four_tyre);
        saveData=(Button)findViewById(R.id.saveData);
        if( Run_flot_tyres.isChecked()){
            Run_flot_tyrest=true ;
        }
        one_Tyre.setTextColor(Color.WHITE);
        two_tyre.setTextColor(Color.WHITE);
        four_tyre.setTextColor(Color.WHITE);
        one_Tyre.setText(R.string.one_Tyre);
        two_tyre.setText(R.string.two_tyre);
        four_tyre.setText(R.string.four_tyre);




        if(one_Tyre.isChecked()){
            TyrT=getString(R.string.one_Tyre);
            two_tyre.setChecked(false);
            four_tyre.setChecked(false);
        }else if(two_tyre.isChecked()){
            TyrT=getString(R.string.two_tyre);
            one_Tyre.setChecked(false);
            four_tyre.setChecked(false);
        }else if(four_tyre.isChecked()){
            TyrT=getString(R.string.four_tyre);
            one_Tyre.setChecked(false);
            two_tyre.setChecked(false);
        }else {
            Toast.makeText(getApplicationContext(),getString(R.string.Select_the_frame_type ),Toast.LENGTH_LONG).show();

        }
        radioSexGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton  radioSexButton = (RadioButton) findViewById(selectedId);
                TyrT=  radioSexButton.getText().toString();

            }
        });


        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (!Size.getText().toString().trim().equals("")&&TyrT!=null) {
                    orderList=new OrderList(carePar, TyrT, "-", "-", "-", Size.getText().toString().trim());
                    if (PHONE_number != null) {
//                        orderImages.add(new OrderImage("image"));
//                        sendOrder(PHONE_number, carType, carModle, carYear, orderList, orderImages);

                        Calendar c = Calendar.getInstance();
                        System.out.println("Current time => " + c.getTime());

                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                        String formattedDate = df.format(c.getTime());
//                        firebaseHepler.addArequestTyres(PHONE_number, carType, carModle, carYear, formattedDate,"false" ,formattedDate, Size.getText().toString().trim()  ,TyrT);
                        firebaseHepler.addArequestTyres(PHONE_number,carType,carModle,carYear,formattedDate,enginCapasty,color
                                ,Size.getText().toString() ,TyrT,Run_flot_tyrest);
                        Toast.makeText(getApplicationContext(),getString(R.string.send_successfully),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MakTyreRequestActivity.this,NavigationDrawerBuyer.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Phone_number_does_not_exist),Toast.LENGTH_SHORT).show();

                    }
                }
//                else {
//
//
//                }



            }
        });
    }

         @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
      setTitle(R.string.MakTyreRequest);

    }
}
