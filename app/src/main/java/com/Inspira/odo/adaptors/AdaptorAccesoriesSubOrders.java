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
import com.Inspira.odo.buyerUi.SubOrderList;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.accessories.accessoriesDescription;
import com.Inspira.odo.sellerUi.RespondtoaReques;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class AdaptorAccesoriesSubOrders   extends RecyclerView.Adapter<AdaptorAccesoriesSubOrders.ViewHolder> {
    private ArrayList<accessoriesDescription> androidList;
    private Context context;
    private int lastPosition=-1;
    private String carModel ,carType,carYear ,buyerPhoneNumber,key;
    SharedPreferencesManager sharedPreferencesManager ;
    public AdaptorAccesoriesSubOrders(ArrayList<accessoriesDescription> android, Context c  , String carType,
                                       String carModel, String carYear ,String buyerPhoneNumber ,String key) {
        this.androidList = android;
        this.context=c;
        this.carType=carType;
        this.carModel=carModel;
        this.carYear=carYear;
        this.buyerPhoneNumber =buyerPhoneNumber ;
        this.key= key;
        sharedPreferencesManager= new SharedPreferencesManager(context);


    }

    @Override
    public AdaptorAccesoriesSubOrders.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);


        return new AdaptorAccesoriesSubOrders.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptorAccesoriesSubOrders.ViewHolder viewHolder, final int i) {
viewHolder.foater.setVisibility(View.GONE);

        viewHolder.Name_request.setText(androidList.get(i).getPart());
        viewHolder.name_car.setText(carModel);
        viewHolder.Type_car.setText(carType);
        viewHolder.year_car.setText(carYear);
        viewHolder.model_car.setText(carModel);


        viewHolder.Favorite_image.setVisibility(View.GONE);

        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, RespondtoaReques.class);
                intent.putExtra("buyerPhoneNumber",buyerPhoneNumber);
                intent.putExtra("key",key);
                intent.putExtra("TypeRequest","accessories");
                intent.putStringArrayListExtra("imagees",androidList.get(i).getPhotosURL());
                if(!androidList.get(i).getPhotosURL().isEmpty()){
                    intent.putExtra("image",androidList.get(i).getPhotosURL().get(0));
                }else {
                    intent.putExtra("image","");
                }

                int index =androidList.indexOf(androidList.get(i));
                Toast.makeText(context, index +"  i", Toast.LENGTH_SHORT).show();
                intent.putExtra("index",index);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);
            }
        });

        setAnimation(viewHolder.card, i);
        if(!androidList.get(i).getPhotosURL().isEmpty()){

         String imagee = androidList.get(i).getPhotosURL().get(0);

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
        private TextView Name_request, name_car, Type_car, year_car, model_car;
        private CardView card;
        private ImageView Favorite_image, image_itme_selle;
        LinearLayout foater ;

        public ViewHolder(View view) {
            super(view);
            card =   view.findViewById(R.id.card);
            card.setCardBackgroundColor(Color.TRANSPARENT);
            card.setOnClickListener(this);
            Name_request =   view.findViewById(R.id.Name_request);
            name_car =  view.findViewById(R.id.name_car);
            Type_car =  view.findViewById(R.id.Type_car);
            year_car =  view.findViewById(R.id.year_car);
            model_car =  view.findViewById(R.id.model_car);
            Favorite_image =  view.findViewById(R.id.Favorite_image);
            image_itme_selle = view.findViewById(R.id.image_itme_selle);
            foater=  view.findViewById(R.id.foater);


        }

        @Override
        public void onClick(View view) {

        }



    }



}
