package com.zaimutest777.zaim.main;

import android.util.Log;

import com.zaimutest777.zaim.interfaces.MainContract;
import com.zaimutest777.zaim.model.NavigatorList;
import com.zaimutest777.zaim.interfaces.MarsPhotosDataService;
import com.zaimutest777.zaim.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zaimutest777.zaim.MainActivity.CARDS_LINK;
import static com.zaimutest777.zaim.helper.ParseURL.getPathUrl;


public class IntractorImpl implements MainContract.Intractor {
    @Override
    public void getMarsPhotosArrayList(int i ,final OnFinishedListener onFinishedListener) {
        String  BASE_URL = CARDS_LINK;
        String BASE_URL_NEW = "";
        try {
            BASE_URL_NEW = getPathUrl(BASE_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MarsPhotosDataService dataService = RetrofitInstance.getRetrofitInstance().create(MarsPhotosDataService.class);
        Call<NavigatorList> marsPhotosListCall = dataService.getMarsPhotoListCall(BASE_URL_NEW);

        marsPhotosListCall.enqueue(new Callback<NavigatorList>() {
            @Override
            public void onResponse(Call<NavigatorList> call, Response<NavigatorList> response) {

                if(i==1){
                    /** CreditCardsFrag  */

                   /* ArrayList<NavigatorList> lis = response.body().getCreditcards();
                    ReplyFromServer mReplyFromServer = lis.get(lis.size() - 1);*/
                    onFinishedListener.onFinished(response.body().getCreditcards());

                }else if(i==2){
                    /** DebitCardsFrag  */
                    onFinishedListener.onFinished(response.body().geDebitcards());

                }else if(i==3){
                    /** RasrochkaFrag  */
                    onFinishedListener.onFinished(response.body().getRasrochka());

                } else if(i==4){
                    /** KredityFragment  */
                    onFinishedListener.onFinished(response.body().getCredits());

                }else if(i==5){
                    /** ZaimyFragment */
                    onFinishedListener.onFinished(response.body().getLoans());

        }
            }
            @Override
            public void onFailure(Call<NavigatorList> call, Throwable error) {
               Log.d("Retrofit: ", "Something");
                onFinishedListener.onFailure(error);

            }
        });
    }

}
