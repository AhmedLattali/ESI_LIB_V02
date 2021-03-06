package com.devmobile.cs2.esi_lib;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.devmobile.cs2.esi_lib.AsyncTasks.GetUserByNomEtMPasseTask;


public class LoginActivity extends FragmentActivity implements OnTabChangeListener, OnPageChangeListener {

    private TabsPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSavedUser("logedIn")) {

            Intent intent = new Intent(this, com.devmobile.cs2.esi_lib.ListeLivres.class);
            startActivity(intent);
            finish();

        } else {

            setContentView(R.layout.activity_login);

            mViewPager = (ViewPager) findViewById(R.id.viewpager);

            // Tab Initialization
            initialiseTabHost();
            mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
            // Fragments and ViewPager Initialization

            mViewPager.setAdapter(mAdapter);
            mViewPager.setOnPageChangeListener(LoginActivity.this);

        }
    }

    // Method to add a TabHost
    private static void AddTab(LoginActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec) {
        tabSpec.setContent(new MyTabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    // Manages the Tab changes, synchronizing it with Pages
    public void onTabChanged(String tag) {
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    // Manages the Page changes, synchronizing it with Tabs
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        int pos = this.mViewPager.getCurrentItem();
        this.mTabHost.setCurrentTab(pos);
    }

    @Override
    public void onPageSelected(int arg0) {
    }


    // Tabs Creation
    private void initialiseTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        // TODO Put here your Tabs
        LoginActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Sign-In").setIndicator("Sign-Ip"));
        LoginActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Sign-Un").setIndicator("Sign-Up"));
        LoginActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("About").setIndicator("About"));

        mTabHost.setOnTabChangedListener(this);
    }

    public void onClickLogin(View v) {

        String pseudo = ((EditText) findViewById(R.id.editText)).getText().toString();
        String mot_de_passe = ((EditText) findViewById(R.id.editText2)).getText().toString();
        Intent intent = new Intent(this, com.devmobile.cs2.esi_lib.ListeLivres.class);
        GetUserByNomEtMPasseTask auth = new GetUserByNomEtMPasseTask(this, intent, pseudo, mot_de_passe);
        finish();
        auth.execute();
        //startActivity(intent);
    }

    public boolean getSavedUser(String category) {

        return getBaseContext().getSharedPreferences("User", Context.MODE_PRIVATE).getBoolean(category, false);
    }





}