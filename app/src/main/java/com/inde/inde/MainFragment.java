package com.inde.inde;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements View.OnClickListener{

    private ImageView iv_main_gosearch;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_IndeBand adapter;
    private ArrayList<IndeBand> indeBands = new ArrayList<>();

    public MainFragment() { }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        iv_main_gosearch = view.findViewById(R.id.iv_main_gosearch);
        iv_main_gosearch.setOnClickListener(this);
//        iv_main_gosearch.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void connectToAdapter() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (arr.isEmpty() == false || arr.size() != 0) {
//                            Collections.sort(arr,new Filtering_for_ganada());
                            adapter = new RecyclerViewAdapter_MySell(getActivity(), arr);
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.iv_main_gosearch:
//                Intent intent1 = new Intent(getActivity(), BookActivity.class);
//                startActivity(intent1);
                break;

        }
    }
}