package com.example.ceramiczuhairkhalaf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.ceramiczuhairkhalaf.AppFace.HomeFragment;
import com.example.ceramiczuhairkhalaf.AppFace.MainFragment;

public class MainActivity extends AppCompatActivity {
    private FirebaseServices fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbs = FirebaseServices.getInstance();
        if(fbs.getAuth().getCurrentUser()==null)
        {
            goToMainFragment();
        }
        else
        {
            goToHomeFragment();
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
}