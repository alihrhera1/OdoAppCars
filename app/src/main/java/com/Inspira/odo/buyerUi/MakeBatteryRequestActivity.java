package com.Inspira.odo.buyerUi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.LocaleHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MakeBatteryRequestActivity extends AppCompatActivity {
    Spinner KNOW_BATTERY;
    EditText Size ;
    ArrayList<String> catogires ;
    CustomArrayAdapter_Spinner customArrayAdapter_spinner ;
    SharedPreferencesManager sharedPreferencesManager ;
    String carType ,carYear ,carePar ,carModle ,PHONE_number;
   OrderList orderList ;
    List<OrderImage> orderImages ;
    EditText amper ;
    CheckBox Run_flot_tyres  ,Reversed;
    String  Run_flot_tyresT ,polesT   ,KNOW_BATTERYS;
    RadioButton larg_poles ,small_poles ,not_know ;
     RadioGroup radioG ;
    Button saveData ;
    LinearLayout linear ;
    boolean ReversedT ;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    FirebaseHepler firebaseHepler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_make_battery_request);
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        firebaseHepler=new FirebaseHepler(MakeBatteryRequestActivity.this);
        String lange=  localeHelper.getLanguage(MakeBatteryRequestActivity.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakeBatteryRequestActivity.this,   NavigationDrawerBuyer.class);
                startActivity(intent);
                finish();

            }
        });


        sharedPreferencesManager= new SharedPreferencesManager(MakeBatteryRequestActivity.this);
        setTitle(R.string.MakeBatteryRequest);
        carModle =sharedPreferencesManager.getCar_Modle();
        carYear =sharedPreferencesManager.getCar_Year() ;
        carType =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        carePar="Battery";
        KNOW_BATTERY = (Spinner) findViewById(R.id.KNOW_BATTERY);
        Size =(EditText) findViewById(R.id.Size);
        amper=(EditText) findViewById(R.id.amper);
        catogires= new ArrayList<>();
        catogires.add(getString(R.string.donot_know));
        catogires.add(getString(R.string.know));
        larg_poles=(RadioButton) findViewById(R.id.larg_poles);
        small_poles=(RadioButton) findViewById(R.id.small_poles);
        not_know=(RadioButton) findViewById(R.id.not_know);
        Run_flot_tyres=(CheckBox) findViewById(R.id.Run_flot_tyres);
        Reversed=(CheckBox) findViewById(R.id.Reversed);
         radioG=(RadioGroup) findViewById(R.id.radioG);
        saveData=(Button) findViewById(R.id.saveData);
        linear=(LinearLayout) findViewById(R.id.linear);

        customArrayAdapter_spinner = new CustomArrayAdapter_Spinner(MakeBatteryRequestActivity.this,
                R.layout.customspinneritem, catogires);
        KNOW_BATTERY.setAdapter(customArrayAdapter_spinner);
         orderImages = new ArrayList<>();
        if( Reversed.isChecked()){
            ReversedT=true;
        }

        if (Run_flot_tyres.isChecked()){
            Run_flot_tyresT =getString(R.string.Run_flot_tyres);
        }


        radioG.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton  radioSexButton = (RadioButton) findViewById(selectedId);
                polesT=  radioSexButton.getText().toString();

            }
        });



        // Spinner click listener
        KNOW_BATTERY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                KNOW_BATTERYS= parent.getItemAtPosition(position).toString();
                if(KNOW_BATTERYS.equals(getString(R.string.donot_know))){
                      amper.setVisibility(View.GONE);
                    Size.setVisibility(View.GONE);
                    Reversed.setVisibility(View.GONE);
                    radioG.setVisibility(View.GONE);
                    Run_flot_tyres.setVisibility(View.GONE);
                    linear.setVisibility(View.GONE);
                }else{
                    Size.setVisibility(View.VISIBLE);

                    amper.setVisibility(View.VISIBLE);
                    Reversed.setVisibility(View.VISIBLE);
                    radioG.setVisibility(View.VISIBLE);
                    Run_flot_tyres.setVisibility(View.VISIBLE);
                    linear.setVisibility(View.VISIBLE);
                }
                Toast.makeText(parent.getContext(),KNOW_BATTERYS, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(KNOW_BATTERYS.equals(getString(R.string.donot_know))){

                        Calendar c = Calendar.getInstance();
                        System.out.println("Current time => " + c.getTime());
                        orderList=new OrderList(carePar,Run_flot_tyresT,"-","-","-","-");
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                        String formattedDate = df.format(c.getTime());
                        firebaseHepler.addArequestBatteries(PHONE_number,carType,carModle ,carYear,formattedDate ,
                                "EngCapsty","COLOR","AMPER",getString(R.string.donot_know),getString(R.string.donot_know),false);
                    Toast.makeText(getApplicationContext(),getString(R.string.send_successfully),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MakeBatteryRequestActivity.this,NavigationDrawerBuyer.class);
                    startActivity(intent);
                    finish();
                }else {

                    if(!Size.getText().toString().trim().equals("") &&!amper.getText().toString().trim().equals("")){
                        if(PHONE_number!=null){
                            Toast.makeText(getApplicationContext(),getString(R.string.send_successfully),Toast.LENGTH_SHORT).show();
                            orderList=new OrderList(carePar,Run_flot_tyresT,"-","-",amper.getText().toString().trim(),Size.getText().toString().trim());

                            Calendar c = Calendar.getInstance();
                            System.out.println("Current time => " + c.getTime());

                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                            String formattedDate = df.format(c.getTime());
                            if(Run_flot_tyresT==null){
                                Run_flot_tyresT="";
                            }

                        firebaseHepler.addArequestBatteries(PHONE_number,carType,carModle ,carYear,formattedDate ,
                                "EngCapsty","color",amper.getText().toString(),Size.getText().toString(),polesT,ReversedT);
                            Toast.makeText(getApplicationContext(),getString(R.string.send_successfully),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MakeBatteryRequestActivity.this,NavigationDrawerBuyer.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(getApplicationContext(),getString(R.string.Phone_number_does_not_exist),Toast.LENGTH_SHORT).show();

                        }
                    }else{

                        Toast.makeText(getApplicationContext(),getString(R.string.add_Battery_details),Toast.LENGTH_SHORT).show();

                    }

                }



            }
        });

    }


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setTitle(R.string.MakeBatteryRequest);

    }
}
