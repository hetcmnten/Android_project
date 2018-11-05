package com.teemo.xuantruong.android_project;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import com.teemo.xuantruong.android_project.entity.Source;
import com.teemo.xuantruong.android_project.fragments.FragmentHomePage;
import com.teemo.xuantruong.android_project.fragments.FragmentSources;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public TabLayout tabLayout;
    public AppBarLayout appBarLayout;
    public ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    public NavigationView ndrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Mappings();
    }

    public void Mappings(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ndrawer = (NavigationView) findViewById(R.id.nav_view);

//        todo
        View hView =  ndrawer.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.user_name);
        nav_user.setText("123323");

        ndrawer.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Bundle getFirstSource = getIntent().getExtras();
        ArrayList<Source> getSource = new ArrayList<>();
       // getSource = ( ArrayList<Source>)getFirstSource.getSerializable("FirstSource");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHomePage()).commit();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_setting:
                Intent intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_homepage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHomePage()).commit();
                break;
                // go login
            case  R.id.nav_account:
                Intent intent1 = new Intent(this.getApplicationContext(), LoginActivity.class);
                startActivity(intent1);
                break;
                default:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHomePage()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
