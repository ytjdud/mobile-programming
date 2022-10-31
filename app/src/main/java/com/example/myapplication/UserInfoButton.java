package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.UserInfoButtonBinding;


public class UserInfoButton extends AppCompatActivity {
    UserInfoButtonBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = UserInfoButtonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.okToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserInfoButton.this, "회원 정보 페이지로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserInfoButton.this, Register.class);
                startActivity(intent);
            }
        });

        binding.noToGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserInfoButton.this, "상품 목록 페이지로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserInfoButton.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
