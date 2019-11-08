package pl.jansmi.scheduler.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Category;

public class AddCategoryActivity extends AppCompatActivity {

    private String categoryId;
    private Category category;
    private EditText name;
    private NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.add_category_content_name);

        picker = findViewById(R.id.add_category_content_order_number);
        picker.setMinValue(0);
        picker.setMaxValue(23);

        categoryId = getIntent().getExtras().getString("categoryId");

        if (categoryId != null) { // update
            this.category = App.db.categories().getById(categoryId);
            name.setText(category.getName());
            picker.setValue(category.getOrder());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (categoryId == null) { // insert
                    category = new Category(name.getText().toString());
                    category.setOrder(picker.getValue());
                    App.db.categories().insert(category);
                    finish();
                }

                else { // update
                    category.setName(name.getText().toString());
                    category.setOrder(picker.getValue());
                    App.db.categories().update(category);
                    finish();
                }
            }

        });

    }

}
