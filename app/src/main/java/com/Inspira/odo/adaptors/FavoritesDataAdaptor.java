package com.Inspira.odo.adaptors;

import android.content.Context;
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

import com.Inspira.odo.R;
import com.Inspira.odo.data.Model.FavouriteShowData;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.DateTimeHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
 import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shirya on 24/01/18.
 */

public class FavoritesDataAdaptor extends RecyclerView.Adapter<FavoritesDataAdaptor.ViewHolder> {
    private ArrayList<FavouriteShowData> androidList;
    private Context context;
    private int lastPosition=-1;
    SimpleDateFormat formater ;
    DateTimeHelper dateTimeHelper ;
     MyApplication myApplication ;
    String imagee;
    String nameFragmente;
    String kye;
    FirebaseHepler firebaseHepler ;
    public FavoritesDataAdaptor(ArrayList<FavouriteShowData> android, Context c ) {
        this.androidList = android;
        this.context=c;
         this.formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateTimeHelper= new DateTimeHelper(context);
        myApplication= new MyApplication();
        firebaseHepler=new FirebaseHepler(context);

    }

    @Override
    public FavoritesDataAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);


        return new FavoritesDataAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoritesDataAdaptor.ViewHolder viewHolder, final int i) {
        viewHolder.foater.setVisibility(View.GONE);
        viewHolder.time_of_post.setVisibility(View.GONE);
        viewHolder.Name_request.setText(androidList.get(i).getPart_ofCar());
        viewHolder.name_car.setText(androidList.get(i).getCarType());
        viewHolder.Type_car.setText(androidList.get(i).getCarModel());
        Calendar c = Calendar.getInstance();
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String dat = androidList.get(i).getKey_post();
        String formatt = simpleDateFormat.format(c.getTime());

        try {
            Date date1 = simpleDateFormat.parse(dat);
            Date date2 = simpleDateFormat.parse(formatt);
            String resul = dateTimeHelper.printDifference(date1, date2);
            viewHolder.time_of_post.setText(resul);




        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.Favorite_image.setVisibility(View.GONE);


        if (!androidList.get(i).getImageUrl().equals("")) {
            String imagee = androidList.get(i).getImageUrl();


            Picasso.with(context)
                    .load(imagee)
                    .into(viewHolder.image_itme_selle);
        }

        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        setAnimation(viewHolder.card, i);
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
