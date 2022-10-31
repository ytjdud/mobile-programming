package com.example.myapplication;

import static com.example.myapplication.LogIn.login;
import static com.example.myapplication.LogIn.nowUser;
import static com.example.myapplication.Register.SHARE_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.MyPageBinding;
import com.example.myapplication.databinding.RegisterBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MyPage extends AppCompatActivity {
    MyPageBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 변수 객체 생성

        super.onCreate(savedInstanceState);

        binding = MyPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<String> list = getArrayListStringPref(nowUser);
        binding.newUserId.setText(list.get(0));
        binding.newUserPw.setText(list.get(1));
        binding.newUserName.setText(list.get(2));
        binding.newUserPhoneNumber.setText(list.get(3));
        binding.newUserAddr.setText(list.get(4));

        binding.toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyPage.this, "상품 목록 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyPage.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public ArrayList<String> getArrayListStringPref(String key) { // (ID)
        SharedPreferences prefs = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try{
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }
}
