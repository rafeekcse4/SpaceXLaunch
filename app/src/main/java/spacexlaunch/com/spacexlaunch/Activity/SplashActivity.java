package spacexlaunch.com.spacexlaunch.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import spacexlaunch.com.spacexlaunch.R;
import spacexlaunch.com.spacexlaunch.Utils.Constant;
import spacexlaunch.com.spacexlaunch.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        final String userName = prefs.getString("username", null);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Util.isStringNullOrNot(userName)){
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
                    finish();
                }

            }
        },2000);
    }
}
