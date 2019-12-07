package pl.jansmi.scheduler.activities;

import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;

public class AddIngredientActivity extends AppCompatActivity {

    private String ingredientId;
    private Ingredient ingredient;
    private EditText name;
    private EditText quantity;
    private Spinner unit;
    private EditText kcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.add_ingredient_name);
        quantity = findViewById(R.id.add_ingredient_quantity);

        unit = findViewById(R.id.add_ingredient_unit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.ingredient_units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit.setAdapter(adapter);

        kcal = findViewById(R.id.add_ingredient_kcal);

        ingredientId = Objects.requireNonNull(getIntent().getExtras()).getString("ingredientId");

        if (ingredientId != null) { // update
            this.ingredient = App.db.ingredients().getById(ingredientId);

            name.setText(ingredient.getName());
            quantity.setText(String.valueOf(ingredient.getQuantity()));

            if (ingredient.getUnit().equals(Ingredient.UNIT_NONE))
                unit.setSelection(0);
            else if (ingredient.getUnit().equals(Ingredient.UNIT_G))
                unit.setSelection(1);
            else
                unit.setSelection(2);

            kcal.setText(String.valueOf(ingredient.getKcal()));
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter ingredient name", Toast.LENGTH_LONG).show();
                else if (quantity.getText().toString().isEmpty() || Integer.parseInt(quantity.getText().toString()) <= 0)
                    Toast.makeText(getApplicationContext(), "Enter appropriate ingredient quantity", Toast.LENGTH_LONG).show();
                else if (!kcal.getText().toString().isEmpty() && Integer.parseInt(kcal.getText().toString()) <= 0)
                    Toast.makeText(getApplicationContext(), "Kcal must not be negative", Toast.LENGTH_LONG).show();
                else {
                    if (ingredientId == null) { // insert
                        ingredient = new Ingredient(name.getText().toString());
                        ingredient.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        ingredient.setUnit(unit.getSelectedItem().toString());

                        if (!kcal.getText().toString().isEmpty())
                            ingredient.setKcal(Integer.parseInt(kcal.getText().toString()));

                        App.db.ingredients().insert(ingredient);
                    } else { // update
                        ingredient.setName(name.getText().toString());
                        ingredient.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        ingredient.setUnit(unit.getSelectedItem().toString());

                        if (!kcal.getText().toString().isEmpty())
                            ingredient.setKcal(Integer.parseInt(kcal.getText().toString()));

                        //ingredient.setFavour(1.f);
                        App.db.ingredients().update(ingredient);
                    }
                    finish();
                }
            }
        });
    }

}
