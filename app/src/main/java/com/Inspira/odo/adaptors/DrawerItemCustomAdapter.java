package com.Inspira.odo.adaptors;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.ObjectDrawerItem;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;
import com.squareup.picasso.Picasso;

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


        //////************** ali start******************
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ImageView photo = (ImageView)listItem.findViewById(R.id.itemphoto);
        LinearLayout itemconteiner=(LinearLayout)listItem.findViewById(R.id.itemconteiner);
        String titel=objectDrawerItem.getName();
        nameTextView.setText(titel);
        Picasso.with(mContext).load(objectDrawerItem.getPhoto()).fit().into(photo);

        if (titel.contains(mContext.getString(R.string.MyRequest))){
            listItem.setEnabled(false);
            nameTextView.setTextSize(17);
        }
        else if( titel.contains(mContext.getString(R.string.SparepartsRequests))||
                titel.contains(mContext.getString(R.string.AccesoriesRequests))
                ||titel.contains(mContext.getString(R.string.TyreBattereyRequests))){
            params.setMargins(50, 0, 1, 0);
            itemconteiner.setLayoutParams(params);

            nameTextView.setTextSize(15);
            listItem.setEnabled(true);

        }
        else if (titel.contains(mContext.getString(R.string.MaK_Arequest))){
            nameTextView.setTextSize(17);
            nameTextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));}
        else if(titel.contains(mContext.getString(R.string.Edit_Profile))||
                titel.contains(mContext.getString(R.string.Change_Language))|
                        titel.contains(mContext.getString(R.string.Loge_Out))){
            nameTextView.setTextSize(13);
            listItem.setEnabled(true);
            params.setMargins(30, 0, 1, 0);
            itemconteiner.setLayoutParams(params);
            Picasso.with(mContext).load(R.drawable.trans).fit().into(photo);

        }
        else if (titel.contains(mContext.getString(R.string.OrderRequestes))
                ){
            nameTextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            nameTextView.setTextSize(17);
            nameTextView.setText(objectDrawerItem.getName());
            listItem.setEnabled(true);

        }   else if(titel.contains(mContext.getString(R.string.My_Favorites))){
            nameTextView.setTextSize(17);
            nameTextView.setText(objectDrawerItem.getName());
            listItem.setEnabled(true);
        }
        else {
            nameTextView.setTextSize(13);
            listItem.setEnabled(true);

            params.setMargins(25, 0, 1, 0);
            itemconteiner.setLayoutParams(params);
            Picasso.with(mContext).load(R.drawable.trans).fit().into(photo);
        }



        //////////////************************end************





        if (activiy instanceof NavigationDrawerSeler && position==0){
            phone= sharedPreferencesManager.getUser_Phoe();





        }


        return listItem;
    }


 }
