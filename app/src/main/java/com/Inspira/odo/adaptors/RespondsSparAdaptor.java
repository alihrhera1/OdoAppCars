package com.Inspira.odo.adaptors;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.detalisOfRequest;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.spareParts.RespondsSpar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by shirya on 09/02/18.
 */

public class RespondsSparAdaptor extends RecyclerView.Adapter<RespondsSparAdaptor.ViewHolder> implements Filterable {
    private ArrayList<RespondsSpar> androidList;
    private Context context;
    private int lastPosition=-1;
    SharedPreferencesManager sharedPreferencesManager ;
    String orderId ;
    String latitude ,longitude;

    public RespondsSparAdaptor(Context c, ArrayList<RespondsSpar> responses , String orderId) {
        this.androidList = responses;
        this.context=c;
        sharedPreferencesManager= new SharedPreferencesManager(context);
        this.orderId=orderId ;
    }

    @Override
    public RespondsSparAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. response_itm, viewGroup, false);
        return new RespondsSparAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RespondsSparAdaptor.ViewHolder viewHolder, final int i) {
        viewHolder.price.setText(androidList.get(i).getPrice().toString() +"EGP");
        viewHolder.description_request.setText(androidList.get(i).getDescription());

        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(orderId).child("companyOnMap").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                  latitude = dataSnapshot.child("latitude").getValue().toString();
                  longitude = dataSnapshot.child("longitude").getValue().toString();
                viewHolder.location.setText(getGeocodeName(Double.parseDouble(latitude),Double.parseDouble(longitude)));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        viewHolder.time.setText("time");
        viewHolder.Favorite_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesManager= new SharedPreferencesManager(context);

            }
        });

//        Double.parseDouble




        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context,detalisOfRequest.class);
                intent.putExtra("price",androidList.get(i).getPrice());
                intent.putExtra("SellerPhoneNumber", orderId);
                intent.putExtra("Latitude",latitude);
                intent.putExtra("Longitude",longitude);
                intent.putExtra("name",androidList.get(i).getDescription());
                intent.putExtra("CompanyAddress",getGeocodeName(Double.parseDouble(latitude),Double.parseDouble(longitude)));
//                intent.putParcelableArrayListExtra("Response", (ArrayList<? extends Parcelable>) androidList );

                context.startActivity(intent);

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

    @Override
    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults filterResults = new FilterResults();
//                if (constraint != null) {
//                    List locations = findLocations(context, constraint.toString());
//
//                    // Assign the data to the FilterResults
//                    filterResults.values = locations;
//                    filterResults.count = locations.size();
//                }
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                if (results != null && results.count > 0) {
//                    resultList = (List) results.values;
//                    notifyDataSetChanged();
//                } else {
//                    notifyDataSetChanged();
//                }
//            }
//        };
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView description_request,price,location ,time ;
        private CardView card;
        private ImageView Favorite_image  ,image_itme_selle;
        public ViewHolder(View view) {
            super(view);
            card=(CardView)view.findViewById(R.id.card);
            description_request = (TextView)view.findViewById(R.id.description_request);
            price = (TextView)view.findViewById(R.id.price);
            location = (TextView)view.findViewById(R.id.location);
            time = (TextView)view.findViewById(R.id.time);
            Favorite_image = (ImageView) view.findViewById(R.id.Favorite_image);




        }
    }

    public String getGeocodeName(double latitude, double longitude) {
        Context context = getApplicationContext();

        Geocoder geocoder = new Geocoder( context);
        List<Address> addresses = null;
        String unknown ="Unknown Location";
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return unknown;
        }
        if ( addresses == null ||addresses.size() == 0) {
            return unknown;
        }
        Address address = addresses.get(0);



        String cn = address.getCountryName();

        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        String mainLocality = address.getSubAdminArea();

        return city + ", " + state+ ", " +country;
    }

    public void add(RespondsSpar r) {
        androidList.add(r);
        notifyItemInserted(androidList.size() -1);
    }

    public void addAll(List<RespondsSpar> moveResults) {
        for (RespondsSpar result : moveResults) {
//            if (!result.getProducts().isEmpty())
            add(result);
        }
    }

    public void remove(RespondsSpar r) {
        int position = androidList.indexOf(r);
        if (position > -1) {
            androidList.remove(position);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    private RespondsSpar getItem(int position) {
        return androidList.get(position);
    }




}