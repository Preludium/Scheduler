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

import java.util.Objects;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SelectTaskRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Task;

public class SelectTaskActivity extends AppCompatActivity {

    private Task selectedTask;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tasks);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recycler = findViewById(R.id.select_task_content_recycler);
        this.selectedTask = (Task) getIntent().getSerializableExtra("task");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = ((SelectTaskRecyclerViewAdapter)
                        Objects.requireNonNull(recycler.getAdapter())).getSelectedTask();
                Intent intent = new Intent(getApplicationContext(), NewInvitationActivity.class);
                intent.putExtra("task", task);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter = new SelectTaskRecyclerViewAdapter(this, selectedTask);
        recycler.setAdapter(adapter);
    }

}
