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
import pl.jansmi.scheduler.dbstructure.entities.Tag;

public class AddTagActivity extends AppCompatActivity {

    private String tagId;
    private Tag tag;
    private EditText tagName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tagName = findViewById(R.id.tag_content_name);

        tagId = Objects.requireNonNull(getIntent().getExtras()).getString("tagId");
        if (tagId != null) { // update
            tag = App.db.tags().getById(tagId);
            tagName.setText(tag.getName().toString());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tagName.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter tag name", Toast.LENGTH_LONG).show();
                else {
                    if (tagId == null) { // insert
                        tag = new Tag(tagName.getText().toString());
                        App.db.tags().insert(tag);
                    } else { // update
                        tag.setName(tagName.getText().toString());
                        // tag.setFavour(1.f);
                        App.db.tags().update(tag);
                    }
                    finish();
                }
            }
        });
    }

}
