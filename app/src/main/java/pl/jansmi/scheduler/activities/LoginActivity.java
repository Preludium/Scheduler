package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.Session;
import pl.jansmi.scheduler.dbstructure.Database;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.db = Database.build(getApplicationContext());

        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button login_btn = findViewById(R.id.signin_btn);
        TextView signup_text = findViewById(R.id.signup_text);
        final EditText login_box = findViewById(R.id.login_box);
        // EditText password_box = findViewById(R.id.password_box);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if user exists of a given name and password AND open new Session
                String name = login_box.getText().toString();
                User fetched = App.db.users().getByName(name);

                if (fetched != null) {
                    App.session = new Session(fetched.getId());
                    startActivity(new Intent(getApplicationContext(), ArrangementsActivity.class));
                }

                else {
                    Toast.makeText(getApplicationContext(), "No user of a given name found!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        App.db.close();
        // App.session.terminate();
    }

}
