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

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Target;

public class NewTargetActivity extends AppCompatActivity {

    private Target selectedTarget;
    private EditText deadline;
    private EditText title;
    private DatePickerDialog.OnDateSetListener deadlineDateSetListener;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_target);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        deadline = findViewById(R.id.new_target_content_deadline);
        title = findViewById(R.id.new_target_content_name);
        date = new Date();

        selectedTarget = (Target) getIntent().getSerializableExtra("target");
        if(selectedTarget != null) {
            title.setText(selectedTarget.getName());
            if(selectedTarget.getDeadline() != null) {
                date = selectedTarget.getDeadline();
                @SuppressLint("DefaultLocale")
                String dateHandler = String.format("%d/%d/%d", date.getDate(), date.getMonth() + 1, date.getYear());
                deadline.setText(dateHandler);
            }
        }

        deadlineDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = new Date(year, month, day);
                deadline.setText(String.format("%s/%s/%s", day, month + 1, year));
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (title.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter title", Toast.LENGTH_LONG).show();
                }
                else {

                    if (selectedTarget == null) {    //insert
                        if (deadline.getText().toString().equals(""))
                            selectedTarget = new Target(title.getText().toString(), null, App.session.getUserId());
                        else
                            selectedTarget = new Target(title.getText().toString(), date, App.session.getUserId());
                        App.db.targets().insert(selectedTarget);
                    } else {  //update
                        selectedTarget.setName(title.getText().toString());
                        selectedTarget.setDeadline(date);
                        selectedTarget.setAchieved(false);
                        App.db.targets().update(selectedTarget);
                    }

                    finish();
                }
            }
        });
    }

    public void onDeadlineSelectClick(View view) {
        Calendar cal = Calendar.getInstance();
        int year, month, day;

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(NewTargetActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                deadlineDateSetListener, year, month, day);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
