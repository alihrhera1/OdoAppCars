package com.Inspira.odo.sellerUi;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.DataSellerHomeAdaptor;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.sellerData.FilterData;
import com.Inspira.odo.sellerData.RelatedOrder;

import java.util.ArrayList;


public class OrderRequestes extends Fragment {
  private   RecyclerView recycler_view;
  private   View rooteView;
  private    ArrayList<RelatedOrder> MyOrderList;
  private    SharedPreferencesManager sharedPreferencesManager;
  private   MyApplication myApplication;
  private DataSellerHomeAdaptor dataSellerHomeAdaptor;
  private FilterData data = new FilterData();
  private   String phone_number ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView= inflater.inflate(R.layout.fragment_order_requestes, container, false);
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        phone_number =   sharedPreferencesManager.getUser_Phoe() ;
        getActivity().setTitle(R.string.Order_Requestes);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);
        initViews();
        return  rooteView;
    }

    private void initViews() {
        recycler_view = (RecyclerView) rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);



    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.Order_Requestes);
    }

}
