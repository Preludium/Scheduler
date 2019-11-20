package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Study;

public class NewStudyingActivity extends AppCompatActivity {

    private Study study;

    private EditText name;
    private EditText desc;
    // etc.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_studying);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: pass data back to AddArrangementActivity
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: IF updating Study, read data passed from AddArrangementActivity
    }

    public void onSubjectSelect(View view) {
        // TODO: open NewStudyingSelectSubjectActivity with recycler containing subjects
        // startActivityForResult();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO: fetch selected subject from NewStudyingSelectSubjectActivity and fill text box

    }
}
