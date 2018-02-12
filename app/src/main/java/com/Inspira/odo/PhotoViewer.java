package com.Inspira.odo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoViewer extends AppCompatActivity {
    private ImageView singlephot;
    private RecyclerView recyclerView;
    private ViewPager glareyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);


        singlephot=(ImageView)findViewById(R.id.singlephoto);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        glareyView = (ViewPager) findViewById(R.id.glareyView);


        Bundle bundle=getIntent().getExtras();
        assert bundle != null;
        String photos[]= bundle.getStringArray("photos");
        assert photos != null;
        if (photos.length==1){
            singlephot.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            Picasso.with(this).load(photos[0]).fit().into(singlephot);

        }else {
            singlephot  .setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(this,3));
            Customadapter customadapter=new Customadapter(photos);
            recyclerView.setAdapter(customadapter);
            ViewPageradepter adapter = new ViewPageradepter(PhotoViewer.this, photos);
            glareyView.setAdapter(adapter);
        }


    }

    @Override
    public void onBackPressed() {
        if (recyclerView.getVisibility() == View.GONE && glareyView.getVisibility() == View.VISIBLE)
        {
            glareyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        else {
            finish();
        }
        super.onBackPressed();

    }

    class Customadapter extends RecyclerView.Adapter<Customadapter.viewholere>{
        String[] photo;
        Customadapter(String [] phot){
            this.photo=phot;
        }
        @Override
        public Customadapter.viewholere onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.photoview,parent,false);
            viewholere view =new viewholere(row);
            return view;
        }

        @Override
        public void onBindViewHolder(Customadapter.viewholere holder, final int position) {

            Picasso.with(PhotoViewer.this).load(photo[position]).fit().into(holder.imagee);
            holder.imagee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    glareyView.setVisibility(View.VISIBLE);
                    glareyView.setCurrentItem(position);
                    Picasso.with(PhotoViewer.this).load(photo[position]).fit().into(singlephot);
                    recyclerView.setVisibility(View.GONE);
                }
            });

        }

        @Override
        public int getItemCount() {
            return photo.length;
        }
        class viewholere extends  RecyclerView.ViewHolder{
            private ImageView imagee;
            public viewholere(View itemView) {
                super(itemView);
                imagee=(ImageView)itemView.findViewById(R.id.Imge_item);
            }
        }
    }


    class ViewPageradepter extends PagerAdapter {
        private String[] photosarray;
        private Context context;
        private int lastPosition = -1;

        ViewPageradepter(Context context, String[] photos) {
            this.photosarray = photos;
            this.context = context;
        }

        @Override
        public int getCount() {
            return photosarray.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            View view = layoutInflater.inflate(R.layout.fullscreenphoto, container, false);
            ImageView photo = (ImageView) view.findViewById(R.id.fullsernrow);
            Picasso.with(context).load(photosarray[position]).into(photo);
            setAnimation(view, position, android.R.anim.slide_out_right);
            container.addView(view);
            return view;
        }

        private void setAnimation(View viewToAnimate, int position, int anmition) {
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(context, anmition);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }

}

