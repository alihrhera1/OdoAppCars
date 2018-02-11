package com.Inspira.odo.buyerUi;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.adaptors.MyRequestAdapter;
import com.Inspira.odo.adaptors.RespondsACAdaptore;
import com.Inspira.odo.adaptors.RespondsSparAdaptor;
import com.Inspira.odo.adaptors.RespondsTyresABatteries;
import com.Inspira.odo.adaptors.ResponseAdaptor;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.FilterData;
import com.Inspira.odo.model.TyresABatteries.Responds;
import com.Inspira.odo.model.accessories.RespondsAC;
import com.Inspira.odo.model.spareParts.RespondsSpar;
import com.Inspira.odo.model.spareParts.SpareParts;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class RequestResponses extends AppCompatActivity {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number;
    MyApplication myApplication ;
    private FilterData data = new FilterData();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
    ResponseAdaptor responseAdaptor ;
    ArrayList<Response> mSelectedList ;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    String orderId ,arraType ;
    ArrayList<String>AreaArray ;
    AdView adView ;
    DatabaseReference mDatabase;
    Bundle bundle;
    String carModel ,carType,carYear,buyerPhoneNumber ,key ,TypeRequest;
    RespondsSparAdaptor respondsSparAdaptor ;
    RespondsACAdaptore respondsACAdaptore ;
    RespondsTyresABatteries respondsTyresABatteries ;
    FloatingActionButton fab;
    Button Get_Direction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_responses);
         adView = (AdView)findViewById(R.id.adView);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(RequestResponses.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestResponses.this, NavigationDrawerBuyer.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        mSelectedList = intent.getParcelableArrayListExtra("Response");

         bundle = getIntent().getExtras();
        if(bundle!=null){
            carModel=bundle.getString("carModel");
            carType= bundle.getString("carType");
            carYear =bundle.getString("carYear");
             buyerPhoneNumber= bundle.getString("buyerPhoneNumber");
             key= bundle.getString("key");
            TypeRequest= bundle.getString("TypeRequest");


        }
        data.setmList(mSelectedList);
        myApplication= new MyApplication();
        initViews();


        sharedPreferencesManager= new SharedPreferencesManager(this);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
       findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final Dialog okdialog = new Dialog(RequestResponses.this, R.style.custom_dialog_theme);
              okdialog.setContentView(R.layout.dialog_response_filter);
              Button OK_d = okdialog.findViewById(R.id.Search);
              final EditText price_from =okdialog.findViewById(R.id.price_from);
              final EditText to_price =okdialog.findViewById(R.id.to_price);
              final Spinner addArea =okdialog.findViewById(R.id.addArea);
              final  EditText Describetion=okdialog.findViewById(R.id.Describetion);
              AreaArray= new ArrayList<>();
              AreaArray.add(getString(R.string.choseArea));
              AreaArray.add(getString(R.string.cairo));
              AreaArray.add(getString(R.string.ma3adi));
              AreaArray.add(getString(R.string.giza));
               CustomArrayAdapter_Spinner Adaptor = new CustomArrayAdapter_Spinner(RequestResponses.this,
                      R.layout.customspinneritem, AreaArray);
              addArea.setAdapter(Adaptor);


              // Spinner click listener
              addArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                      // On selecting a spinner item
                      // On selecting a spinner item
                      String  itemReques= parent.getItemAtPosition(position).toString();
                      if(itemReques.equals(getString(R.string.choseArea))){
                          arraType=null;
                          Toast.makeText(parent.getContext(),getString(R.string.choseArea) , Toast.LENGTH_LONG).show();
                      }else {
                          arraType=parent.getItemAtPosition(position).toString() ;
                          sharedPreferencesManager.setArea(arraType);
                          Toast.makeText(parent.getContext(), arraType, Toast.LENGTH_LONG).show();
                      }
                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> adapterView) {

                  }
              });
              OK_d.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      addToSelectedMap("price_max", to_price.getText().toString());
                      addToSelectedMap("price_min", price_from.getText().toString());
                      addToSelectedMap("area", arraType);
                      addToSelectedMap("describetion", Describetion.getText().toString());
                      if (mSelectedList != null) {
                          ArrayMap<String, List<String>> applied_filter = applied_filters;
                          if (applied_filter.size() != 0) {
                              List<Response> filteredList = data.getAllData();
                              //iterate over arraymap
                              for (Map.Entry<String, List<String>> entry : applied_filter.entrySet()) {
                                  Log.d("k9res", "entry.key: " + entry.getKey());
                                  switch (entry.getKey()) {
                                      case "price_max":
                                          filteredList = data.getPriceFilteredMyOrderMax(entry.getValue(), filteredList);
                                          break;
                                      case "price_min":
                                          filteredList = data.getPriceFilteredMyOrderMin(entry.getValue(), filteredList);
                                          break;
                                      case "area":
                                          filteredList = data.getAddressFilteredResponse(entry.getValue(),filteredList);
                                          break;
                                      case "describetion":
                                          filteredList = data.getPriceFilteredMyOrderDIS(entry.getValue(),filteredList);
                                          break;

                                  }

                                  responseAdaptor.clear();
                                  responseAdaptor.addAll(filteredList);
                              }
                          }
                      }
                      okdialog.dismiss();
                  }
              });
              okdialog.show();


//              addToSelectedMap("area", Area.getText().toString());

          }
      });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                phoneCall(SellerPhoneNumber);
//                 Intent callIntent = new Intent(Intent.ACTION_CALL);
//                 callIntent.setData(Uri.parse("tel:" + SellerPhoneNumber));
//                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                if (ActivityCompat.checkSelfPermission(detalisOfRequest.this, Manifest.permission.CALL_PHONE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                startActivity(callIntent);

            }
        });
        Get_Direction=(Button)findViewById(R.id.Get_Direction);
        Get_Direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri gmmIntentUri = Uri.parse("google.navigation:q="+Latitude+","+Longitude);
//                Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                startActivity(intent);

            }
        });

    }

    private void addToSelectedMap(String key, String value) {
        if (applied_filters.get(key) != null && !applied_filters.get(key).contains(value)) {
            applied_filters.get(key).add(value);
        } else {
            List<String> temp = new ArrayList<>();
            temp.add(value);
            applied_filters.put(key, temp);
        }
    }




    private void initViews(){
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
        recycler_view.setLayoutManager(layoutManager);
         getData(buyerPhoneNumber,TypeRequest,key ,recycler_view);


    }
     public  void getData(String buyerPhoneNumber,String TypeRequest, String id_Post,final RecyclerView  recycler_view){
         mDatabase= FirebaseDatabase.getInstance().getReference();
         switch (TypeRequest) {
             case "spareParts":
                 mDatabase.child("Users").child(buyerPhoneNumber).child("Requests").child(TypeRequest).child(id_Post).
                         child("Responds").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         ArrayList<RespondsSpar> MyOrderList = new ArrayList<>();
                         for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                             Toast.makeText(getApplicationContext(), postSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                             RespondsSpar value = postSnapshot.getValue(RespondsSpar.class);
                             value.setKey_Respon(postSnapshot.getKey());
                             MyOrderList.add(value);
                             respondsSparAdaptor = new RespondsSparAdaptor(RequestResponses.this, MyOrderList, postSnapshot.getKey());
                         }
                         recycler_view.setAdapter(respondsSparAdaptor);
                         respondsSparAdaptor.notifyDataSetChanged();


//


                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });

                 break;
             case "accessories":
                 mDatabase.child("Users").child(buyerPhoneNumber).child("Requests").child(TypeRequest).child(id_Post).
                         child("Responds").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         ArrayList<RespondsAC> MyOrderList = new ArrayList<>();
                         for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                             Toast.makeText(getApplicationContext(), postSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                             RespondsAC value = postSnapshot.getValue(RespondsAC.class);
                             value.setKey_Respon(postSnapshot.getKey());
                             MyOrderList.add(value);
                             respondsACAdaptore = new RespondsACAdaptore(RequestResponses.this, MyOrderList, postSnapshot.getKey());

                         }
                         recycler_view.setAdapter(respondsACAdaptore);
                         respondsSparAdaptor.notifyDataSetChanged();


//


                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });

                 break;
             case "TyresABatteries":

                 mDatabase.child("Users").child(buyerPhoneNumber).child("Requests").child(TypeRequest).child(id_Post).
                         child("Responds").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         ArrayList<Responds> MyOrderList = new ArrayList<>();
                         for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                             Toast.makeText(getApplicationContext(), postSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                             Responds value = postSnapshot.getValue(Responds.class);
                             value.setKey_Respon(postSnapshot.getKey());
                             MyOrderList.add(value);
                             respondsTyresABatteries = new RespondsTyresABatteries(RequestResponses.this, MyOrderList, postSnapshot.getKey());

                         }
                         recycler_view.setAdapter(respondsTyresABatteries);
                         respondsSparAdaptor.notifyDataSetChanged();


//


                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });

                 break;
         }

     }


    private void phoneCall(String tel ){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + tel));
            startActivity(callIntent);
        }else{
            Toast.makeText(this, getString(R.string.assign_permission),Toast.LENGTH_SHORT).show();

        }
    }


}
