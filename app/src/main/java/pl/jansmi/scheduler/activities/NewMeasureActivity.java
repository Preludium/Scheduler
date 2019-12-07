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

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Measure;

public class NewMeasureActivity extends AppCompatActivity {
    private EditText weight;
    private EditText measureDate;
    private NumberPicker water;
    private Measure selectedMeasure;
    private Date date;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_measure);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weight = findViewById(R.id.new_measure_content_weight);
        measureDate = findViewById(R.id.new_measure_content_date);
        water = findViewById(R.id.new_measure_content_water);

        water.setMaxValue(99);
        water.setMinValue(0);

        selectedMeasure = (Measure) getIntent().getSerializableExtra("measure");

        if(selectedMeasure != null) {
            if(selectedMeasure.getWeight() != 0.0f)
                weight.setText(String.format("%.2f", selectedMeasure.getWeight()));
            water.setValue(selectedMeasure.getWater());
            date = selectedMeasure.getDate();
            String dateHandler = String.format("%d/%d/%d", date.getDate(), date.getMonth() + 1, date.getYear());
            measureDate.setText(dateHandler);
        }
        else {
            date = null;
        }

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = new Date(year, month, day);
                measureDate.setText(String.format("%s/%s/%s", day, month + 1, year));
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (date == null) {
                    Toast.makeText(getApplicationContext(), "Enter date", Toast.LENGTH_LONG).show();
                }
                else {
                    if (selectedMeasure == null) {    //insert
                        selectedMeasure = new Measure(date, App.session.getUserId(), water.getValue(),
                                (weight.getText().toString().equals("")) ? 0.0f : Float.valueOf(weight.getText().toString()) );
                        App.db.measures().insert(selectedMeasure);
                    } else {  //update
                        selectedMeasure.setWeight((!weight.getText().toString().equals("")) ? Float.valueOf(weight.getText().toString()) : 0.0f);
                        selectedMeasure.setDate(date);
                        selectedMeasure.setWater(water.getValue());
                        App.db.measures().update(selectedMeasure);
                    }
                    finish();
                }
            }
        });
    }

    public void onDateSelect(View view) {
        Calendar cal = Calendar.getInstance();
        int year, month, day;

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(NewMeasureActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener, year, month, day);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
