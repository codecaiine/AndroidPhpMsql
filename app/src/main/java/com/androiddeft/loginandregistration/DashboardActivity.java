package com.androiddeft.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.androiddeft.loginandregistration.R;

public class DashboardActivity extends AppCompatActivity {

    Button LogOut, courses;
    TextView EmailShow, NameShow;
    String EmailHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        LogOut = (Button)findViewById(R.id.btnLogout);
        EmailShow = (TextView)findViewById(R.id.email);
        NameShow = (TextView)findViewById(R.id.name);

        courses = (Button)findViewById(R.id.btncourse);


        Intent intent = getIntent();
        EmailHolder = intent.getStringExtra(LoginActivity.UserEmail);
        EmailShow.setText(EmailHolder);




        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });



        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);

                startActivity(intent);

                Toast.makeText(DashboardActivity.this, "Log Out Successfully", Toast.LENGTH_LONG).show();


            }
        });
    }
}
