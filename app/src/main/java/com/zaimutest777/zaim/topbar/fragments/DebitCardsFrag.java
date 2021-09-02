package com.zaimutest777.zaim.topbar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaimutest777.zaim.webview.Detail2;
import com.zaimutest777.zaim.webview.DetailActivity;
import com.zaimutest777.zaim.R;
import com.zaimutest777.zaim.adapter.GeneralFragmentsAdapter;
import com.zaimutest777.zaim.main.IntractorImpl;
import com.zaimutest777.zaim.interfaces.MainContract;
import com.zaimutest777.zaim.main.MainPresenterImpl;
import com.zaimutest777.zaim.interfaces.RecyclerItemClickListener;
import com.zaimutest777.zaim.model.MapNavigatorList;

import java.util.ArrayList;

public class DebitCardsFrag extends Fragment implements MainContract.MainView{

    MainContract.Presenter presenter;
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<MapNavigatorList> marsPhotoArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main22, container, false);


        presenter = new MainPresenterImpl(this, new IntractorImpl());
        presenter.requestDataFromServer(2);


        progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        recyclerView = view.findViewById(R.id.recyclerViewMarsPhoto);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());


        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.VISIBLE);
        GeneralFragmentsAdapter generalFragmentsAdapter = new GeneralFragmentsAdapter(marsPhotoArrayList,
                recyclerItemClickListener);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generalFragmentsAdapter);



        return view;

    }

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(String url) {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("url",url);
            startActivity(intent);
        }


        @Override
        public void onItem2Click(String description, String url, String logo, String title) {
            Intent intent = new Intent(getActivity(), Detail2.class);
            intent.putExtra("description",description);
            intent.putExtra("url",url);
            intent.putExtra("logoImage",logo);
            intent.putExtra("titles",title);
            startActivity(intent);

        }
    };



    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<MapNavigatorList> marsPhotoArrayList) {
        GeneralFragmentsAdapter generalFragmentsAdapter = new GeneralFragmentsAdapter(marsPhotoArrayList,
                recyclerItemClickListener);
        recyclerView.setAdapter(generalFragmentsAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(),
                "Connection Error" + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }



}
