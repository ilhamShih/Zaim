package com.zaimutest777.zaim;


import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.zaimutest777.zaim.bottombar.BottomBarActivity;
import com.zaimutest777.zaim.helper.ForbiddenSession;
import com.zaimutest777.zaim.helper.SQLiteHandler;
import com.zaimutest777.zaim.helper.SessionManager;
import com.zaimutest777.zaim.model.ReplyFromServer;
import com.zaimutest777.zaim.interfaces.MarsPhotosDataService;
import com.zaimutest777.zaim.network.RetrofitInstance2;
import com.zaimutest777.zaim.politic.Politika;
import com.zaimutest777.zaim.sms.SmsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.view.View.GONE;



public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {
    public static  String   PACKAGENAME;

    /**домен сервера который определяет открывать дальше витрину или нет  check_link*/
    public static  String   CHECK_LINK    = "";
    /**ссылка куда передавать номера телефоны инструкция как передать sheet_link  */
    public static  String   SHEET_LINK    = "";
    /** Json где лежит весь контент:  картинки/текста/ссылки cards_link */
    public static String   CARDS_LINK     = "";
    /** Приват полиси который мы показываем пользователю privatepolicy */
    public static  String  PRIVATEPOLICY  = "";
    public static  int TIMER = 50;
    public String server_ok;
    public TextView text_view,text_check,text2;
    public CheckBox checkBox;
    public Button button;
    public ImageView Views;
    private SessionManager session;
    private ForbiddenSession forbidden;
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    Handler handler;
    Toolbar toolbar;

    SwipeRefreshLayout swipeRefreshLayout = null;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        handler = new Handler();
        progressDialog = new ProgressDialog(MainActivity.this);
        PACKAGENAME = getApplication().getPackageName();

        session = new SessionManager(getApplicationContext());
        forbidden = new ForbiddenSession(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Подтвердите ваше согласие");

        checkBox = (CheckBox) findViewById(R.id.check);
        button = (Button) findViewById(R.id.buttoms);
        button.setBackgroundResource(R.color.duration);
        button.setOnClickListener(this);
        text_view = (TextView) findViewById(R.id.text_view);
        text_check = (TextView) findViewById(R.id.text_check);
        text2 = (TextView) findViewById(R.id.text2);
        text2.setPaintFlags(text2.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        text2.setOnClickListener(this);
        Views = (ImageView) findViewById(R.id.View);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        checkBox.setChecked(true);


      /**---------------------------------- REFRESH------------------------------------------------------*/

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        forb();

     /**---------------------------------- CHECK_BOX--------------------------------------------------*/
        checkBox.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    button.setEnabled(true);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            swipeRefreshLayout.setRefreshing(true);
                            CheckByServer();
                        }
                    });
                } else {
                    button.setEnabled(false);
                    button.setBackgroundResource(R.color.duration);
                }

            }
        });
    }
    /**---------------------------------- VISIBILITY--------------------------------------------------*/

    public void setHideInterface(){
        checkBox.setVisibility(GONE);
        button.setVisibility(GONE);
        text_view.setVisibility(GONE);
        text_check.setVisibility(GONE);
        Views.setVisibility(GONE);
        text2.setVisibility(GONE);
        toolbar.setVisibility(GONE);
    }

    public void setShowInterface(){
        checkBox.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        text_view.setVisibility(View.VISIBLE);
        text_check.setVisibility(View.VISIBLE);
        Views.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
    }

              /**---------------------------------------------------------------*/
                      /**--------------------------------------------------*/
                                 /**-----------------------------*/
    /**------------------------------------- START-----------------------------------------------------*/

    public void forb(){

        if (!forbidden.isLoggedIn()){
            startProcessSynchronization();

        }else if (forbidden.isLoggedIn()){

            setShowInterface();
        }

    }



    public void startProcessSynchronization(){

        swipeRefreshLayout.setRefreshing(true);
        fillInConstants();

        if (!constantsСhecking()) {

            TIMER = TIMER + 50;
            setHideInterface();
            waitingForConstants();

        }else if (session.isLoggedIn()){
                      TIMER = TIMER + 50;
                if (!constantsСhecking()) {
                      fillInConstants();
                      setHideInterface();
                      waitingForConstants();
                }else{
                        Intent i = new Intent(getApplication(), BottomBarActivity.class);
                        startActivity(i);
                        finish();
                }

        }else {
            setShowInterface();
            swipeRefreshLayout.setRefreshing(false);
        }
    }
    /**------------------------------------------------------------------------------------------------------*/

    /**----------------------------------------REMOTE_CONFIG-------------------------------------------------*/


    public void fillInConstants(){
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(2).build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {

                        CHECK_LINK    = mFirebaseRemoteConfig.getString("check_link"    );
                        SHEET_LINK    = mFirebaseRemoteConfig.getString("sheet_link"    );
                        CARDS_LINK    = mFirebaseRemoteConfig.getString("cards_link"    );
                        PRIVATEPOLICY = mFirebaseRemoteConfig.getString("privatepolicy" );

                    }
                });
    }
    /**------------------------------------------------------------------------------------------------------*/

    /**------------------------------------REMOTE_CONFIG----------------------------------------------*/


    public boolean constantsСhecking(){
        boolean errors ;
        if (    CHECK_LINK.trim().length() == 0 &&
                SHEET_LINK.trim().length() == 0 &&
                CARDS_LINK.trim().length() == 0 &&
                PRIVATEPOLICY.trim().length() == 0 ) {
               errors  =    false;
        }else{ errors  =    true; }
        return errors;
    }
    /**------------------------------------------------------------------------------------------------------*/

    /**---------------------------------------REMOTE_CONFIG----------------------------------------------*/

    public void waitingForConstants(){
        handler.postDelayed(new Runnable() {
            public void run() {
                startProcessSynchronization();
            }
        }, TIMER);
    }

    /**------------------------------------------------------------------------------------------------------*/

    /**--------------------------------------------SMS_ACTIVITY-------------------------------------------*/

    public void goToSmsActivity(String serverOk){
        handler.postDelayed(new Runnable() {
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                Intent i = new Intent(getApplication(), SmsActivity.class);
                i.putExtra("server_ok",serverOk);
                startActivity(i);
                finish();
            }
        }, 1500);

    }

    /**------------------------------------------------------------------------------------------------------*/

    /**----------------------------------------CHECK_LINK SERVER--------------------------------------------*/

    public void CheckByServer(){
        final  String ANDROID_ID = Settings.Secure.getString(getApplicationContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
        button.findFocus();
        swipeRefreshLayout.setRefreshing(true);
       // TimeZone tz = TimeZone.getDefault();
       // String  tz_s = (tz.getDisplayName(false, TimeZone.SHORT));
        final String appName = getApplication().getPackageName();
        MarsPhotosDataService dataService = RetrofitInstance2.getRetrofitInstance2().create(MarsPhotosDataService.class);
        Call<ReplyFromServer> marsPhotosListCall = dataService
                .getMySyte(appName,ANDROID_ID);

        marsPhotosListCall.enqueue(new Callback<ReplyFromServer>() {
            @Override
            public void onResponse(Call<ReplyFromServer> call, Response<ReplyFromServer> response) {
                if (response.isSuccessful()) {

                    server_ok =  response.body().getFlag();
                    goToSmsActivity(server_ok);

                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    String errors =  "false";
                    goToSmsActivity(errors);
                    forbidden.setLogin(true);
                }
            }
            @Override
            public void onFailure(Call<ReplyFromServer> call, Throwable t) {
             //   Toast.makeText(getApplicationContext(), "Error during query execution", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    /**------------------------------------------------------------------------------------------------------*/

    /**--------------------------------------ON-CLICK----------------------------------------------------*/

    @Override
    public void onClick(final View view) {
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.text2:
                    Intent ii = new Intent(getApplication(), Politika.class);
                    startActivity(ii);
                    break;
                case R.id.buttoms:
                    if( CHECK_LINK.trim().length() == 0 ){
                        goToSmsActivity("errors");
                    }else{
                        CheckByServer();
                    }
                    break;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setEnabled(true);
                }
            }, 4000);
        }
    }
}
/**------------------------------------------------------------------------------------------------------*/