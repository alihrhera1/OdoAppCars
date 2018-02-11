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
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.firebase.FirebaseHepler;
import com.Inspira.odo.helper.DateTimeHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.spareParts.PartsDescription;
import com.Inspira.odo.sellerUi.RespondtoaReques;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SubOrderAdaptors  extends RecyclerView.Adapter<SubOrderAdaptors.ViewHolder> {
    private ArrayList<PartsDescription> androidList;
    private Context context;
    private int lastPosition=-1;
    SimpleDateFormat formater ;
    DateTimeHelper dateTimeHelper ;
     MyApplication myApplication ;
    String imagee;
    FirebaseHepler firebaseHepler ;
    SharedPreferencesManager sharedPreferencesManager ;
    String carModel ,carType,carYear,engineCapacity ,index,buyerPhoneNumber,key;
    public SubOrderAdaptors(ArrayList<PartsDescription> android, Context c, String carType,
                            String carModel, String carYear, String engineCapacity , String index ,
                            String buyerPhoneNumber ,String key) {
        this.androidList = android;
        this.context=c;
         this.formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateTimeHelper= new DateTimeHelper(context);
        myApplication= new MyApplication();
        firebaseHepler=new FirebaseHepler(context);
        sharedPreferencesManager= new SharedPreferencesManager(context);
        this.carType=carType;
        this.carModel=carModel;
        this.carYear=carYear;
        this.engineCapacity=engineCapacity;
         this.index =index;
        this.key=key;
        this.buyerPhoneNumber=buyerPhoneNumber;


    }

    @Override
    public SubOrderAdaptors.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);


        return new SubOrderAdaptors.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubOrderAdaptors.ViewHolder viewHolder, final int i) {

       viewHolder.Name_request.setText(androidList.get(i).getPart());
        viewHolder.color_car.setText(engineCapacity);
        viewHolder.name_car.setText(carModel);
        viewHolder.Type_car.setText( carType);
        viewHolder.year_car.setText( carYear);
        viewHolder.model_car.setText( carModel);
        viewHolder.Favorite_image.setVisibility(View.GONE);

        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"this",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context, RespondtoaReques.class);
                 intent.putExtra("buyerPhoneNumber",sharedPreferencesManager.getUser_Phoe());
                intent.putExtra("key",key);
                intent.putExtra("TypeRequest","spareParts");
                intent.putExtra("image",androidList.get(i).getPhotosURL().get(0));
                int index =androidList.indexOf(androidList.get(i));
                Toast.makeText(context, index +"  i", Toast.LENGTH_SHORT).show();
                intent.putExtra("index",index);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);


            }
        });

        setAnimation(viewHolder.card, i);
        if(!androidList.get(i).getPhotosURL().isEmpty()){
            imagee = androidList.get(i).getPhotosURL().get(0);

            Picasso.with(context)
                    .load(imagee)
                    .into(viewHolder.image_itme_selle);
        }






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


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

//             ArrayList<Response> responses = new ArrayList<>();
//            responses.addAll(androidList.get(position).getResponses());
//            myApplication.setResponses(responses);
//            Intent intent = new Intent(context, RequestResponses.class);
//            intent.putParcelableArrayListExtra("Response", (ArrayList<? extends Parcelable>)  responses);
//            intent.putExtra("orderId",androidList.get(position).getId());
//            intent.putExtra("Response", responses);
//             context.startActivity(intent);
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
