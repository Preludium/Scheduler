package pl.jansmi.scheduler.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.Database;

public class AddMealActivity extends AppCompatActivity {

    private String mealId; // to (potentially) update

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mealId = getIntent().getExtras().getString("mealId");
        if (mealId != null) { // update
            // TODO: set activity title to 'update' and fill activity with initial data
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

}
