package com.inde.inde;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText mEtEmail, mEtPassword;
    private CardView mBtnLogin, cardview_login_underinfo;
    private TextView mTvJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init() {
        mEtEmail = findViewById(R.id.edit_login_email);
        mEtPassword = findViewById(R.id.edit_login_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mTvJoin = findViewById(R.id.btn_join);

        cardview_login_underinfo = findViewById(R.id.cardview_login_underinfo);

        mBtnLogin.setOnClickListener(this);
        mTvJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String strEmail = mEtEmail.getText().toString();
                String strPassword = mEtPassword.getText().toString();
                Login(strEmail, strPassword);
                break;

            case R.id.btn_join:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private void Login(String email, String password) {

        RESTApi mRESTApi = RESTApi.retrofit.create(RESTApi.class);
        mRESTApi.login(email, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG, "joinRequest");

                        String test = response.headers().get("code");
                        Toast.makeText(LoginActivity.this,"test : " + test , Toast.LENGTH_SHORT).show();
//
//                        if (test.equals("00")) {
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(LoginActivity.this,"회원가입 실패" + test , Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                        Log.d("JoinActivity", throwable.getMessage());
                        Toast.makeText(LoginActivity.this,"throwable : " + throwable , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void upload_dialog(View v) {
//        View dialogView = getLayoutInflater().inflate(R.layout.dialog_progress, null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//        builder.setView(dialogView);
//
//        alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        alertDialog.show();
//
//        animationView = dialogView.findViewById(R.id.lottie_progress);
//        animationView.playAnimation();
    }
}