package com.example.firstproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    EditText upload1, upload2;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initializeStuff();

        setSupportActionBar(toolbar);

        setUpNavigationView(navigationView);

        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameContent,new DashboardFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_item_dashboard);
        //setTitle("title");


        upload1 = findViewById(R.id.upload1);
        upload2 = findViewById(R.id.upload2);
    }

    void initializeStuff(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar =  findViewById(R.id.toolbar);
        toolbar =  findViewById(R.id.toolbar);
        navigationView =  findViewById(R.id.navigationDrawer);
    }


    @Nullable
    private void setUpNavigationView(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Fragment selectedFragment = selectDrawerItem(menuItem);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.frameContent, selectedFragment).commit();
                        navigationView.setCheckedItem(menuItem.getItemId());
                        setTitle(menuItem.getTitle());
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public Fragment selectDrawerItem(MenuItem menuItem){
        Fragment fragment = null;
        switch(menuItem.getItemId()) {
            case R.id.nav_item_dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.nav_item_regis:
                fragment = new TabFragment();
                break;
            case R.id.nav_item_tutorial:
                fragment = new TutorialFragment();
                break;
            case R.id.nav_item_faq:
                fragment = new FaqFragment();
                break;
        }
        return fragment;
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.drawer_open,R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}