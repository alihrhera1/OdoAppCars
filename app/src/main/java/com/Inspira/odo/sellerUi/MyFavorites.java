package com.Inspira.odo.sellerUi;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.AdaporAccessories;
import com.Inspira.odo.adaptors.AdaptorTyresABatteries;
 import com.Inspira.odo.adaptors.MyRequestAdapter;
  import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.TyresABatteries.TyresABatteries;
import com.Inspira.odo.model.accessories.AccessoriesRequest;
import com.Inspira.odo.model.spareParts.SpareParts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MyFavorites extends Fragment {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
     DatabaseReference mDatabase;
    AdaporAccessories   adaporAccessories ;
    MyRequestAdapter myRequestAdapter ;
    AdaptorTyresABatteries myTyresABatteriesAdapter ;
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
        final String type= sharedPreferencesManager.getPartType();
        if(type!=null){
            if(type.equals(getActivity().getString(R.string.spareParts))){
                getKeyes("spareParts");
            }else if(type.equals(getActivity().getString(R.string.batteries))){
                getKeyes("TyresABatteries");

            }else  if(type.equals(getActivity().getString(R.string.tyres))){
                getKeyes("TyresABatteries");

            }else if(type.equals(getActivity().getString(R.string.accessories))){
                getKeyes("accessories");

            }

        }



    }
    private  void getKeyes(final String KEY){


            mDatabase.child("Users").child(PHONE_number).child("favourites").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final ArrayList<String> Keys= new ArrayList<>();

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        postSnapshot.getKey();
                        if(postSnapshot.getKey()!=null){
                            Keys.add(postSnapshot.getKey());
                        }

                        getDAtaHome(KEY,recycler_view,Keys);

                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



    }
    private  void getDAtaHome(String Tpe, final RecyclerView  recycler_view ,ArrayList<String>keys){
        mDatabase= FirebaseDatabase.getInstance().getReference();

        switch (Tpe) {
            case "accessories":
                for(int i =0 ; i<   keys.size();i++){
                    mDatabase.child("Requests").child("accessories").child(keys.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                ArrayList<AccessoriesRequest> MyOrderList = new ArrayList<>();
                                AccessoriesRequest accessoriesRequest = new AccessoriesRequest();
                                accessoriesRequest.setKeyPost( dataSnapshot.getKey());
                                AccessoriesRequest value = dataSnapshot.getValue(AccessoriesRequest.class);
                                value.setKeyPost(dataSnapshot.getKey());
                                  MyOrderList.add(value);
                                adaporAccessories = new AdaporAccessories(MyOrderList, getActivity());
                                adaporAccessories.notifyDataSetChanged();
                                 recycler_view.setAdapter(adaporAccessories);
                                adaporAccessories.notifyDataSetChanged();
                            }else {
                                Toast.makeText(getApplicationContext(), "else", Toast.LENGTH_SHORT).show();
                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

                break;
            case "spareParts":
                for(int i =0 ; i<   keys.size();i++){
                    mDatabase.child("Requests").child("spareParts").child(keys.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                ArrayList<SpareParts> MyOrderList = new ArrayList<>();
                                Toast.makeText(getApplicationContext(), dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                                SpareParts value = dataSnapshot.getValue(SpareParts.class);
                                value.setKeyPost(dataSnapshot.getKey());
                                MyOrderList.add(value);

                                myRequestAdapter = new MyRequestAdapter(MyOrderList, getActivity(), "SpareParts", dataSnapshot.getKey());
                                myRequestAdapter.notifyDataSetChanged();
//
                                recycler_view.setAdapter(myRequestAdapter);

                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


                break;
            case "TyresABatteries":
                for(int i =0 ; i<   keys.size();i++){

                    mDatabase.child("Requests").child("TyresABatteries").child(keys.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                ArrayList<TyresABatteries> MyOrderList = new ArrayList<>();
                                dataSnapshot.getKey();
                                TyresABatteries value = dataSnapshot.getValue(TyresABatteries.class);
                                MyOrderList.add(value);
                                value.setKeyPost(dataSnapshot.getKey());
                                myTyresABatteriesAdapter = new AdaptorTyresABatteries(MyOrderList, getActivity(), "TyresABatteries", dataSnapshot.getKey());
                                myTyresABatteriesAdapter.notifyDataSetChanged();

                                recycler_view.setAdapter(myTyresABatteriesAdapter);

                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

                break;
        }

    }


}
