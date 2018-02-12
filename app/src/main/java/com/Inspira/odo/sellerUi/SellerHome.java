
package com.Inspira.odo.sellerUi;


import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.AdaporAccessories;
import com.Inspira.odo.adaptors.AdaptorTyresABatteries;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.adaptors.MyRequestAdapter;
import com.Inspira.odo.data.Model.DataCar;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.accessories.AccessoriesRequest;
import com.Inspira.odo.model.spareParts.SpareParts;
import com.Inspira.odo.model.TyresABatteries.TyresABatteries;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Map;



public class SellerHome extends Fragment {

  private RecyclerView recycler_view;
  private View rooteView;
  private ArrayList<String> categories_CarType, categories_car_model, categories_car_year;
  private DataCar dataCar;
  private String itemTYear, item_model, itemType;
  private Map<String, ArrayList<String>> AllData;
  private SharedPreferencesManager sharedPreferencesManager;
 //   private FilterData data = new FilterData();
 //  private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
  private AdView adView ;
  private String phone_number ;
  private AdaporAccessories   adaporAccessories ;
  private MyRequestAdapter myRequestAdapter ;
  private AdaptorTyresABatteries myTyresABatteriesAdapter ;
  private DatabaseReference mDatabase;
  private String  spareParts ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView = inflater.inflate(R.layout.fragment_seller_home, container, false);
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        mDatabase= FirebaseDatabase.getInstance().getReference();
        phone_number =   sharedPreferencesManager.getUser_Phoe() ;
        adView = (AdView) rooteView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
//         datah = new ArrayList<>();
        Toast.makeText(getActivity(), sharedPreferencesManager.getPartType()+"part", Toast.LENGTH_LONG).show();
        getActivity().findViewById(R.id.filter).setVisibility(View.VISIBLE);
           spareParts= getActivity().getString(R.string.spareParts);
        getActivity().findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showFilter();


            }
        });

        initViews();
        return rooteView;

    }


    private void initViews() {
        recycler_view = (RecyclerView) rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);
        final String type= sharedPreferencesManager.getPartType();
//        final String type=getActivity().getString(R.string.tyres);
        if(type!=null){
             if(type.equals(getActivity().getString(R.string.spareParts))){
                 getDAtaHome("spareParts",recycler_view);
             }else if(type.equals(getActivity().getString(R.string.batteries))){
                 getDAtaHome("TyresABatteries",recycler_view);

             }else  if(type.equals(getActivity().getString(R.string.tyres))){
                 getDAtaHome("TyresABatteries",recycler_view);

             }else if(type.equals(getActivity().getString(R.string.accessories))){
                 getDAtaHome("accessories",recycler_view);

             }
//
        }




    }
     private  void getDAtaHome(String Tpe, final RecyclerView  recycler_view ){
         mDatabase= FirebaseDatabase.getInstance().getReference();
         switch (Tpe) {
             case "accessories":
                 mDatabase.child("Requests").child("accessories").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         ArrayList<AccessoriesRequest> MyOrderList = new ArrayList<>();
                         for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                             postSnapshot.getKey();
                              AccessoriesRequest value = postSnapshot.getValue(AccessoriesRequest.class);
                             value.setKeyPost(postSnapshot.getKey());
                             MyOrderList.add(value);
                             adaporAccessories = new AdaporAccessories(MyOrderList, getActivity());
                             adaporAccessories.notifyDataSetChanged();
                         }
                         recycler_view.setAdapter(adaporAccessories);


                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });

                 break;
             case "spareParts":
                 mDatabase.child("Requests").child("spareParts").addListenerForSingleValueEvent(new ValueEventListener() {
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

                 break;
             case "TyresABatteries":
                 mDatabase.child("Requests").child("TyresABatteries").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         ArrayList<TyresABatteries> MyOrderList = new ArrayList<>();
                         for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                             postSnapshot.getKey();
//                             TyresABatteries value = postSnapshot.getValue(TyresABatteries.class);
//                             MyOrderList.add(value);
//                             value.setKeyPost(postSnapshot.getKey());
//                             myTyresABatteriesAdapter = new AdaptorTyresABatteries(MyOrderList, getActivity(), "TyresABatteries", postSnapshot.getKey());
//                             myTyresABatteriesAdapter.notifyDataSetChanged();
                         }
                         recycler_view.setAdapter(myTyresABatteriesAdapter);


                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });

                 break;
         }

     }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.home_seller);
    }


    public void showFilter() {
//        boolean check = false;
        final Dialog okdialog = new Dialog(getActivity(), R.style.custom_dialog_theme);
        okdialog.setContentView(R.layout.dialog_seller_home_data);
        Button OK_d = (Button) okdialog.findViewById(R.id.ok);
        Spinner SpinnerCarType = (Spinner) okdialog.findViewById(R.id.SpinnerCarType);
        Spinner your_car_model = (Spinner) okdialog.findViewById(R.id.your_car_model);
        Spinner your_car_year = (Spinner) okdialog.findViewById(R.id.your_car_year);
//        ArrayList<String> categories;
        AllData = new HashMap<>();
        dataCar = new DataCar();
        CustomArrayAdapter_Spinner myAdaptor_CarType, myAdaptor_car_model, myAdaptor_year;
        categories_CarType = new ArrayList< >();
        categories_car_year = new ArrayList< >();
        categories_car_model = new ArrayList< >();
        categories_CarType.add(getString(R.string.your_car_type));
        dataCar = new DataCar();
        AllData = dataCar.getAllData();
        dataCar.getCarTypesEnglish();
        categories_CarType.addAll(dataCar.getCarTypesEnglish());
        categories_CarType.add(getString(R.string.your_car_type));
        categories_car_model.add(getString(R.string.your_car_modle));
        categories_car_year.add(getString(R.string.your_car_year));
        categories_car_year.addAll(dataCar.getYears());


        myAdaptor_CarType = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_CarType);
        myAdaptor_car_model = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_model);
        myAdaptor_year = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_year);
        your_car_year.setAdapter(myAdaptor_year);
        SpinnerCarType.setAdapter(myAdaptor_CarType);
        your_car_model.setAdapter(myAdaptor_car_model);


        SpinnerCarType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String itemTyp = parent.getItemAtPosition(position).toString();
                if (itemTyp.equals(getString(R.string.your_car_type))) {
                    itemType = null;
                 } else {
                    itemType = parent.getItemAtPosition(position).toString();
                    categories_car_model.clear();
                    categories_car_model.add(getString(R.string.your_car_modle));
                    categories_car_model.addAll(AllData.get(itemType));


                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        myAdaptor_car_model = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_model);
        your_car_model.setAdapter(myAdaptor_car_model);

        your_car_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String item_mode = parent.getItemAtPosition(position).toString();
                if (item_mode.equals(getString(R.string.your_car_modle))) {
                    item_model = null;
                 } else {

                    item_model = parent.getItemAtPosition(position).toString();
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        your_car_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String itemTYea = parent.getItemAtPosition(position).toString();
                if (itemTYea.equals(getString(R.string.your_car_year))) {
                    itemTYear = null;
                 } else {
                    itemTYear = parent.getItemAtPosition(position).toString();
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        OK_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                List<RelatedOrder> filteredList = data.getAllData();
//                if (item_model != null && itemTYear != null && itemType != null) {
//                    filteredList = data.getFilter(itemType,item_model,itemTYear ,filteredList);
//                    dataSellerHomeAdaptor.clear();
//                    dataSellerHomeAdaptor.addAll(filteredList);
//                    dataSellerHomeAdaptor.notifyDataSetChanged();

//                    addToSelectedMap("itemType", itemType);
//                    addToSelectedMap("item_model", item_model);
//                    addToSelectedMap("itemTYear", itemTYear);
//                    if (data != null) {
//                        ArrayMap<String, List<String>> applied_filter = applied_filters;
//                        if (applied_filter.size() != 0) {
//                            List<RelatedOrder> filteredList = data.getAllData();
//                            //iterate over arraymap
//                            for (Map.Entry<String, List<String>> entry : applied_filter.entrySet()) {
//                                Log.d("k9res", "entry.key: " + entry.getKey());
//                                switch (entry.getKey()) {
//                                    case "itemType":
//                                        filteredList = data.getCarType(entry.getValue(), filteredList);
//                                        break;
//                                    case "item_model":
//                                        filteredList = data.getModel(entry.getValue(), filteredList);
//                                        break;
//                                    case "itemTYear":
//                                        filteredList = data.getYear(entry.getValue(), filteredList);
//                                        break;
//
//                                }
//                                dataSellerHomeAdaptor.clear();
//                                dataSellerHomeAdaptor.addAll(filteredList);
//                            }
//                        }
//                    }

//                } else {
//
//                    Toast.makeText(getApplicationContext(), getString(R.string.choose_data),Toast.LENGTH_SHORT).show();
//                }
                okdialog.dismiss();
            }
        });
        okdialog.show();


    }

//    private void addToSelectedMap(String key, String value) {
//        if (applied_filters.get(key) != null && !applied_filters.get(key).contains(value)) {
//            applied_filters.get(key).add(value);
//        } else {
//            List<String> temp = new ArrayList<>();
//            temp.add(value);
//            applied_filters.put(key, temp);
//        }
//    }


}