package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.Session;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button signup_btn = findViewById(R.id.signup_btn);
        EditText signup_login_box = findViewById(R.id.signup_login_box);
        //EditText signup_password1_box = findViewById(R.id.signup_password1_box);
        //EditText signup_password2_box = findViewById(R.id.signup_password2_box);


        signup_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // check if passwords are equal and add new user to database
                String name = signup_login_box.getText().toString();
                User usr = new User(name);

                App.db.users().insert(usr);
                App.session = new Session(usr.getId());

                startActivity(new Intent(getApplicationContext(), ArrangementsActivity.class));
                finish();
            }
        });

    }

}
