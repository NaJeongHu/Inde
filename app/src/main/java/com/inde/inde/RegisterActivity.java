package com.inde.inde;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private static final int IMAGE_REQUEST = 0;
    private EditText mEtEmail, mEtPassword, mEtName, mEtNickname;
    private ImageView mIvPicture;
    private Button mBtnRegister;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

    }

    private void init() {
        mEtEmail = findViewById(R.id.et_email);
        mEtPassword = findViewById(R.id.et_password);
        mEtName = findViewById(R.id.et_name);
        mEtNickname = findViewById(R.id.et_nickname);
        mBtnRegister = findViewById(R.id.btn_register);
        mIvPicture = findViewById(R.id.iv_picture);

        mBtnRegister.setOnClickListener(this);
        mIvPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String strEmail = mEtEmail.getText().toString();
                String strPassword = mEtPassword.getText().toString();
                String strName = mEtName.getText().toString();
                String strNickname = mEtNickname.getText().toString();

                // todo : retrofit

                if (imageUri == null) {
                    Toast.makeText(this, "사진을 선택해주세요", Toast.LENGTH_LONG).show();
                } else {
                    // todo : 이메일 형식 및 기타 정보들 체크 필요
                    RegisterToServer(strEmail, strPassword, strNickname, strName);
                }
                break;

            case R.id.iv_picture:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMAGE_REQUEST);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST){
            if(resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageUri = data.getData();

                //set Image to mIvPicture
                if (imageUri != null) {
                    mIvPicture.setImageURI(imageUri);
                }
            } else if(resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void RegisterToServer(String email, String password, String nickname, String name) {

        MultipartBody.Part filePart = null;
        Bitmap img = null;

        //change Uri to Bitmap
        if(imageUri != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    img = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getContentResolver(), imageUri));
                } else {
                    img = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            img  = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        }

        try {

            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //create a file to write bitmap data
            File f = new File(this.getCacheDir(), "filename");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //write the bytes in file
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            filePart = MultipartBody.Part.createFormData("file",
                    f.getName(), RequestBody.create(MediaType.parse("image/*"), f));
        } catch (Exception e) {
            e.printStackTrace();
        }

        user user = new user(email, password, name, nickname);


        HashMap<String, user> student = new HashMap<String, user>();
        student.put("user", user);

        HashMap<String, MultipartBody.Part> student1 = new HashMap<String, MultipartBody.Part>();
        student1.put("email", filePart);

        RESTApi mRESTApi = RESTApi.retrofit.create(RESTApi.class);
        mRESTApi.register(student, filePart)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG, "joinRequest");

                        String test = response.headers().get("code");
                        Toast.makeText(RegisterActivity.this,"test : " + test , Toast.LENGTH_SHORT).show();
//
//                        if (test.equals("00")) {
//                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(RegisterActivity.this,"회원가입 실패" + test , Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                        Log.d("JoinActivity", throwable.getMessage());
                        Toast.makeText(RegisterActivity.this,"throwable : " + throwable , Toast.LENGTH_SHORT).show();
                    }
                });
    }

}