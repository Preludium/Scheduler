package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.Session;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class SignupActivity extends AppCompatActivity {

    private EditText signup_login_box;
    private Button signup_btn;
    private EditText signup_password1_box;
    private EditText signup_password2_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.signup_btn = findViewById(R.id.signup_btn);
        this.signup_login_box = findViewById(R.id.signup_login_box);
        signup_password1_box = findViewById(R.id.signup_password1_box);
        signup_password2_box = findViewById(R.id.signup_password2_box);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signup_login_box.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Enter user name", Toast.LENGTH_LONG).show();
                else if(signup_password1_box.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG).show();
                else if(signup_password2_box.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Enter password confirmation", Toast.LENGTH_LONG).show();
                else if(App.db.users().getAll().contains(App.db.users().getByName(signup_login_box.getText().toString())))
                    Snackbar.make(v, "Given login is occupied.", Snackbar.LENGTH_LONG).show();
                else if(signup_password1_box.getText().toString().length() < 5)
                    Snackbar.make(v, "Given password is too short. Minimum 5 characters.", Snackbar.LENGTH_LONG).show();
                else if(!signup_password1_box.getText().toString().equals(signup_password2_box.getText().toString()))
                    Snackbar.make(v, "Given passwords differ.", Snackbar.LENGTH_LONG).show();
                else {
                    User usr = new User(signup_login_box.getText().toString(), signup_password1_box.getText().toString());
                    App.db.users().insert(usr);
                    Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

}
