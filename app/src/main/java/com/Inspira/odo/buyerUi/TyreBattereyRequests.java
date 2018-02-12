package com.Inspira.odo.buyerUi;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.AdaptorTyresABatteries;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.TyresABatteries.TyresABatteries;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;



public class TyreBattereyRequests extends Fragment {

    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
    AdaptorTyresABatteries myTyresABatteriesAdapter;
//    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
    DatabaseReference mDatabase;
    View rooteView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   rooteView=     inflater.inflate(R.layout.fragment_tyre_batterey_requests, container, false);
        getActivity().setTitle(R.string.TyreBattereyRequests);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        setHasOptionsMenu(true);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        getActivity().setTitle(R.string.SparepartsRequests);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();

        initViews() ;


        return  rooteView;
    }
    private void initViews(){
        recycler_view = (RecyclerView) rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);
        getDataFirebase();
    }
    public  void getDataFirebase(){
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        mDatabase.child("Users").child(PHONE_number).child("Requests").child("TyresABatteries").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<TyresABatteries> MyOrderList= new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                     TyresABatteries value= postSnapshot.getValue(TyresABatteries.class);
                     MyOrderList.add(value);
                    if (value != null) {
                        value.setKeyPost(postSnapshot.getKey());
                    }
                    myTyresABatteriesAdapter = new AdaptorTyresABatteries(MyOrderList,getActivity(),"TyresABatteries" ,postSnapshot.getKey());
                    myTyresABatteriesAdapter.notifyDataSetChanged();
                }
                recycler_view.setAdapter(myTyresABatteriesAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
