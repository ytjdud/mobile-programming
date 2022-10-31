package com.example.myapplication;

import static com.example.myapplication.Register.SHARE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.databinding.LogInBinding;
import com.example.myapplication.databinding.RegisterBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    static public boolean login = false;
    static EditText idInput, pwInput;
    static String nowUser;

    LogInBinding binding;
    RegisterBinding bindingR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = LogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idInput = (EditText) findViewById(R.id.idInput); // 아이디
        pwInput = (EditText) findViewById(R.id.pwInput); // 비밀번호

        if (login) {
            ArrayList<String> list = getArrayListStringPref(bindingR.newUserId.getText().toString());
            idInput.setText(list.get(0));
            pwInput.setText(list.get(1));
        }

        // 로그인 버튼
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = getArrayListStringPref(binding.idInput.getText().toString());
                if (list.size() != 0 && list.get(1).equals(pwInput.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                    nowUser = binding.idInput.getText().toString();
                    login = true;
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 회원가입 버튼
        binding.toSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = false;
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });

        // 비회원 보기
        binding.toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = false;
                Intent intent = new Intent(LogIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public ArrayList<String> getArrayListStringPref(String key) {
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
