package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;

public class AddMealActivity extends AppCompatActivity {

    private static final int INGREDIENTS_RC = 1;

    private String mealId;
    private Meal meal;
    private List<Category> categories;
    private HashMap<String, Integer> selectedIngredients;

    private EditText name;
    private Spinner categorySpinner;
    private TextView ingredientsTxt;
    private Button ingredientsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.name = findViewById(R.id.add_meal_content_name);

        this.categories = App.db.categories().getAll();
        List<String> categoryNames = sortAndGetNames();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.categorySpinner = findViewById(R.id.add_meal_content_category_spinner);
        categorySpinner.setAdapter(adapter);

        this.ingredientsTxt = findViewById(R.id.add_meal_content_ingredients_title);
        this.ingredientsBtn = findViewById(R.id.add_meal_content_ingredients_btn);

        this.mealId = Objects.requireNonNull(getIntent().getExtras()).getString("mealId");
        if (mealId != null) { // update
            this.meal = App.db.meals().getById(mealId);
            name.setText(meal.getName());
            categorySpinner.setSelection(getCategoryPosition());

            this.selectedIngredients = new HashMap<>();
            List<IngredientMealJoin> joins = App.db.ingredientMealJoin().getIngredientsByMealId(meal.getId());
            for (IngredientMealJoin join : joins)
                selectedIngredients.put(join.getIngredientId(), join.getQuantity());

            updateIngredientsTxt();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter meal name", Toast.LENGTH_LONG).show();

                else {
                    String categoryId = categories.get(categorySpinner.getSelectedItemPosition()).getId();

                    if (mealId == null) { // insert
                        meal = new Meal(name.getText().toString(), categoryId);
                        App.db.meals().insert(meal);

                        if (selectedIngredients != null)
                            for (String key : selectedIngredients.keySet())
                                App.db.ingredientMealJoin().insert(
                                        new IngredientMealJoin(key, meal.getId(), selectedIngredients.get(key)));

                        finish();
                    } else { // update
                        meal.setName(name.getText().toString());
                        meal.setCategoryId(categoryId);
                        App.db.meals().update(meal);

                        List<IngredientMealJoin> joins = App.db.ingredientMealJoin().getIngredientsByMealId(meal.getId());
                        for (IngredientMealJoin join : joins)
                            App.db.ingredientMealJoin().delete(join);

                        for (String key : selectedIngredients.keySet())
                            App.db.ingredientMealJoin().insert(
                                    new IngredientMealJoin(key, meal.getId(), selectedIngredients.get(key)));

                        finish();
                    }
                }
            }
        });

    }

    private List<String> sortAndGetNames() {
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                //noinspection ComparatorMethodParameterNotUsed
                return c1.getOrder() <= c2.getOrder() ? -1 : 1;
            }
        });

        List<String> categoryNames = new ArrayList<>();
        for (Category cat : categories)
            categoryNames.add(cat.getName());

        return categoryNames;
    }

    private int getCategoryPosition() {
        for (int i=0; i<categories.size(); ++i) {
            if (categories.get(i).getId().equals(meal.getCategoryId()))
                return i;
        }
        return -1;
    }

    public void onSelectClick(View view) {
        if (App.db.ingredients().getAll().size() == 0)
            Snackbar.make(view, "No meal ingredients found! Please first add one.",
                    Snackbar.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(this, SelectMealIngredientsActivity.class);
            intent.putExtra("ingredients", selectedIngredients);
            startActivityForResult(intent, INGREDIENTS_RC);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INGREDIENTS_RC && resultCode == RESULT_OK) {
            this.selectedIngredients = (HashMap<String, Integer>) data.getSerializableExtra("ingredients");
            updateIngredientsTxt();
        }

    }

    private void updateIngredientsTxt() {
        int count = 0;
        for (String key : selectedIngredients.keySet()) {
            count += selectedIngredients.get(key);
        }
        this.ingredientsTxt.setText("Ingredients: " + count);
    }

}
