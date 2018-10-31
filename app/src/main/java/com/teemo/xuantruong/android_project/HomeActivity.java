package com.teemo.xuantruong.android_project;


import android.app.Activity;
import android.app.Fragment;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.fragments.FragmentHomePage;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static int SPLASH_TIME_OUT= 2000;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView ndrawer;
    private FragmentHomePage FraHomePage;
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
        ndrawer.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                if (TextUtils.isEmpty(newText)) {
//                    adapter.filter("");
//                    listView.clearTextFilter();
//                } else {
//                    adapter.filter(newText);
//                }
                Bundle bundle = new Bundle();
                bundle.putString("edtSearch", "From Homepage");

                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_setting:
                Toast.makeText(HomeActivity.this,"SETTING FRAGMENT",Toast.LENGTH_LONG).show();
                Log.d("setting ","select setting fragment");
                break;
            case R.id.nav_homepage:
                FraHomePage = new FragmentHomePage();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,FraHomePage).commit();
                break;
                // go login
            case  R.id.nav_account:
                Intent intent = new Intent(this.getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;

            default:
                FraHomePage = new FragmentHomePage();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FraHomePage).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
