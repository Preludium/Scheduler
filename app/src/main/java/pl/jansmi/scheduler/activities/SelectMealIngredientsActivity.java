package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.MealIngredientsRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;

public class SelectMealIngredientsActivity extends AppCompatActivity {

    private MaterialSearchView searchView;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meal_ingredients);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recycler = findViewById(R.id.select_meal_ingredients_content_recycler);
        this.searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((MealIngredientsRecyclerViewAdapter)adapter).updateQuery(newText);
                return true;
            }
        });

//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//                //Do some magic
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                //Do some magic
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Integer> selectedIngredients = ((MealIngredientsRecyclerViewAdapter)
                        recycler.getAdapter()).getSelectedIngredients();

                Intent intent = new Intent();
                intent.putExtra("ingredients", selectedIngredients);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.snippet_search_ingredient, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        adapter = new MealIngredientsRecyclerViewAdapter(this,
                (HashMap<String, Integer>) getIntent().getExtras().getSerializable("ingredients"));
        recycler.setAdapter(adapter);

    }
}
