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

import java.io.Serializable;
import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SubmitArrangementRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Tag;

public class SubmitArrangementActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_arrangement);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycler = findViewById(R.id.submit_arrangement_content_recycler);
        adapter = new SubmitArrangementRecyclerViewAdapter(this);
        recycler.setAdapter(adapter);

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Tag> selectedTags = ((SubmitArrangementRecyclerViewAdapter) adapter).getSelectedTags();
                Intent intent = new Intent();
                intent.putExtra("tags", (Serializable) selectedTags);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
