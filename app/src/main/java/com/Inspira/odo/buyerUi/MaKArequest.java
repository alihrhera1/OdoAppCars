package com.Inspira.odo.buyerUi;


import android.app.Dialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.UploadImageHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;


public class MaKArequest extends Fragment {
    Button submet_requst ;
    OrderList orderList ;
    List<OrderImage> orderImages ;
    EditText part , enginCapasty ,color ;
    Bundle  bundle ;
    String  car_type ,car_modle ,car_year , request_type;
    ImageView add_image ;
    private static final int INTENT_REQUEST_CODE = 100;
    UploadImageHelper uploadImageHelper ;
    private String imagepath=null;
     String imageName = null ;
    SharedPreferencesManager sharedPreferencesManager ;
    ImageView back ;
    String PHONE_number ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView = inflater.inflate(R.layout.fragment_ma_karequest, container, false);
        getActivity().setTitle(R.string.MaK_Arequest);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        car_modle =sharedPreferencesManager.getCar_Modle();
        car_year =sharedPreferencesManager.getCar_Year() ;
        car_type =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);
        submet_requst =(Button)rooteView.findViewById(R.id.submet_requst);
        add_image = (ImageView)rooteView.findViewById(R.id.add_image);

         orderImages= new ArrayList<>();
        uploadImageHelper= new UploadImageHelper();
        uploadImageHelper.requestStoragePermission(getActivity());

         part=(EditText)rooteView.findViewById(R.id.part) ;
        enginCapasty=(EditText)rooteView.findViewById(R.id.enginCapasty) ;
        color=(EditText)rooteView.findViewById(R.id.color) ;
        add_image= (ImageView)rooteView.findViewById(R.id.add_image);


        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");

                try {
                    getActivity().startActivityForResult(intent, INTENT_REQUEST_CODE);

                } catch (ActivityNotFoundException e) {

                    e.printStackTrace();
                }

            }
        });




        submet_requst =(Button)rooteView.findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                orderList.add(new OrderList("spareParts","Motor","20","red" , "amper","size"));


                if(!part.getText().toString().trim().equals("")&&
                        !enginCapasty.getText().toString().trim().equals("")&&
                        !color.getText().toString().trim().equals("")){


                    if (imageName!=null) {
//                        orderImages.add(new OrderImage(imageName));
                        orderList=new OrderList(request_type,part.getText().toString().trim(), enginCapasty.getText().toString().trim(),color.getText().toString().trim()," "," ");

                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.select_image),Toast.LENGTH_SHORT).show();

                    }



                }

                final Dialog dialog = new Dialog(getActivity(), R.style.custom_dialog_theme);
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
                                if (imageName!=null){
                                    final Dialog okdialog = new Dialog(getActivity(), R.style.custom_dialog_theme);
                                    okdialog.setContentView(R.layout.ok_dialog);
                                    Button OK_d = okdialog.findViewById(R.id.ok);
                                    OK_d.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            okdialog.dismiss();

                                        }
                                    });
                                    okdialog.show();

//                                    sendOrder(PHONE_number,car_type,car_modle ,car_year,orderList ,orderImages);


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

        return  rooteView ;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == INTENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {


                try {
                    Uri selectedImageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    add_image.setImageBitmap(bitmap);
                    imagepath = getPath(selectedImageUri);
                    File f = new File(imagepath);
                    imageName = f.getName();

                    InputStream is = getActivity().getContentResolver().openInputStream(data.getData());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().setTitle(R.string.MaK_Arequest);

    }

}
