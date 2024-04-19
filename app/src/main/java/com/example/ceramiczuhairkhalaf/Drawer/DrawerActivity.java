package com.example.ceramiczuhairkhalaf.Drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ceramiczuhairkhalaf.AppFace.HomeFragment;
import com.example.ceramiczuhairkhalaf.AppFace.BathSanitaryFragment;
import com.example.ceramiczuhairkhalaf.FirebaseServices;
import com.example.ceramiczuhairkhalaf.MainActivity;
import com.example.ceramiczuhairkhalaf.R;
import com.example.ceramiczuhairkhalaf.ShowData.AllBathSanitaryFragment;
import com.example.ceramiczuhairkhalaf.ShowData.AllTilesFragment;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private FirebaseServices fbs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fbs = FirebaseServices.getInstance();
        setContentView(R.layout.activity_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
        else if (itemId == R.id.nav_BathSanitary) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BathSanitaryFragment()).commit();
        } else if (itemId == R.id.nav_logout) {
            fbs.getAuth().signOut();
            finish();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (itemId == R.id.nav_AllTiles) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllTilesFragment()).commit();
        } else if (itemId == R.id.nav_AllBathSanitaries) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllBathSanitaryFragment()).commit();
        }



/*
        switch (item.getItemId()){
            case R.id.nav_homw:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment()).commit();
                break;

            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new CartFragment()).commit();
                break;
        }

 */
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}