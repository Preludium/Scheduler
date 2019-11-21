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

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SelectSubjectRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Subject;

public class NewStudyingSelectSubjectActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_studying_select_subject);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recycler = findViewById(R.id.new_studying_select_subject_content_recycler);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subject selectedSubject = ((SelectSubjectRecyclerViewAdapter)
                        recycler.getAdapter()).getSelectedSubject();

                Intent intent = new Intent();
                intent.putExtra("subject", (Serializable) selectedSubject);
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

        adapter = new SelectSubjectRecyclerViewAdapter(this);
        recycler.setAdapter(adapter);

    }

}
