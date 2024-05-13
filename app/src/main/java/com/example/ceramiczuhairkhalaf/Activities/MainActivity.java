package com.example.ceramiczuhairkhalaf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.ceramiczuhairkhalaf.AppFace.HomeFragment;
import com.example.ceramiczuhairkhalaf.AppFace.MainFragment;
import com.example.ceramiczuhairkhalaf.Classes.FirebaseServices;
import com.example.ceramiczuhairkhalaf.Activities.DrawerActivity;
import com.example.ceramiczuhairkhalaf.R;

public class MainActivity extends AppCompatActivity {
    private FirebaseServices fbs;
    private ProgressBar progressBar;
    private FrameLayout overlay;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbs = FirebaseServices.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.pbDownloadImageMainActivity);
        overlay = findViewById(R.id.frameLayoutMain);
        progressBar.setVisibility(View.GONE);
        if(fbs.getAuth().getCurrentUser()==null)
        {
            goToMainFragment();
        }
        else
        {
            //goToHomeFragment();
            gotoDrawerActivity();
        }
    }

    private void goToMainFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new MainFragment());
        ft.commit();
    }
    private void goToHomeFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new HomeFragment());
        ft.commit();
    }
    private void gotoDrawerActivity() {
        Intent i = new Intent(this, DrawerActivity.class);
        startActivity(i);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public FrameLayout getOverlay() {
        return overlay;
    }
}