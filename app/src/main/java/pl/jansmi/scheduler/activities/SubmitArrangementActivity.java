package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SubmitArrangementRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Tag;

public class SubmitArrangementActivity extends AppCompatActivity {

    private EditText title;
    private TextView infoBox;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private Arrangement arrangement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_arrangement);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.title = findViewById(R.id.submit_arrangement_content_title);
        this.infoBox = findViewById(R.id.submit_arrangement_content_info_text);

        arrangement = (Arrangement) getIntent().getSerializableExtra("arrangement");

        if (arrangement != null)
            this.title.setText(arrangement.getName());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter arrangement title", Toast.LENGTH_LONG).show();
                    return;
                }

                List<Tag> selectedTags = ((SubmitArrangementRecyclerViewAdapter) adapter).getSelectedTags();
                Intent intent = new Intent();
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("tags", (Serializable) selectedTags);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        recycler = findViewById(R.id.submit_arrangement_content_recycler);
        adapter = new SubmitArrangementRecyclerViewAdapter(this, arrangement);
        recycler.setAdapter(adapter);

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        if (adapter.getItemCount() == 0)
            infoBox.setVisibility(View.VISIBLE);
        else
            infoBox.setVisibility(View.INVISIBLE);
    }
}
