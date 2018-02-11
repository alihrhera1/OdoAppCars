package com.Inspira.odo.buyerUi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.AdaptorAccesoriesSubOrders;
 import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.model.CarDetails;
import com.Inspira.odo.model.accessories.accessoriesDescription;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AccesoriesSubOrders extends AppCompatActivity {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
    AdaptorAccesoriesSubOrders subOrderAdaptors ;
    DatabaseReference mDatabase;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    ArrayList<accessoriesDescription> list ;
    Bundle bundle;
     String carModel ,carType,carYear ,buyerPhoneNumber,key;
    CarDetails  carDetails ;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesories_sub_orders);
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  LocaleHelper.getLanguage(AccesoriesSubOrders.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccesoriesSubOrders.this,NavigationDrawerBuyer.class);
                startActivity(intent);
                finish();

            }
        });
        mDatabase= FirebaseDatabase.getInstance().getReference();
        sharedPreferencesManager= new SharedPreferencesManager(this);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
        recycler_view.setLayoutManager(layoutManager);
        bundle = getIntent().getExtras();


        if(bundle!=null){
            carModel=bundle.getString("carModel");
            carType= bundle.getString("carType");
            carYear =bundle.getString("carYear");
            buyerPhoneNumber= bundle.getString("buyerPhoneNumber");
            key= bundle.getString("key");
            list = (ArrayList<accessoriesDescription>) getIntent().getSerializableExtra("orders");
            carDetails = bundle.getParcelable("MyClass");
            if(!list.isEmpty()){
                subOrderAdaptors= new AdaptorAccesoriesSubOrders(list, this  ,carType,carModel,carYear ,buyerPhoneNumber,key);
                recycler_view.setAdapter(subOrderAdaptors);
                subOrderAdaptors.notifyDataSetChanged();

            }else {
                Toast.makeText(this,  "  else", Toast.LENGTH_SHORT).show();

            }
        }


    }
}
