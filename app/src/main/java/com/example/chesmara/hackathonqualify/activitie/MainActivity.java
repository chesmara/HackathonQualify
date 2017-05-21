package com.example.chesmara.hackathonqualify.activitie;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chesmara.hackathonqualify.R;
import com.example.chesmara.hackathonqualify.dbase.DatabaseHelper;
import com.example.chesmara.hackathonqualify.dbase.model.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId())
        {

    //----------------------------------login----------------------------------------------------
            case (R.id.login_dialog_presed):
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.login_dialog);

                Button confirm = (Button) dialog.findViewById(R.id.confirm_login);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText email=(EditText) dialog.findViewById(R.id.user_email);
                        EditText password = (EditText) dialog.findViewById(R.id.user_password);

                    }
                });

    //-----------------------------------register------------------------------------------------
            case (R.id.register_dialog_presed):
                final Dialog regDialog = new Dialog(this);
                regDialog.setContentView(R.layout.register_dialog);

                Button create = (Button) regDialog.findViewById(R.id.create_user);
                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText userName = (EditText) regDialog.findViewById(R.id.user_name);
                        EditText userAdress = (EditText) regDialog.findViewById(R.id.user_adress);
                        EditText userEmail  = (EditText) regDialog.findViewById(R.id.user_email);
                        EditText userPassword = (EditText) regDialog.findViewById(R.id.user_password);

                            User user = new User();
                        user.setuName(userName.getText().toString());
                        user.setuAdress(userAdress.getText().toString());
                        user.setuEmail(userEmail.getText().toString());
                        user.setuPassword(userPassword.getText().toString());


                        try {
                            getDatabaseHelper().getmUserDao().create(user);

                            regDialog.dismiss();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }
                });

        }

        return super.onOptionsItemSelected(item);

    }

//--------------------------------------getHelper--------------------------------------------

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

}
