package com.Inspira.odo.adaptors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.AccesoriesSubOrders;
import com.Inspira.odo.buyerUi.RequestResponses;
import com.Inspira.odo.buyerUi.SubOrderList;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.DateTimeHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.accessories.AccessoriesRequest;
import com.Inspira.odo.model.accessories.accessoriesDescription;
import com.Inspira.odo.sellerUi.RespondtoaReques;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AdaporAccessories   extends RecyclerView.Adapter<AdaporAccessories.ViewHolder> {
    private ArrayList<AccessoriesRequest> androidList;
    private Context context;
    private int lastPosition=-1;
    private  FirebaseHepler firebaseHepler ;
    private SharedPreferencesManager sharedPreferencesManager ;
     public AdaporAccessories(ArrayList<AccessoriesRequest> android, Context c  ) {
        this.androidList = android;
        this.context=c;
         firebaseHepler=new FirebaseHepler(context);
         sharedPreferencesManager= new SharedPreferencesManager(context);

    }

    @Override
    public AdaporAccessories.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);


        return new AdaporAccessories.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaporAccessories.ViewHolder viewHolder, final int i) {
        viewHolder.foater.setVisibility(View.GONE);
        viewHolder.Name_request.setText(androidList.get(i).getAccessoriesDescription().get(0).getPart());
        viewHolder.name_car.setText(androidList.get(i).getCarDetails().getCarModel());
        viewHolder.Type_car.setText(androidList.get(i).getCarDetails().getCarType());
        viewHolder.year_car.setText(androidList.get(i).getCarDetails().getCarYear());
        viewHolder.model_car.setText(androidList.get(i).getCarDetails().getCarModel());


        long dat = androidList.get(i).getDateAdded();
        viewHolder.time_of_post.setText(dat+ "");

        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        if(sharedPreferencesManager.getUserType().equals("buyer")){
            viewHolder.Favorite_image.setVisibility(View.GONE);
        }else {

            mDatabase.child("Users").child(sharedPreferencesManager.getUser_Phoe())
                    .child("favourites").child(androidList.get(i).getKeyPost()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if( dataSnapshot.exists()){
                        viewHolder.Favorite_image.setImageResource(R.drawable.staryellow);


                    }else {
                        viewHolder.Favorite_image.setImageResource(R.drawable.star);


                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        mDatabase.child("Users").child(sharedPreferencesManager.getUser_Phoe())
                .child("Visited").child(androidList.get(i).getKeyPost()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if( dataSnapshot.exists()){
                    viewHolder.card.setCardBackgroundColor(Color.GREEN);


                }else {


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





//
        viewHolder.Favorite_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.Favorite_image.setImageResource(R.drawable.staryellow);
                SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
                 firebaseHepler.setFaverits(sharedPreferencesManager.getUser_Phoe(),androidList.get(i).getKeyPost(),true);
            }
        });
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseHepler.setVisited(sharedPreferencesManager.getUser_Phoe(),androidList.get(i).getKeyPost(),true);
                viewHolder.card.setCardBackgroundColor(Color.GREEN);


                if(sharedPreferencesManager.getUserType().equals("buyer")){
                    Intent intent = new Intent(context, RequestResponses.class);
                    intent.putExtra("buyerPhoneNumber",androidList.get(i).getBuyerPhoneNumber());
                    intent.putExtra("carModel", androidList.get(i).getCarDetails().getCarModel());
                    intent.putExtra("carType",androidList.get(i).getCarDetails().getCarType());
                    intent.putExtra("carYear",androidList.get(i).getCarDetails().getCarYear());
                    intent.putExtra("TypeRequest","accessories");
                    intent.putExtra("key",androidList.get(i).getKeyPost());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.startActivity(intent);

                }else {
                    if (androidList.get(i).getAccessoriesDescription().size() >1) {
                        ArrayList<accessoriesDescription> accessoriesDescription = androidList.get(i).getAccessoriesDescription() ;
                        if( androidList.get(i).getAccessoriesDescription().size()>0){}
                        Intent intent = new Intent(context, AccesoriesSubOrders.class);
                        intent.putExtra("orders", accessoriesDescription);
                        intent.putExtra("MyClass",  androidList.get(i).getCarDetails());
                        intent.putExtra("carModel", androidList.get(i).getCarDetails().getCarModel());
                        intent.putExtra("carType",androidList.get(i).getCarDetails().getCarType());
                        intent.putExtra("carYear",androidList.get(i).getCarDetails().getCarYear());
                        intent.putExtra("buyerPhoneNumber", androidList.get(i).getBuyerPhoneNumber());
                        intent.putExtra("key", androidList.get(i).getKeyPost());
                        context.startActivity(intent);


                    }else {

                        Toast.makeText(context,"this",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(context, RespondtoaReques.class);
                         intent.putExtra("buyerPhoneNumber",androidList.get(i).getBuyerPhoneNumber());
                        intent.putExtra("key",androidList.get(i).getKeyPost());
                        intent.putExtra("TypeRequest","accessories");
                        intent.putStringArrayListExtra("imagees",androidList.get(i).getAccessoriesDescription().get(0).getPhotosURL());
                        intent.putExtra("image",androidList.get(i).getAccessoriesDescription().get(0).getPhotosURL().get(0));
                        int index =androidList.indexOf(androidList.get(i));
                        Toast.makeText(context, index +"  i", Toast.LENGTH_SHORT).show();
                        intent.putExtra("index",index);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        context.startActivity(intent);

                    }


                }


            }
        });
//
        setAnimation(viewHolder.card, i);
        if(!androidList.get(i).getAccessoriesDescription().get(0).getPhotosURL().isEmpty()){
            String imagee = androidList.get(i).getAccessoriesDescription().get(0).getPhotosURL().get(0);

            Picasso.with(context)
                    .load(imagee)
                    .into(viewHolder.image_itme_selle);


        }


//
//
    }



    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount()
    {
        return androidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView Name_request, name_car, Type_car, year_car, model_car, color_car, time_of_post;
        private CardView card;
        private ImageView Favorite_image, image_itme_selle;
        LinearLayout foater ;

        public ViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.card);
            card.setCardBackgroundColor(Color.TRANSPARENT);
            card.setOnClickListener(this);
            Name_request = (TextView) view.findViewById(R.id.Name_request);
            name_car = (TextView) view.findViewById(R.id.name_car);
            Type_car = (TextView) view.findViewById(R.id.Type_car);
            year_car = (TextView) view.findViewById(R.id.year_car);
            model_car = (TextView) view.findViewById(R.id.model_car);
            color_car = (TextView) view.findViewById(R.id.color_car);
            time_of_post = (TextView) view.findViewById(R.id.time_of_post);
            Favorite_image = (ImageView) view.findViewById(R.id.Favorite_image);
            image_itme_selle = (ImageView) view.findViewById(R.id.image_itme_selle);
            foater= (LinearLayout) view.findViewById(R.id.foater);


        }

        @Override
        public void onClick(View view) {

        }



    }
    public String printDifference(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        return   elapsedDays +" d :"+ elapsedHours +"  h:"+ elapsedMinutes+"  m :"+ elapsedSeconds +" s";


    }



}
