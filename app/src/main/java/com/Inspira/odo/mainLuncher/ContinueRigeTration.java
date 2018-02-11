package com.Inspira.odo.mainLuncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.BuyerWithFacebook;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.sellerUi.SellerWithFacebook;

public class ContinueRigeTration extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    String user_name  ,email;
    Bundle bundle ;
    LocaleHelper localeHelper ;
    ImageView go_back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_rige_tration);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            user_name = bundle.getString("user_name");
            email= bundle.getString("email");
        }
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(ContinueRigeTration.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinueRigeTration.this,   LogInActivity.class);
                startActivity(intent);
                finish();

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    BuyerWithFacebook buperFragment = new BuyerWithFacebook();
                    Bundle args = new Bundle();
                    args.putString("user_name", user_name);
                    args.putString("email",email);
                     buperFragment.setArguments(args);
                    return buperFragment;
                case 1:
                    SellerWithFacebook sellerFragment = new SellerWithFacebook();
                    Bundle arg = new Bundle();
                    arg.putString("user_name", user_name);
                    arg.putString("email",email);
                    sellerFragment.setArguments(arg);
                    return sellerFragment;

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return  getString(R.string.Buyer);
                case 1:
                    return getString(R.string.Seller);
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ContinueRigeTration.this,   LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
