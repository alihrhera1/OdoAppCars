package com.Inspira.odo.buyerUi;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
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
import com.Inspira.odo.data.Model.CarDataDetails;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.helper.UploadImageHelper;
import com.Inspira.odo.model.spareParts.PartsDescription;
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
import java.util.HashMap;
import java.util.Map;

public class AddAntherPartDetails extends AppCompatActivity {

    Button submet_requst ;
//    ArrayList<OrderList>  orderList ;
    ArrayList<PartsDescription> sparePartsDatas ;
     EditText part ;
     String enginCapasty ,color ;
    Bundle  bundle ;
    String  car_type ,car_modle ,car_year , request_type;
    ImageView add_image ;
    private static final int INTENT_REQUEST_CODE = 100;
    UploadImageHelper uploadImageHelper ;
    private String imagepath=null;
     String imageName = null ;
    ArrayList<String>URLS;
    SharedPreferencesManager sharedPreferencesManager ;
     String PHONE_number ;
    LocaleHelper localeHelper ;
    ImageView go_back  ,hintBack;
     boolean check = false ;
    TextView Add_anther_part_details ;
    FirebaseHepler firebaseHepler ;
    ProgressBar brogs ;
     ArrayList<Bitmap> bitmaps;
    RecyclerView recycler_view;
    AdaptorOfImages adaptorOfImages ;
    Map<String,String > imagHash  ;
    String formattedDate ;
    CarDataDetails carDataDetails ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_anther_part_details);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        formattedDate = df.format(c.getTime());
        firebaseHepler= new FirebaseHepler(AddAntherPartDetails.this);
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        bitmaps=new ArrayList<>();
        URLS=new ArrayList<>();
        imagHash = new HashMap<>();
        String lange=  localeHelper.getLanguage(AddAntherPartDetails.this);
        brogs= (ProgressBar)findViewById(R.id.brogs);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAntherPartDetails.this,NavigationDrawerBuyer.class);
                startActivity(intent);
            }
        });
//        orderList= new ArrayList<>();
        sparePartsDatas= new ArrayList<>();
        hintBack= (ImageView)findViewById(R.id.hintBack);
        Add_anther_part_details=(TextView)findViewById(R.id.Add_anther_part_details);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager    = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(layoutManager);
        adaptorOfImages=new AdaptorOfImages(bitmaps,this);
        recycler_view.setAdapter(adaptorOfImages);
        adaptorOfImages.notifyDataSetChanged();

        sharedPreferencesManager= new SharedPreferencesManager(this);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        bundle = getIntent().getExtras();
        if(bundle !=null){
            car_type=bundle.getString("car_type");
            car_modle=bundle.getString("car_modle");
            car_year=bundle.getString("car_year");
            request_type=bundle.getString("request_type");
            check=bundle.getBoolean("check");
            enginCapasty= bundle.getString("engineCanasta");
            color= bundle.getString("color");



        }
          carDataDetails = new CarDataDetails(car_modle,car_type,car_year,formattedDate,"false");

        if(check==true){
            hintBack.setVisibility(View.VISIBLE);
            sparePartsDatas = (ArrayList<PartsDescription>) getIntent().getSerializableExtra("MyClass");
            Toast.makeText(this, sparePartsDatas.get(0).getPart()  +"   getPart()", Toast.LENGTH_SHORT).show();

        }
         uploadImageHelper= new UploadImageHelper();
        uploadImageHelper.requestStoragePermission(AddAntherPartDetails.this);

         part=(EditText)findViewById(R.id.part) ;
//        enginCapasty=(EditText)findViewById(R.id.enginCapasty) ;
//        color=(EditText)findViewById(R.id.color) ;
        add_image= (ImageView)findViewById(R.id.add_image);

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                try {
                    startActivityForResult(intent, INTENT_REQUEST_CODE);

                } catch (ActivityNotFoundException e) {

                    e.printStackTrace();
                }

            }
        });


        Add_anther_part_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(car_type!=null &&car_modle!=null&&car_year!=null){
                    if(PHONE_number!=null){
                        if (imageName!=null) {


                            if(!part.getText().toString().trim().equals("")){

                                if (imageName!=null) {
//                        orderImages.add(new OrderImage(imagHash));
//                                    orderList.add(new OrderList(request_type,part.getText().toString().trim(), enginCapasty.getText().toString().trim(),color.getText().toString().trim(),"-","-"));
//                                    sparePartsDatas.add(new SparePartsData(color.getText().toString().trim(), engineCanasta.getText().toString().trim(),part.getText().toString().trim(),request_type,URLS));
                                    sparePartsDatas.add(new PartsDescription(part.getText().toString(),URLS));
                                    final Dialog okdialog = new Dialog(AddAntherPartDetails.this, R.style.custom_dialog_theme);
                                    okdialog.setContentView(R.layout.ok_dialog);
                                    Button OK_d = okdialog.findViewById(R.id.ok);
//                            TextView text = okdialog.findViewById(R.id.text);
//                            text.setVisibility(View.VISIBLE);
                                    OK_d.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            okdialog.dismiss();
                                            check =true;

                                            Intent  intent =new Intent(AddAntherPartDetails.this,AddAntherPartDetails.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.putExtra("car_type" ,car_type);
                                            intent.putExtra("car_modle" ,car_modle);
                                            intent.putExtra("car_year" ,car_year);
                                            intent.putExtra("request_type",request_type);
                                            intent.putExtra("check",check);
                                            intent.putExtra("engineCanasta",enginCapasty);
                                            intent.putExtra("color",color);
                                            intent.putExtra("MyClass",  sparePartsDatas);
                                             startActivity(intent);


                                        }
                                    });
                                    okdialog.show();



                                }else {

                                    Toast.makeText(getApplicationContext(),getString(R.string.select_image),Toast.LENGTH_SHORT).show();

                                }

                            }else {
                                Toast.makeText(getApplicationContext(), getString(R.string.enter_data),Toast.LENGTH_SHORT).show();


                            }




//                            Toast.makeText(getApplicationContext(),getString(R.string.send_successfully),Toast.LENGTH_SHORT).show();
//                            Calendar c = Calendar.getInstance();
//                            System.out.println("Current time => " + c.getTime());
//                            SimpleDateFormat df = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
//                            String formattedDate = df.format(c.getTime());
//                            firebaseHepler.addArequestSpareParts(PHONE_number,car_type,car_modle ,car_year,orderList ,imagHash,formattedDate);
////                            sendOrder(PHONE_number,car_type,car_modle ,car_year,orderList ,orderImages);
//

                        }else {
                            Toast.makeText(getApplicationContext(),getString(R.string.select_image),Toast.LENGTH_SHORT).show();

                        }

                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Phone_number_does_not_exist),Toast.LENGTH_SHORT).show();

                    }

                }else {

                    Toast.makeText(getApplicationContext(),getString(R.string.The_Arabic_and_Medellin ),Toast.LENGTH_SHORT).show();
                }




            }
        });

        submet_requst =(Button)findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!part.getText().toString().trim().equals("")){

                    if (imageName!=null) {
//                        orderImages.add(new OrderImage(imagHash));

//                        orderList.add(new OrderList(request_type,part.getText().toString().trim(), enginCapasty.getText().toString().trim(),color.getText().toString().trim(),"-","-"));
//                        sparePartsDatas.add(new SparePartsData(color.getText().toString().trim(), engineCanasta.getText().toString().trim(),part.getText().toString().trim(),request_type,URLS));

                        sparePartsDatas.add(new PartsDescription(part.getText().toString(),URLS));
                    }else {

                        Toast.makeText(getApplicationContext(),getString(R.string.select_image),Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_data),Toast.LENGTH_SHORT).show();


                }



                final Dialog dialog = new Dialog(AddAntherPartDetails.this, R.style.custom_dialog_theme);
                dialog.setContentView(R.layout.comfirm_layout);
                final Button ok = dialog.findViewById(R.id.ok);
                Button no  = dialog.findViewById(R.id.no);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        dialog.dismiss();

                        if(car_type!=null &&car_modle!=null&&car_year!=null){
                            if(PHONE_number!=null){
                                if (imageName!=null) {


                                    final Dialog okdialog = new Dialog(AddAntherPartDetails.this, R.style.custom_dialog_theme);
                                    okdialog.setContentView(R.layout.ok_dialog);
                                    Button OK_d = okdialog.findViewById(R.id.ok);
                                    OK_d.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            okdialog.dismiss();
                                            Intent intent = new Intent(AddAntherPartDetails.this,NavigationDrawerBuyer.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    });
                                    okdialog.show();


                                        firebaseHepler.addArequestSpareParts(PHONE_number,car_type,car_modle ,car_year,sparePartsDatas ,formattedDate ,enginCapasty ,color ,part.getText().toString());
                                        Toast.makeText(getApplicationContext(),getString(R.string.send_successfully),Toast.LENGTH_SHORT).show();





//                                    sendOrder(PHONE_number,car_type,car_modle ,car_year,orderList~ ,orderImages);


                                }else {
                                    Toast.makeText(getApplicationContext(),getString(R.string.select_image),Toast.LENGTH_SHORT).show();

                                }

                            }else {
                                Toast.makeText(getApplicationContext(),getString(R.string.Phone_number_does_not_exist),Toast.LENGTH_SHORT).show();

                            }

                        }else {

                            Toast.makeText(getApplicationContext(),getString(R.string.The_Arabic_and_Medellin ),Toast.LENGTH_SHORT).show();
                        }






                    }
                });

                dialog.show();
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
                    uploade_post_image(AddAntherPartDetails.this,smallbitmap,"SpareParts",PHONE_number,imageNameU,brogs );


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
                     imagHash.put(name1,Imagerl);
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


}