package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.MealIngredientsRecyclerViewAdapter;

public class SelectMealIngredientsActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private MealIngredientsRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meal_ingredients);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recycler = findViewById(R.id.select_meal_ingredients_content_recycler);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> counts = ((MealIngredientsRecyclerViewAdapter) recycler.getAdapter()).getCounts();
                Intent intent = new Intent();
                intent.putExtra("counts", (Serializable) counts);
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

        adapter = new MealIngredientsRecyclerViewAdapter(this);
        recycler.setAdapter(adapter);

    }
}
