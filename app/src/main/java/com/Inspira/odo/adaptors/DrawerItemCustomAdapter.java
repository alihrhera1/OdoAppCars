package com.Inspira.odo.adaptors;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.ObjectDrawerItem;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;

import java.util.ArrayList;


public class DrawerItemCustomAdapter extends BaseAdapter {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<ObjectDrawerItem> mData = new ArrayList<>();
    Activity activiy ;
    int x = 0 ;
    SharedPreferencesManager sharedPreferencesManager ;
    String phone ;

    public DrawerItemCustomAdapter(Activity activit,Context context, int layoutResourceId,  ArrayList<ObjectDrawerItem>  data) {
        this.mContext = context;
        this.mLayoutResourceId = layoutResourceId;
        this.mData = data;
        activiy =activit;
        sharedPreferencesManager= new SharedPreferencesManager(context);

        phone= sharedPreferencesManager.getUser_Phoe();

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;
        ObjectDrawerItem objectDrawerItem = mData.get(position);

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(mLayoutResourceId, parent, false);
//        if(position==2){
////            listItem.setBackgroundColor(Color.BLACK);
//            listItem.setBackground( mContext.getResources().getDrawable(R.drawable.rounded_border));
//        }else {
//            listItem.setBackground( mContext.getResources().getDrawable(R.drawable.rounded_border));
//        }


         TextView nameTextView = (TextView) listItem.findViewById(R.id.drawer_item_name);
        final TextView numbers = (TextView)listItem.findViewById(R.id.numbers);

         nameTextView.setText(objectDrawerItem.getName());

        if (activiy instanceof NavigationDrawerSeler && position==0){
            phone= sharedPreferencesManager.getUser_Phoe();





        }


        return listItem;
    }


 }
