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
import com.Inspira.odo.adaptors.AdaporAccessories;
 import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.accessories.AccessoriesRequest;
 import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AccesoriesRequests extends Fragment {
    private RecyclerView recycler_view;
    private SharedPreferencesManager sharedPreferencesManager ;
    private String PHONE_number ;
    private AdaporAccessories adaporAccessories ;
//     private FilterData data = new FilterData();
//    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
   private DatabaseReference mDatabase;
   private View rooteViw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rooteViw =   inflater.inflate(R.layout.fragment_accesories_requests, container, false);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        setHasOptionsMenu(true);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        getActivity().setTitle(R.string.AccesoriesRequests);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();

        initViews() ;



        return  rooteViw;
    }
    private void initViews(){
        recycler_view = (RecyclerView) rooteViw.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);
        getDataFirebase();
    }
    public  void getDataFirebase(){
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        mDatabase.child("Users").child(PHONE_number).child("Requests").child("accessories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<AccessoriesRequest> MyOrderList= new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    postSnapshot.getKey();
                    Toast.makeText(getApplicationContext(), postSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                    AccessoriesRequest value= postSnapshot.getValue(AccessoriesRequest.class);
                    value.setKeyPost(postSnapshot.getKey());
                    MyOrderList.add(value);
                    adaporAccessories = new AdaporAccessories(MyOrderList,getActivity() );
                    adaporAccessories.notifyDataSetChanged();
                }
                recycler_view.setAdapter(adaporAccessories);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}
