package com.androiddeft.loginandregistration;

/**
 * Created by codecaiine on 07/04/2018.
 */
import com.androiddeft.loginandregistration.R;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class RegisterActivity extends Activity {

    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password ;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder;
    String finalResult ;
    String HttpURL = "http://eduafric.000webhostapp.com/api/UserRegistration.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Assign Id'S
        First_Name = (EditText)findViewById(R.id.etFirstname);
        Last_Name = (EditText)findViewById(R.id.etLastname);

        Email = (EditText)findViewById(R.id.etUserMail);
        Password = (EditText)findViewById(R.id.etPassword);

        register = (Button)findViewById(R.id.btnRegister);
        log_in = (Button)findViewById(R.id.btnRegisterLogin);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(F_Name_Holder,L_Name_Holder, EmailHolder, PasswordHolder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(RegisterActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();


        if(TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String password){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(RegisterActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(RegisterActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("f_name",params[0]);

                hashMap.put("L_name",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("password",params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(F_Name,L_Name,email,password);
    }
}

