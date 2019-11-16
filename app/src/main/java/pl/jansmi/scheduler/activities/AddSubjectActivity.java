package pl.jansmi.scheduler.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Subject;

public class AddSubjectActivity extends AppCompatActivity {

    private EditText name;
    private String subjectId;
    private Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.subject_content_name);

        subjectId = Objects.requireNonNull(getIntent().getExtras()).getString("subjectId");

        if(subjectId != null) {
            subject = App.db.subjects().getById(subjectId);
            name.setText(subject.getName());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter subject name", Toast.LENGTH_LONG).show();
                else
                {
                    if (subjectId == null) { //insert
                        subject = new Subject(name.getText().toString());
                        App.db.subjects().insert(subject);
                    } else { //update
                        subject.setName(name.getText().toString());
                        //subject.setFavour(1.f);
                        App.db.subjects().update(subject);
                    }
                    finish();
                }
            }
        });
    }

}
