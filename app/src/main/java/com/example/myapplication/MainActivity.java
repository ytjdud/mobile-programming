package com.example.myapplication;

import static com.example.myapplication.LogIn.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] appleItem = {"AirPod Max","Airpods Pro","Apple Watch","iMac",
                "iPad","iPhone","MacBook Pro"};
        int[] appleItemImage = {R.drawable.airpodmax,R.drawable.airpodspro,R.drawable.applewatch,
                R.drawable.imac,R.drawable.ipad,R.drawable.iphone,R.drawable.macbookpro};

        GridAdapter gridAdapter = new GridAdapter(MainActivity.this,appleItem,appleItemImage);
        binding.gridView.setAdapter(gridAdapter);


        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this,"You Clicked on "+ appleItem[position],Toast.LENGTH_SHORT).show();

            }
        });

        binding.userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login) {
                    Toast.makeText(getApplicationContext(), "회원 정보 페이지로 이동합니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MyPage.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), UserInfoButton.class);
                    startActivity(intent);
                }
            }
        });

    }
}