package com.zaimutest777.zaim.interfaces;


import com.zaimutest777.zaim.model.NavigatorList;
import com.zaimutest777.zaim.model.ReplyFromServer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Ilham Shihnazarow  on 9/7/2021
 */

public interface MarsPhotosDataService {

    @GET("{cards_link}")
    Call<NavigatorList> getMarsPhotoListCall(@Path("cards_link") String cards_link);

    @GET("/")
    Call<ReplyFromServer> getMySyte(
            @Query("packageid")String packageid,
            @Query("usserid")String usserid);
}
