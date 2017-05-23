package com.example.chesmara.hackathonqualify.activitie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.chesmara.hackathonqualify.R;
import com.example.chesmara.hackathonqualify.dbase.DatabaseHelper;
import com.example.chesmara.hackathonqualify.dbase.model.Article;
import com.example.chesmara.hackathonqualify.dbase.model.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;
//-----------------------------------------------------------------------------------------
//---------------------------user data and articles----------------------------------------
//-----------------------------------------------------------------------------------------

public class DetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private User u;
    private static final String ARTICLE_KEY_DETAIL="article key detail";

    private EditText uName;
    private EditText uAdress;
    private EditText uEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        int userConnection = getIntent().getExtras().getInt(MainActivity.USER_ID);

        //------------------------------USER info--------------------------------------
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

        //-----------------------------user article list---------------------------------

       final  ListView userArticlesList = (ListView) findViewById(R.id.user_articles_list);

        List<Article> ArtList= null;
        try {
            ArtList = getDatabaseHelper().getmArticleDao().queryBuilder()
                    .where()
                    .eq(Article.FIELD_NAME_USER, u.getuId())
                    .query();

            ListAdapter artadapter = new ArrayAdapter<>(this,R.layout.list_item, ArtList);
            userArticlesList.setAdapter(artadapter);

            userArticlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Article ar= (Article) userArticlesList.getItemAtPosition(position);
                    Intent intent = new Intent(DetailActivity.this,ArticleActivity.class);
                    intent.putExtra(ARTICLE_KEY_DETAIL, ar.getaId());
                    startActivity(intent);
                }
            });




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
