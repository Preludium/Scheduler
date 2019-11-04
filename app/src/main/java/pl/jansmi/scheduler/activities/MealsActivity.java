package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pl.jansmi.scheduler.R;

public class MealsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMealActivity.class);
                intent.putExtra("mealId", (String) null); // no update
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ingredients:
                ingredients();
                return true;

            case R.id.action_categories:
                categories();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void categories() {
        startActivity(new Intent(getApplicationContext(), CategoriesActivity.class));
    }

    private void ingredients() {
        //startActivity(new Intent(getApplicationContext(), IngredientsActivity.class));
    }

}
