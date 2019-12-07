package pl.jansmi.scheduler.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class ProfileActivity extends AppCompatActivity {

    private EditText name;
    private EditText birthday;
    private EditText weight;
    private EditText height;
    private EditText kcalPerDayTarget;
    private Date date;

    private User user;
    private final String[] sexList = {"male", "female"};
    private boolean gender;
    private DatePickerDialog.OnDateSetListener birthdayDateSetListener;
    private String userNameInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = new Date();

        user = App.db.users().getById(App.session.getUserId());
        name = findViewById(R.id.login_content);
        birthday = findViewById(R.id.birthday_content);
        birthday.setClickable(true);
        birthday.setFocusableInTouchMode(false);
        Spinner sex = findViewById(R.id.sex_content);
        weight = findViewById(R.id.weight_content);
        height = findViewById(R.id.height_content);
        kcalPerDayTarget = findViewById(R.id.kcalPerDayTarget_content);

        name.setText(user.getName());
        userNameInitial = user.getName();

        if(user.getBirthday() != null) {
            date = user.getBirthday();
            @SuppressLint("DefaultLocale")
            String dateHandler = String.format("%d/%d/%d", date.getDate(), date.getMonth() + 1, date.getYear());
            birthday.setText(dateHandler);
        }
        else
            birthday.setText("");

        if(user.getWeight() != 0.0f)
            weight.setText(String.valueOf(user.getWeight()));

        if(user.getHeight() != 0)
            height.setText(String.valueOf(user.getHeight()));

        if(user.getKcalPerDayTarget() != 0)
            kcalPerDayTarget.setText(String.valueOf(user.getKcalPerDayTarget()));

        gender = user.isSex();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sexList);
        sex.setAdapter(adapter);

        if(gender)
            sex.setSelection(1);
        else
            sex.setSelection(0);

        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 0:
                        gender = false;
                        break;

                    case 1:
                        gender = true;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        birthdayDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = new Date(year, month, day);
                birthday.setText(String.format("%s/%s/%s", day, month + 1, year));
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!name.getText().toString().equals(userNameInitial) && name.getText() != null) {
                    List<User> users;
                    users = App.db.users().getAll();
                    for(User userTemp : users) {
                        if (name.getText().toString().equals(userTemp.getName())) {
                            Toast.makeText(getApplicationContext(), "That user name is occupied", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    user.setName(name.getText().toString());
                }


                if(!birthday.getText().toString().equals(""))
                    user.setBirthday(date);

                user.setSex(gender);

                if(!weight.getText().toString().equals("")) {
                    if (Float.valueOf(weight.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "Weight cannot be negative.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    user.setWeight(Float.valueOf(weight.getText().toString()));
                }

                if(!height.getText().toString().equals("")) {
                    if (Integer.valueOf(height.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "Height cannot be negative.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    user.setHeight(Integer.valueOf(height.getText().toString()));
                }

                if(!kcalPerDayTarget.getText().toString().equals("")) {
                    if (Integer.valueOf(kcalPerDayTarget.getText().toString()) < 0) {
                        Toast.makeText(getApplicationContext(), "kcalPerDayTarget cannot be negative.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    user.setKcalPerDayTarget(Integer.valueOf(kcalPerDayTarget.getText().toString()));
                }

                App.db.users().update(user);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_invitations:
                invitations();
                return true;

            case R.id.action_measures:
                measures();
                return true;

            case R.id.action_targets:
                targets();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void targets() {
        startActivity(new Intent(getApplicationContext(), TargetActivity.class));
    }

    private void measures() {
        startActivity(new Intent(getApplicationContext(), MeasureActivity.class));
    }

    private void invitations() {
        startActivity(new Intent(getApplicationContext(), InvitationsActivity.class));
    }

    public void onBirthdayClick(View view) {
        Calendar cal = Calendar.getInstance();
        int year, month, day;

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(ProfileActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                birthdayDateSetListener, year, month, day);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}
