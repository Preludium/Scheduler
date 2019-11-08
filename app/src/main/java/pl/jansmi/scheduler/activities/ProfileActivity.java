package pl.jansmi.scheduler.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class ProfileActivity extends AppCompatActivity {

    private User user;
    private EditText name;
    private EditText birthday;
    private Spinner sex;
    private EditText weight;
    private EditText height;
    private EditText kcalPerDayTarget;
    private Date date;
    private DatePickerDialog.OnDateSetListener birthdayDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        user = App.db.users().getById(App.session.getUserId());
        name = findViewById(R.id.login_content);
        birthday = findViewById(R.id.birthday_content);
        sex = findViewById(R.id.sex_content);
        weight = findViewById(R.id.weight_content);
        height = findViewById(R.id.height_content);
        kcalPerDayTarget = findViewById(R.id.kcalPerDayTarget_content);

        name.setText(user.getName());

        if(user.getBirthday() != null) {
            date = user.getBirthday();
            birthday.setText(dateFormat.format(date));
        }

//        if(user.getWeight() != null)
//            weight.setText(String.valueOf(user.getWeight()));
//
//        if(user.getHeight() != null)
//            height.setText(String.valueOf(user.getHeight()));

//        if(user.getKcalPerDayTarget() != null)
//            kcalPerDayTarget.setText(String.valueOf(user.getKcalPerDayTarget()));

        birthday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
//                if(date != null) {
//                    cal.set(Calendar.DAY_OF_MONTH, date.getDay());
//                    cal.set(Calendar.MONTH, date.getMonth());
//                    cal.set(Calendar.YEAR, date.getYear());
//                }
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ProfileActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        birthdayDateSetListener, year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        birthdayDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = new Date(year, month, day);
                birthday.setText(dateFormat.format(date));
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
