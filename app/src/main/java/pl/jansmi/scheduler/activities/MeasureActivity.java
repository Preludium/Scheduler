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
import android.widget.TextView;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.MeasuresRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Measure;

public class MeasureActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private TextView infoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycler = findViewById(R.id.measures_content_recycler);
        infoBox = findViewById(R.id.measures_content_info_text);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewMeasureActivity.class);
                intent.putExtra("measure", (Measure) null);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        adapter = new MeasuresRecyclerViewAdapter(this);
        recycler.setAdapter(adapter);

        if (adapter.getItemCount() == 0)
            infoBox.setVisibility(View.VISIBLE);
        else
            infoBox.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
