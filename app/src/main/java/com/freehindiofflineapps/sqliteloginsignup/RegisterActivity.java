package com.freehindiofflineapps.sqliteloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputName, inputPassword, inputEmail, inputAddress, inputMobileNo;
     private RadioGroup radioGroupGender;
    private RadioButton rdMale, rdFemale;
    private Button btnRegister;
    private TextView loginHere;
    private DatabaseHelper databaseHelper;
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = (EditText) findViewById(R.id.input_username);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputAddress = (EditText) findViewById(R.id.input_address);
        inputMobileNo = (EditText) findViewById(R.id.input_mobileno);
        radioGroupGender = (RadioGroup) findViewById(R.id.gender);
        rdMale = (RadioButton) findViewById(R.id.male);
        rdFemale = (RadioButton) findViewById(R.id.female);
        btnRegister = (Button) findViewById(R.id.btn_register);
        loginHere = (TextView) findViewById(R.id.login_here);
        databaseHelper = new DatabaseHelper(this);

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.male) {
                    gender = "Male";
                } else if (i == R.id.female) {
                    gender = "Female";
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputName.getText().toString().trim();
                String pass = inputPassword.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String address = inputAddress.getText().toString().trim();
                String mobile = inputMobileNo.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    inputName.setError("Please enter username");
                    inputName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("Please enter email");
                    inputEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    inputAddress.setError("Please enter address");
                    inputAddress.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("Please enter email");
                    inputEmail.requestFocus();
                    return;
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    inputEmail.setError("Enter valid email");
                    inputEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    inputPassword.setError("Enter valid  password");
                    inputPassword.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    inputAddress.setError("Enter valid address");
                    inputAddress.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    inputMobileNo.setError("Please enter username");
                    inputMobileNo.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    inputPassword.setError("Please enter username");
                    inputPassword.requestFocus();
                    return;
                }
                else {
                    long val = databaseHelper.adduser(username, pass, email, mobile, address, gender);
                    if (val > 0) {
                        Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                        inputName.setText("");
                        inputPassword.setText("");
                        inputEmail.setText("");
                        inputMobileNo.setText("");
                        inputAddress.setText("");
                        radioGroupGender.clearCheck();

                        Intent movelogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(movelogin);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Invalid Registeration Credentials", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}

