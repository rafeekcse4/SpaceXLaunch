package spacexlaunch.com.spacexlaunch.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import spacexlaunch.com.spacexlaunch.R;
import spacexlaunch.com.spacexlaunch.Utils.Constant;
import spacexlaunch.com.spacexlaunch.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    //Instantiate the edit text and button
    EditText edit_userName,edit_pwd;
    Button btn_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        intializeView();

        //Setting the button click listner
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
            }
        });

    }

    //Validate the username and password
    private void loginValidation() {
        String userName=edit_userName.getText().toString();
        String pwd=edit_pwd.getText().toString();

        if (Util.isStringNullOrNot(userName)){
            edit_userName.setError("Please Enter Name");
        }else if (Util.isStringNullOrNot(pwd)){
            edit_pwd.setError("Please Enter Password");
        }else{
            SharedPreferences.Editor editor = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("username", userName+"");
            editor.putString("pwd",pwd+"" );
            editor.apply();
            startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
            finish();
        }

    }

    //Setting the view in login screen component
    private void intializeView() {
        edit_userName=findViewById(R.id.input_cName);
        edit_pwd=findViewById(R.id.input_pwd);
        btn_login=findViewById(R.id.btn_login);
    }
}
