package spacexlaunch.com.spacexlaunch.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import spacexlaunch.com.spacexlaunch.R;
import spacexlaunch.com.spacexlaunch.Utils.Constant;

public class ProfilePage extends AppCompatActivity {
    TextView txt_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //noinspection ConstantConditions
        getSupportActionBar().setTitle("Profile");

        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
         String userName = prefs.getString("username", null);

        txt_profile=findViewById(R.id.id_txt_profile);
        txt_profile.setText(userName);
    }
}
