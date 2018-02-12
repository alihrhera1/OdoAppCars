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
import com.Inspira.odo.adaptors.MyRequestAdapter;
 import com.Inspira.odo.database.SharedPreferencesManager;
 import com.Inspira.odo.model.spareParts.SpareParts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SparepartsRequests extends Fragment {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
    MyRequestAdapter myRequestAdapter ;
 //    private FilterData data = new FilterData();
//    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
    DatabaseReference mDatabase;


    View rooteView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView = inflater.inflate(R.layout.fragment_my_request, container, false);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        setHasOptionsMenu(true);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        getActivity().setTitle(R.string.SparepartsRequests);
         PHONE_number= sharedPreferencesManager.getUser_Phoe();

        initViews() ;


        return  rooteView ;
    }



    private void initViews(){
        recycler_view =(RecyclerView) rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);
        getDataFirebase();
    }
      public  void getDataFirebase(){
         PHONE_number= sharedPreferencesManager.getUser_Phoe();
          mDatabase.child("Users").child(PHONE_number).child("Requests").child("spareParts").addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                  ArrayList<SpareParts> MyOrderList = new ArrayList<>();
                  for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                      SpareParts value = postSnapshot.getValue(SpareParts.class);
                      value.setKeyPost(postSnapshot.getKey());
                      MyOrderList.add(value);

                      myRequestAdapter = new MyRequestAdapter(MyOrderList, getActivity(), "SpareParts", postSnapshot.getKey());
                      myRequestAdapter.notifyDataSetChanged();
                  }
                  recycler_view.setAdapter(myRequestAdapter);


              }

              @Override
              public void onCancelled(DatabaseError databaseError) {

              }
          });



      }






}
