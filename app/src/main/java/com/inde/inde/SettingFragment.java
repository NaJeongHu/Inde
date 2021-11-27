package com.inde.inde;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


public class SettingFragment extends Fragment implements View.OnClickListener {

    private LinearLayout linear_auth,linear_reservation,linear_notice;

    //dialog UI
    private LinearLayout linear_call;
    private Button btn_okay;

    private Boolean auth;

    public SettingFragment() {
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        linear_auth = view.findViewById(R.id.linear_auth);
        linear_reservation = view.findViewById(R.id.linear_reservation);
        linear_notice = view.findViewById(R.id.linear_notice);


        linear_auth.setOnClickListener(this);
        init();
        return view;
    }

    private void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_auth:
                auth_dialog(v);
                break;
        }
    }


    public void auth_dialog(View v) {

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_auth, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
        alertDialog.getWindow().setAttributes(params);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        linear_call = dialogView.findViewById(R.id.linear_call);
        btn_okay = dialogView.findViewById(R.id.btn_okay_dialog_auth);

        linear_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:01077395570")));
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}