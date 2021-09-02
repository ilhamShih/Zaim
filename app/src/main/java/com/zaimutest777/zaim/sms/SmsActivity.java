package com.zaimutest777.zaim.sms;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zaimutest777.zaim.MainActivity;
import com.zaimutest777.zaim.aplications.MyApplication;
import com.zaimutest777.zaim.R;
import com.zaimutest777.zaim.helper.MyNotificationManager;
import com.zaimutest777.zaim.helper.SQLiteHandler;
import com.zaimutest777.zaim.helper.SessionManager;
import com.zaimutest777.zaim.politic.Politika;

import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

import static com.zaimutest777.zaim.MainActivity.SHEET_LINK;

public class SmsActivity extends AppCompatActivity  implements  View.OnClickListener {

    ProgressDialog progressDialog;
    Button button;
    TextView edt_code, edt_phone;
    private SessionManager session;
    private SQLiteHandler db;
    UUID uuid;
    public String uuid_str, server_ok_error;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        uuid_str = uuid.randomUUID().toString();
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        Intent intent = getIntent();
        server_ok_error = intent.getStringExtra("server_ok");


        if (session.isLoggedIn()) {
            // Пользователь уже вошел в систему.Возвращаем на MainActivity
            onBackPressed();
        }
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Подтверждение телефона");
        edt_code = (TextView) findViewById(R.id.edt_code);
        edt_phone= (TextView) findViewById(R.id.edt_phone);
        edt_phone.setText("+7");
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
        progressDialog = new ProgressDialog(SmsActivity.this);
        button.setBackgroundResource(R.color.refresh_progress_2);

    }





    @Override
    public void onClick(final View view) {
        synchronized (view) {
            view.setEnabled(false);
            view.setBackgroundResource(R.color.duration);
            switch (view.getId()) {
                case R.id.btn:
                    String mobile = edt_phone.getText().toString().trim();
                    if (mobile.length() <= 3 ) {
                        Toast.makeText(getApplicationContext(), "В ведите больше символов", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        if(server_ok_error.equals("true")) {
                            sendPhoneNumber(server_ok_error);
                        }else{

                        }

                    }
                    break;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setEnabled(true);
                    view.setBackgroundResource(R.color.refresh_progress_2);
                }
            }, 8000);
        }
    }


    public void sendPhoneNumber(String servers_res){

        String phone = edt_phone.getText().toString();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SHEET_LINK, new Response.Listener<String>() {
            @Override
            public void onResponse(final String ServerResponse) {

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        createNotifications(servers_res, server_ok_error);
                    }
                }, 3500);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }})
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("number", phone);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SmsActivity.this);
        requestQueue.add(stringRequest);
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }



    public void createNotifications(String phone,String servers_res){

        if(servers_res.equals("true")){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", importance);
                mChannel.setDescription("Description");
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mNotificationManager.createNotificationChannel(mChannel);

            }

            MyNotificationManager.getInstance(this).displayNotification("Авторизация", "CODE : "+ uuid_str);

            setCode();
            db.addUser(phone);
            session.setLogin(true);
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }

    }

public void setCode(){
    edt_code.setText(uuid_str);
}

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

}