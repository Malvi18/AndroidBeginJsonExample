package com.example.androidbeginjsonexample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment=new BlankFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.Frame_Layout,fragment)
                .commit();


    }
}
