package com.example.myapplication;

import static com.example.myapplication.LogIn.login;
import static com.example.myapplication.LogIn.nowUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.RegisterBinding;
import com.example.myapplication.databinding.RegisterBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    static String SHARE_NAME = "SHARE_PREF";


    RegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 변수 객체 생성


        super.onCreate(savedInstanceState);

        binding = RegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.personalInfo.setMovementMethod(new ScrollingMovementMethod());

//        if (login) {
//            Intent intent = new Intent(Register.this, MyPage.class);
//            startActivity(intent);
//        }

        // 로그인 버튼을 눌렀을때
        binding.signInCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if radio 버튼 agree 가 true 일때 ㄹㅇ 넘어가도록
                // else toast 로 개인정보 동의 해달라는 메시지 길게?

                String name, number, address, ID, PW, PWAgain;

                name = binding.newUserName.getText().toString();
                number = binding.newUserPhoneNumber.getText().toString();
                address = binding.newUserAddr.getText().toString();
                ID = binding.newUserId.getText().toString();
                PW = binding.newUserPw.getText().toString();
                PWAgain = binding.newUserPwAgain.getText().toString();

                if (!binding.agreeButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "약관을 읽고 동의 해주세요.", Toast.LENGTH_SHORT).show();
                    binding.agreeButton.requestFocus();
                } else if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    binding.newUserName.requestFocus();
                } else if (address.equals("")) {
                    Toast.makeText(getApplicationContext(), "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    binding.newUserAddr.requestFocus();
                } else if (number.equals("")) { // 숫자 010 으로 시작안하고 11자리 아니면 안넘어가도록
                    Toast.makeText(getApplicationContext(), "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    binding.newUserPhoneNumber.requestFocus();
                } else if (!Pattern.matches("^010(\\d{4})(\\d{4})$", number)) {
                    // ^01(?:0|1[6-9])(?:\d{3}|\d{4})\d{4}$
                    Toast.makeText(getApplicationContext(), "올바른 전화번호가 아닙니다", Toast.LENGTH_SHORT).show();
                } else if (ID.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    binding.newUserId.requestFocus();
                } else if (ID.length() < 8) {
                    Toast.makeText(getApplicationContext(), "아이디는 8자 이상이어야 합니다", Toast.LENGTH_SHORT).show();
                } else if (getArrayListStringPref(ID).size() != 0) {
                    Toast.makeText(getApplicationContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                } else if (PW.equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    binding.newUserPw.requestFocus();
                } else if (!check_password(PW)) {
                    Toast.makeText(getApplicationContext(), "비밀번호는 영문 대소문자, 특수기호, 숫자가 적어도 하나씩 포함되어야 합니다.", Toast.LENGTH_SHORT).show();
                } else if(!PW.equals(PWAgain)){
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 모든 입력이 정상적일 때
                    ArrayList<String> list = new ArrayList<>();
                    list.add(binding.newUserId.getText().toString());
                    list.add(binding.newUserPw.getText().toString());
                    list.add(binding.newUserName.getText().toString());
                    list.add(binding.newUserPhoneNumber.getText().toString());
                    list.add(binding.newUserAddr.getText().toString());



                    setArrayListStringPref(String.valueOf(binding.newUserId.getText()), list);
                    // binding.getRoot().requestFocus();
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.\n로그인 화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(intent);
                }

            }
        });


    }

    boolean check_password(String password) {
        // 비밀번호 유효성 검사식1 : 숫자, 특수문자가 포함되어야 한다.
        String reg_symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])";
        // 비밀번호 유효성 검사식2 : 영문자 대소문자가 적어도 하나씩은 포함되어야 한다.
        String reg_alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])";

        // 정규표현식 컴파일
        Pattern pattern_symbol = Pattern.compile(reg_symbol);
        Pattern pattern_alpha = Pattern.compile(reg_alpha);

        // 문자 매칭
        Matcher matcher_symbol = pattern_symbol.matcher(password);
        Matcher matcher_alpha = pattern_alpha.matcher(password);

        // 매칭 결과 출력
        if (matcher_symbol.find() && matcher_alpha.find())
            return true;
        else
            return false;
    }

    // 프레퍼런스에서 데이터를 읽어오기
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

    private void setArrayListStringPref(String key, ArrayList<String> values) {
        SharedPreferences prefs = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) editor.putString(key, a.toString());
        else editor.putString(key, null);
        editor.apply();
    }
}
