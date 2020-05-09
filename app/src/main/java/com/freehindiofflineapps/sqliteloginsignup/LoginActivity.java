package com.freehindiofflineapps.sqliteloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button login;
    TextView register;
    private EditText inputName, inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputName = (EditText) findViewById(R.id.input_name);
        inputPassword = (EditText) findViewById(R.id.input_password);
        register = (TextView) findViewById(R.id.register_now);
        login = (Button) findViewById(R.id.btn_login);
        databaseHelper = new DatabaseHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = inputName.getText().toString().trim();
                String pass = inputPassword.getText().toString().trim();
                Boolean res = databaseHelper.checkuser(user, pass);

                if (TextUtils.isEmpty(user)) {
                    inputName.setError("Please enter username");
                    inputName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    inputPassword.setError("Please enter password");
                    inputPassword.requestFocus();
                    return;
                }
                else{
                    if (res == true) {


                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        intent.putExtra("NAME", inputName.getText().toString());
                        intent.putExtra("EMAIL", inputPassword.getText().toString());


                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                    }                }

            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}
