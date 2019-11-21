package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Study;
import pl.jansmi.scheduler.dbstructure.entities.Subject;
import pl.jansmi.scheduler.fragments.SelectMealsFragment;

public class NewStudyingActivity extends AppCompatActivity {
    private static final int SELECTSUBJECT_RC = 1;

    private Study study;
    private EditText title;
    private EditText name;
    private EditText desc;
    private NumberPicker duration;
    private Subject selectedSubject;
    private Study selectedStudy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_studying);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.new_studying_content_title_edittext);
        name = findViewById(R.id.new_studying_content_subject_name);
        desc = findViewById(R.id.new_studying_content_description);
        duration = findViewById(R.id.new_studying_content_duration);
        duration.setMaxValue(999);
        duration.setMinValue(0);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: pass data back to AddArrangementActivity
                if (study == null) { // insert
                    selectedStudy = new Study(title.getText().toString(), desc.getText().toString(), 0,
                            "0", duration.getValue(), name.getText().toString());
                } else { // update
                    // TODO: modify current selectedStudy with data from forms
                    // selectedStudy.setTitle()
                    // selectedStudy.setDesc() etc.
                }

                Intent intent = new Intent();
                intent.putExtra("study", study);
                setResult(RESULT_OK, intent);

                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO: IF updating Study, read data passed from AddArrangementActivity
        this.selectedStudy = (Study) getIntent().getExtras().get("study");

        if(selectedStudy != null) { // update
            title.setText(selectedStudy.getTitle());
            name.setText(App.db.subjects().getById(selectedStudy.getSubjectId()).getName());
            desc.setText(String.valueOf(selectedStudy.getDesc()));
            duration.setValue(selectedStudy.getDuration());
        }

    }

    public void onSubjectSelect(View view) {
        // TODO: open NewStudyingSelectSubjectActivity with recycler containing subjects
         startActivityForResult(new Intent(getApplicationContext(), NewStudyingSelectSubjectActivity.class), SELECTSUBJECT_RC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECTSUBJECT_RC && resultCode == RESULT_OK) {
            this.selectedSubject = (Subject) data.getSerializableExtra("subject");
            updateSubjectName();
        }
    }

    private void updateSubjectName() {
        name.setText(selectedSubject.getName());
    }
}
