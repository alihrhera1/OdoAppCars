package com.Inspira.odo.buyerUi;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.FavoritesDataAdaptor;
import com.Inspira.odo.data.Model.FavouriteDataAdd;
import com.Inspira.odo.data.Model.FavouriteShowData;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyFavourites extends Fragment {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
    FavoritesDataAdaptor myRequestAdapter ;
    DatabaseReference mDatabase;
    ArrayList<FavouriteShowData> MyOrderList ;
    View rooteViw;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteViw = inflater.inflate(R.layout.fragment_my_favourites, container, false);
        getActivity().setTitle(R.string.My_Favorites);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        setHasOptionsMenu(true);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
         PHONE_number= sharedPreferencesManager.getUser_Phoe();

        initViews() ;

        return rooteViw;
    }

    private void initViews(){
        recycler_view = rooteViw.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);

        getDataFirebase();
    }
    public  void getDataFirebase(){
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        mDatabase.child("users").child(PHONE_number).child("Favorites").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<FavouriteShowData> favouriteDataAddArrayList= new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    postSnapshot.getKey();
                    Toast.makeText(getActivity(), postSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                    FavouriteDataAdd value= postSnapshot.getValue(FavouriteDataAdd.class);
                    Toast.makeText(getActivity(), value.getPost_id(), Toast.LENGTH_SHORT).show();
                    show_data(value,favouriteDataAddArrayList);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public  void show_data(FavouriteDataAdd MyOrderLis, final ArrayList<FavouriteShowData> favouriteDataAddArrayList){
        favouriteDataAddArrayList.clear();
         myRequestAdapter = new FavoritesDataAdaptor(favouriteDataAddArrayList,getActivity());
        recycler_view.setAdapter(myRequestAdapter);
        myRequestAdapter.notifyDataSetChanged();
        if(MyOrderLis.getTypeOfRequest().equals("SpareParts")){
            mDatabase.child("users").child(PHONE_number).child("Requests").child("SpareParts").child(MyOrderLis.getPost_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    FavouriteShowData favouriteShowData = new FavouriteShowData(dataSnapshot.getKey(),
                            dataSnapshot.child("carType").getValue().toString(), dataSnapshot.child("carModel").getValue().toString(),
                            dataSnapshot.child("orderImages").child("0").getValue().toString(),dataSnapshot.child("orderList").child("0").child("part").getValue().toString());

                    favouriteDataAddArrayList.add(favouriteShowData);
                    myRequestAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(MyOrderLis.getTypeOfRequest().equals("TyresABatteries")){
            mDatabase.child("users").child(PHONE_number).child("Requests").child("TyresABatteries").child(MyOrderLis.getPost_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                      if(!dataSnapshot.child("poles").getValue().toString().equals("")){
                          String data=dataSnapshot.child("poles").getValue().toString() ;
                        FavouriteShowData favouriteShowData = new FavouriteShowData(dataSnapshot.getKey(),
                                dataSnapshot.child("carType").getValue().toString(), dataSnapshot.child("carModel").getValue().toString(),
                                "",data);
                        favouriteDataAddArrayList.add(favouriteShowData);
                        myRequestAdapter.notifyDataSetChanged();
                    }else if(!dataSnapshot.child("run_flot_tyres").getValue().toString().equals("")){
                          String data=dataSnapshot.child("run_flot_tyres").getValue().toString() ;
                        FavouriteShowData favouriteShowData = new FavouriteShowData(dataSnapshot.getKey(),
                                dataSnapshot.child("carType").getValue().toString(), dataSnapshot.child("carModel").getValue().toString(),
                                "",data);
                        favouriteDataAddArrayList.add(favouriteShowData);
                        myRequestAdapter.notifyDataSetChanged();
                    }else  {
                          String  data="don't know Batteries";
                        FavouriteShowData favouriteShowData = new FavouriteShowData(dataSnapshot.getKey(),
                                dataSnapshot.child("carType").getValue().toString(), dataSnapshot.child("carModel").getValue().toString(),
                                "",data);
                        favouriteDataAddArrayList.add(favouriteShowData);
                        myRequestAdapter.notifyDataSetChanged();
                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else  {

        }






    }

}
