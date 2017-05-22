package com.example.chesmara.hackathonqualify.activitie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.chesmara.hackathonqualify.R;
import com.example.chesmara.hackathonqualify.dbase.DatabaseHelper;
import com.example.chesmara.hackathonqualify.dbase.model.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class DetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private User u;

    private EditText uName;
    private EditText uAdress;
    private EditText uEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        int userConnection = getIntent().getExtras().getInt(MainActivity.ARTICLE_KEY);

        try {
            u=getDatabaseHelper().getmUserDao().queryForId(userConnection);

            uName=(EditText) findViewById(R.id.user_name_detail);
            uAdress=(EditText) findViewById(R.id.user_adress_detail);
            uEmail=(EditText) findViewById(R.id.user_email_detail);

            uName.setText(u.getuName());
            uAdress.setText(u.getuAdress());
            uEmail.setText(u.getuEmail());


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
