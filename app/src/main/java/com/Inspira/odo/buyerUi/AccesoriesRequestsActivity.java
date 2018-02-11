package com.Inspira.odo.buyerUi;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.AdaptorOfImages;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.helper.UploadImageHelper;
import com.Inspira.odo.model.accessories.accessoriesDescription;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AccesoriesRequestsActivity extends AppCompatActivity {
   private EditText PartId ;
   private TextView add_anther_part_detalis ;
   private Button submet_requst ;
   private OrderList orderList ;
   private List<OrderImage> orderImages ;
   private ImageView addImage ;
   private SharedPreferencesManager sharedPreferencesManager ;
   private String carType ,carYear ,carePar ,carModle ,PHONE_number;
   private static final int INTENT_REQUEST_CODE = 100;
   private UploadImageHelper uploadImageHelper ;
   private String imagepath=null;
   private String imageName = null ;
   private FirebaseHepler firebaseHepler ;
   private String  car_type ,car_modle ,car_year  ;
   private LocaleHelper localeHelper ;
   private ImageView go_back  ,hintBack;
   private boolean check = false ;
   private ArrayList<accessoriesDescription> makOrderAccesoriesArrayList ;
   private ProgressBar brogs ;
   private ArrayList<Bitmap> bitmaps;
   private ArrayList<String>URLS;
   private RecyclerView recycler_view;
   private AdaptorOfImages adaptorOfImages ;
   private String enginCapasty ,color ;

   private Bundle  bundle ;
   private String formattedDate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accesories_requests);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
          formattedDate = df.format(c.getTime());
        setTitle(R.string.AccesoriesRequests);
        firebaseHepler= new FirebaseHepler(AccesoriesRequestsActivity.this);


        sharedPreferencesManager= new SharedPreferencesManager(AccesoriesRequestsActivity.this);
        carModle =sharedPreferencesManager.getCar_Modle();
        carYear =sharedPreferencesManager.getCar_Year() ;
        carType =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        uploadImageHelper= new UploadImageHelper();
        uploadImageHelper.requestStoragePermission(AccesoriesRequestsActivity.this);
        carePar="Accessories";
        makOrderAccesoriesArrayList= new ArrayList<>();
         PartId=(EditText) findViewById(R.id.PartId);
        addImage= (ImageView) findViewById(R.id.add_image);
         orderImages = new ArrayList<>();
        if(!PartId.getText().toString().trim().equals("")){
            orderList=new OrderList(carePar,PartId.getText().toString().trim(),"","","","");
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.add_Accessories_detalies),Toast.LENGTH_LONG).show();

        }

        bundle = getIntent().getExtras();
        if(bundle !=null){
            car_type=bundle.getString("car_type");
            car_modle=bundle.getString("car_modle");
            car_year=bundle.getString("car_year");
             check=bundle.getBoolean("check");
            enginCapasty= bundle.getString("engineCanasta");
            color= bundle.getString("color");



        }

        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(AccesoriesRequestsActivity.this);
        hintBack= (ImageView)findViewById(R.id.hintBack);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccesoriesRequestsActivity.this,NavigationDrawerBuyer.class);
                startActivity(intent);
            }
        });
        if(check==true){
            hintBack.setVisibility(View.VISIBLE);
            makOrderAccesoriesArrayList= (ArrayList<accessoriesDescription>) getIntent().getSerializableExtra("MyClass");

        }

        brogs= (ProgressBar)findViewById(R.id.brogs);
        bitmaps=new ArrayList<>();
        URLS=new ArrayList<>();
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager    = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(layoutManager);
        adaptorOfImages=new AdaptorOfImages(bitmaps,this);
        recycler_view.setAdapter(adaptorOfImages);
        adaptorOfImages.notifyDataSetChanged();

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");

                try {
                    startActivityForResult(intent, INTENT_REQUEST_CODE);

                } catch (ActivityNotFoundException e) {

                    e.printStackTrace();
                }

            }
        });

        if (imageName!=null){
             Toast.makeText(getApplicationContext(),imageName,Toast.LENGTH_SHORT).show();
        }


        add_anther_part_detalis=(TextView)findViewById(R.id.add_anther_part_detalis);
        add_anther_part_detalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!PartId.getText().toString().trim().equals("")){
                    if(PHONE_number!=null){
                        if(imageName!=null){
                            makOrderAccesoriesArrayList.add(new accessoriesDescription(PartId.getText().toString().trim() ,URLS));
//                            makOrderAccesoriesArrayList.add(new MakOrderAccesories(PHONE_number,carType,car_modle,carYear
//                            ,formattedDate,"false",formattedDate,PartId.getText().toString(),URLS));

                            check =true;
                            Intent  intent =new Intent(AccesoriesRequestsActivity.this,AccesoriesRequestsActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("car_type" ,car_type);
                            intent.putExtra("car_modle" ,car_modle);
                            intent.putExtra("car_year" ,car_year);
                             intent.putExtra("check",check);
                            intent.putExtra("MyClass",  makOrderAccesoriesArrayList);
                            startActivity(intent);
                             startActivity(intent);
                            finish();


                        }else {
                            Toast.makeText(getApplicationContext(),getString(R.string.select_image),Toast.LENGTH_SHORT).show();

                        }


                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Phone_number_does_not_exist),Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(),getString(R.string.add_Accessories_detalies),Toast.LENGTH_LONG).show();

                }


            }
        });
        submet_requst= (Button) findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!PartId.getText().toString().trim().equals("")){
                    if(PHONE_number!=null){
                        if(imageName!=null){
                            String date =formattedDate;
                            makOrderAccesoriesArrayList.add(new accessoriesDescription(PartId.getText().toString().trim(),URLS) );

//                            makOrderAccesoriesArrayList.add(new MakOrderAccesories(PHONE_number,carType,car_modle,carYear
//                                    ,date,"false",date,PartId.getText().toString(),URLS));

                            Toast.makeText(getApplicationContext(),getString(R.string.send_successfully),Toast.LENGTH_SHORT).show();
//                            firebaseHepler.addArequestAccessories(PHONE_number,carType ,date,makOrderAccesoriesArrayList);
                            firebaseHepler.addArequestAccessories(PHONE_number,carType,car_modle,car_year,makOrderAccesoriesArrayList,URLS,formattedDate,enginCapasty,color,PartId.getText().toString());
                             Intent intent = new Intent(AccesoriesRequestsActivity.this,NavigationDrawerBuyer.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(getApplicationContext(),getString(R.string.select_image),Toast.LENGTH_SHORT).show();

                        }


                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Phone_number_does_not_exist),Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(),getString(R.string.add_Accessories_detalies),Toast.LENGTH_LONG).show();

                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == INTENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {


                try {
                    Uri selectedImageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    Bitmap smallbitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, true);
                    bitmaps.add(smallbitmap);
                    adaptorOfImages.notifyDataSetChanged();
//                    add_image.setImageBitmap(smallbitmap);
                    imagepath = getPath(selectedImageUri);
                    File f = new File(imagepath);
                    String imageNameU = f.getName();


                    InputStream is = getContentResolver().openInputStream(data.getData());
                    uploade_post_image(AccesoriesRequestsActivity.this,smallbitmap,"Accessories",PHONE_number,imageNameU,brogs );


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public   void  uploade_post_image(final Activity context, Bitmap bitmap, final String UserID, final String RequestsType, final  String name, final ProgressBar bar ) {
        FirebaseStorage storage = FirebaseStorage.getInstance();


        StorageReference storageRef = storage.getReferenceFromUrl("gs://odo-960fa.appspot.com/");
        String ImageUrl ;
        StorageReference mountainImagesRef = storageRef.child("Orders/"+ name+".jpg");
        // Get the data from an ImageView as bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        bar.setVisibility(View.VISIBLE);
        UploadTask uploadTask = mountainImagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(context, "uploading image failed", Toast.LENGTH_SHORT).show();

                bar.setVisibility(View.GONE);
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int progress = (int) ((100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                bar.setProgress(progress);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                String Imagerl =downloadUrl.toString();
                bar.setVisibility(View.GONE);
                String sh= "";

//                if(name.contains("_")||name.contains(",")||name.contains("/")){
                String name1 = name.replace("_","").replace(",","").replace("/","").replace("#","").replace(".","").replace("[","").replace("]","");

//                }
                if(name1!=null){
                    URLS.add(Imagerl);
                 }

                imageName=Imagerl;
                Toast.makeText(context,Imagerl, Toast.LENGTH_SHORT).show();


            }
        });



    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setTitle(R.string.AccesoriesRequests);

    }
}
