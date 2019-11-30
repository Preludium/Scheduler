package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;
import pl.jansmi.scheduler.dbstructure.entities.Practice;

public class NewTrainingActivity extends AppCompatActivity {
    private static final int SELECT_DISCIPLINE_RC = 1;

    private EditText title;
    private EditText name;
    private NumberPicker duration;
    private Discipline selectedDiscipline;
    private Practice selectedPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.new_training_content_name);
        name = findViewById(R.id.new_training_content_discipline);
        duration = findViewById(R.id.new_training_content_duration);
        duration.setMaxValue(999);
        duration.setMinValue(0);
        selectedPractice = null;

        this.selectedPractice = (Practice) getIntent().getExtras().getSerializable("practice");

        if(selectedPractice != null) { // update
            title.setText(selectedPractice.getName());
            if (selectedPractice.getDisciplineId() != null) {
                selectedDiscipline = App.db.disciplines().getById(selectedPractice.getDisciplineId());
                name.setText(selectedDiscipline.getName());
            }
            duration.setValue(selectedPractice.getDuration());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPractice == null) { // insert
                    if (title.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter training title", Toast.LENGTH_LONG).show();
                        return;

                    } else {
                        selectedPractice = new Practice(title.getText().toString(), 0, "0",
                                duration.getValue(), selectedDiscipline == null ? null : selectedDiscipline.getId());
                    }

                } else { // update
                    if (title.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter training title", Toast.LENGTH_LONG).show();
                        return;

                    } else {
                        selectedPractice.setName(title.getText().toString());
                        selectedPractice.setDuration(duration.getValue());
                        selectedPractice.setDisciplineId(selectedDiscipline == null ? null : selectedDiscipline.getId());
                    }
                }

                Intent intent = new Intent();
                intent.putExtra("practice", selectedPractice);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void onDisciplineSelect(View view) {
        if (App.db.disciplines().getAll().isEmpty())
            Snackbar.make(view, "No disciplines found. Please first add one.", Snackbar.LENGTH_LONG).show();
        else
            startActivityForResult(new Intent(getApplicationContext(), NewTrainingSelectDisciplineActivity.class),
                    SELECT_DISCIPLINE_RC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_DISCIPLINE_RC && resultCode == RESULT_OK) {
            this.selectedDiscipline = (Discipline) data.getSerializableExtra("discipline");
            name.setText(selectedDiscipline.getName());
        }
    }
}
