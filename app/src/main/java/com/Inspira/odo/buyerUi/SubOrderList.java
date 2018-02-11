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
import com.Inspira.odo.adaptors.SubOrderAdaptors;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.model.spareParts.PartsDescription;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SubOrderList extends AppCompatActivity {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
    SubOrderAdaptors subOrderAdaptors ;
     DatabaseReference mDatabase;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    ArrayList<PartsDescription> list ;
    Bundle bundle;
    String carModel ,carType,carYear,buyerPhoneNumber ,engineCapacity ,index,key;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_order_list);
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(SubOrderList.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubOrderList.this,NavigationDrawerBuyer.class);
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
            engineCapacity= bundle.getString("engineCapacity");
            buyerPhoneNumber= bundle.getString("buyerPhoneNumber");
            index =bundle.getString("index");
            key= bundle.getString("key");
            list = (ArrayList<PartsDescription>) getIntent().getSerializableExtra("orders");
            if(!list.isEmpty()){
                subOrderAdaptors= new SubOrderAdaptors(list, this ,carType,carModel,carYear,engineCapacity ,index,buyerPhoneNumber,key);
                recycler_view.setAdapter(subOrderAdaptors);
                subOrderAdaptors.notifyDataSetChanged();

            }else {
                Toast.makeText(this,  "  else", Toast.LENGTH_SHORT).show();

            }
        }


//         Toast.makeText(this, list.get(0).getCarDataDetails().getCarType()+"  list", Toast.LENGTH_SHORT).show();


    }
}
