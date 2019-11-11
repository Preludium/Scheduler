package pl.jansmi.scheduler.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.Database;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Meal;

public class AddMealActivity extends AppCompatActivity {

    private String mealId;
    private Meal meal;
    //private List<Category> categories;

    private EditText name;
    private Spinner category;
    private TextView ingredientsTxt;
    private Button ingredientsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.add_meal_content_name);

        // TODO: fill spinner with categories
        /*this.categories = App.db.categories().getAll();
        List<String> categoryNames = sortAndGetNames();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category = findViewById(R.id.add_meal_content_category_spinner);
        category.setAdapter(adapter);*/

        ingredientsTxt = findViewById(R.id.add_meal_content_ingredients_title);
        ingredientsBtn = findViewById(R.id.add_meal_content_ingredients_btn);

        mealId = Objects.requireNonNull(getIntent().getExtras()).getString("mealId");
        if (mealId != null) { // update
            this.meal = App.db.meals().getById(mealId);
            name.setText(meal.getName());
            // TODO: think, how to fetch adequate category position in spinner
            //category.setSelection();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mealId == null) { // insert
                    // TODO: insert data to db
                    finish();
                }

               else { // update
                    // TODO: update data to db
                    finish();
                }
            }
        });

    }

    /*private List<String> sortAndGetNames() {
        return null;
    }*/

    public void onSelectClick(View view) {

    }
}
