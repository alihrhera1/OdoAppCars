package com.Inspira.odo.adaptors;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.Inspira.odo.R;

import java.util.ArrayList;


public class AdaptorOfImages   extends RecyclerView.Adapter<AdaptorOfImages.ViewHolder> {
    private ArrayList<Bitmap> androidList;
    private Context context;
    private int lastPosition=-1;

    public AdaptorOfImages(ArrayList<Bitmap> android, Context c) {
        this.androidList = android;
        this.context=c;
    }

    @Override
    public AdaptorOfImages.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. imag_itme, viewGroup, false);


        return new AdaptorOfImages.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptorOfImages.ViewHolder viewHolder, final int i) {


       viewHolder.imageView3.setImageBitmap(androidList.get(i));



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

    public class ViewHolder extends RecyclerView.ViewHolder{
         private CardView card;
          ImageView imageView3  ;
        public ViewHolder(View view) {
            super(view);
            card=(CardView)view.findViewById(R.id.card);
            imageView3=(ImageView)view.findViewById(R.id.imageView3);




        }
    }

}