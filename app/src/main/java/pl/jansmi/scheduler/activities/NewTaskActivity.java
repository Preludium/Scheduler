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
                    if(title.getText() == null)
                        title.setText("");
                    if(description.getText() == null)
                        description.setText("");
                    selectedTask = new Task(title.getText().toString(), 0, "0",description.getText().toString(), duration.getValue());
                } else { // update
                    selectedTask.setName(title.getText().toString());
                    selectedTask.setDurationMinutes(duration.getValue());
                    selectedTask.setDescription(description.getText().toString());
                }

                Intent intent = new Intent();
                intent.putExtra("task", selectedTask);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
