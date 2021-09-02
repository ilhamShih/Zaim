package com.zaimutest777.zaim.main;

import com.zaimutest777.zaim.interfaces.MainContract;
import com.zaimutest777.zaim.model.MapNavigatorList;

import java.util.ArrayList;

/**
 * Created by Ilham Shihnazarow  on 9/7/2021
 */

public class MainPresenterImpl implements MainContract.Presenter, MainContract.Intractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.Intractor intractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.Intractor intractor){
        this.mainView = mainView;
        this.intractor = intractor;
    }


    @Override
    public void onDestroy() {
        this.mainView = null;
    }

    @Override
    public void requestDataFromServer(int i) {
        if(i==1){
        intractor.getMarsPhotosArrayList(1,this);
        }else if(i==2){
         intractor.getMarsPhotosArrayList(2,this);
        } else if(i==3){
         intractor.getMarsPhotosArrayList(3,this);
        } else if(i==4){
        intractor.getMarsPhotosArrayList(4,this);
        } else if(i==5){
         intractor.getMarsPhotosArrayList(5,this);
    }
    }

    @Override
    public void onFinished(ArrayList<MapNavigatorList> mapNavigatorListArrayList) {
        if(this.mainView != null){
            mainView.setDataToRecyclerView(mapNavigatorListArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(this.mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
