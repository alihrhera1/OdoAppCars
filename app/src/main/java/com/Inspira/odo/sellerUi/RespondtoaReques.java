package com.Inspira.odo.sellerUi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.model.TyresABatteries.Responds;
import com.Inspira.odo.model.accessories.RespondsAC;
import com.Inspira.odo.model.spareParts.RespondsSpar;
 import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RespondtoaReques extends AppCompatActivity {
    TextView name_of_part  ,Date_of_day;
    EditText price  ,Describetion;
    ImageView image  ,image_order;
    Bundle bundle ;
    String buyerPhoneNumber ,orderID  ,part ,PhoneNumber ,TypeRequest ,index ,imagee,orderPartType;
    SharedPreferencesManager sharedPreferencesManager ;
    LocaleHelper localeHelper ;
    Calendar cal ;
    Button Respon ;
    AdView adView ;
    FirebaseHepler firebaseHepler ;
    private ArrayList<String> photosURL ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_respondtoa_reques);
        // Load an ad into the AdMob banner view.
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        setTitle(R.string.Respond_to_aReques);
        image=(ImageView)findViewById(R.id.image);
//        Calendar cal = Calendar.getInstance();
        cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        System.out.println(dateFormat.format(cal.getTime()));
        localeHelper= new LocaleHelper();
        String lange=  LocaleHelper.getLanguage(RespondtoaReques.this);
        sharedPreferencesManager=new SharedPreferencesManager(RespondtoaReques.this);
          firebaseHepler = new FirebaseHepler(getApplicationContext());

        PhoneNumber= sharedPreferencesManager.getUser_Phoe();
        if(lange.equals("ar")){
            image.setImageResource(R.drawable.back_wiht);
        }else if(lange.equals("en")){
            image.setImageResource(R.drawable.back_eft_whit);
        }
        name_of_part= (TextView)findViewById(R.id.name_of_part);
        Date_of_day=(TextView)findViewById(R.id.Date_of_day);
        image_order=(ImageView)findViewById(R.id.image_order);

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        String test = sdf.format(cal.getTime());

        Date_of_day.setText(test);
        price=(EditText)findViewById(R.id.price);
        Describetion=(EditText)findViewById(R.id.Describetion);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            buyerPhoneNumber = bundle.getString("buyerPhoneNumber");
            orderID=bundle.getString("key");
            part = bundle.getString("part");
            TypeRequest= bundle.getString("TypeRequest");
            int  yy = bundle.getInt("index");
            index=String.valueOf(yy);
            imagee=bundle.getString("image");
            photosURL =getIntent().getStringArrayListExtra("imagees");
            orderPartType =bundle.getString("orderPartType");
        }
     if(imagee!=null&&!imagee.equals("")){
         Picasso.with(this)
                 .load(imagee)
                 .into(image_order);
     }

        name_of_part.setText(part);
        Respon=(Button)findViewById(R.id.Respon);
        Respon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if(!price.getText().toString().trim().equals("")&&!Describetion.getText().toString().trim().equals("")){
                      switch (TypeRequest) {
                          case "spareParts":

                              RespondsSpar respondsSpar = new RespondsSpar(Describetion.getText().toString(), price.getText().toString(), photosURL);
                              firebaseHepler.ResponSparParts(buyerPhoneNumber, TypeRequest, orderID, PhoneNumber, index, respondsSpar);
                              Toast.makeText(RespondtoaReques.this, "spareParts", Toast.LENGTH_SHORT).show();

                              break;
                          case "accessories":
                              Toast.makeText(RespondtoaReques.this, "accessories", Toast.LENGTH_SHORT).show();
                              RespondsAC respondsAC = new RespondsAC(Describetion.getText().toString(), price.getText().toString(), photosURL);
                              firebaseHepler.sendAceeerois(buyerPhoneNumber,TypeRequest,orderID,PhoneNumber,index,respondsAC);

                              break;
                          case "TyresABatteries":
                              if (orderPartType.equals("batteries")) {
                                  Responds responds = new Responds(Describetion.getText().toString(), price.getText().toString());
                                  firebaseHepler.ResponToOrderTyerBattres(buyerPhoneNumber, TypeRequest, orderID, PhoneNumber, responds);
                                  Toast.makeText(RespondtoaReques.this, "battery", Toast.LENGTH_SHORT).show();

                              } else if (orderPartType.equals("tyres")) {
                                  Responds responds = new Responds(Describetion.getText().toString(), price.getText().toString(), "");
                                  firebaseHepler.ResponToOrderTyerBattres(buyerPhoneNumber, TypeRequest, orderID, PhoneNumber, responds);
                                  Toast.makeText(RespondtoaReques.this, "tyres", Toast.LENGTH_SHORT).show();

                              }



                              break;
                      }

                    finish();

                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
       setTitle(R.string.Respond_to_aReques);
    }
}
