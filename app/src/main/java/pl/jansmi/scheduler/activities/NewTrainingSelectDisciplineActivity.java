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
import pl.jansmi.scheduler.adapters.SelectDisciplineRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;

public class NewTrainingSelectDisciplineActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training_select_discipline);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recycler = findViewById(R.id.new_training_select_discipline_content_recycler);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Discipline selectedDiscipline = ((SelectDisciplineRecyclerViewAdapter)
                        Objects.requireNonNull(recycler.getAdapter())).getSelectedDiscipline();

                Intent intent = new Intent();
                intent.putExtra("discipline", selectedDiscipline);
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

        adapter = new SelectDisciplineRecyclerViewAdapter(this);
        recycler.setAdapter(adapter);
    }
}
