package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Task;

public class NewTaskActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private NumberPicker duration;
    private Task selectedTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.new_task_content_name);
        description = findViewById(R.id.new_task_content_description);
        duration = findViewById(R.id.new_task_content_duration);
        duration.setMinValue(0);
        duration.setMaxValue(999);

        this.selectedTask = (Task) getIntent().getExtras().getSerializable("task");

        if(selectedTask != null) { // update
            title.setText(selectedTask.getName());
            description.setText(selectedTask.getDescription());
            duration.setValue(selectedTask.getDurationMinutes());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTask == null) { // insert
                    if (title.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter task title", Toast.LENGTH_LONG).show();
                        return;

                    } else {
                        selectedTask = new Task(title.getText().toString(), 0, "0",
                                description.getText().toString(), duration.getValue());
                    }

                } else { // update
                    if (title.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter training title", Toast.LENGTH_LONG).show();
                        return;

                    } else {
                        selectedTask.setName(title.getText().toString());
                        selectedTask.setDescription(description.getText().toString());
                        selectedTask.setDurationMinutes(duration.getValue());
                    }
                }

                Intent intent = new Intent();
                intent.putExtra("task", selectedTask);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
