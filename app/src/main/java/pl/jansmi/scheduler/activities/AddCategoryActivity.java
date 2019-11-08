package pl.jansmi.scheduler.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Category;

public class AddCategoryActivity extends AppCompatActivity {

    private String categoryId;
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

        categoryId = Objects.requireNonNull(getIntent().getExtras()).getString("categoryId");

        if (categoryId != null) { // update
            Category category = App.db.categories().getById(categoryId);
            name.setText(category.getName());
            picker.setValue(category.getOrder());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Category category = new Category(name.getText().toString());
                category.setOrder(picker.getValue());

                if (categoryId == null) {
                    App.db.categories().insert(category);
                    finish();
                }

                else {
                    App.db.categories().update(category);
                    finish();
                }
            }

        });

    }

}
