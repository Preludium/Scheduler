package pl.jansmi.scheduler.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.activities.DisciplinesActivity;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;

public class AddDisciplineActivity extends AppCompatActivity {

    private String disciplineId;
    private Discipline discipline;
    private EditText name;
    private EditText kcalPerMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discipline);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kcalPerMinute = findViewById(R.id.add_discipline_content_KcalPerMinute);
        disciplineId = Objects.requireNonNull(getIntent().getExtras()).getString("disciplineId");
        name = findViewById(R.id.add_discipline_content_name);

        if (disciplineId != null) { // update
            this.discipline = App.db.disciplines().getById(disciplineId);
            name.setText(discipline.getName());
            kcalPerMinute.setText(discipline.getKcalPerMinute());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Name is missing!", Toast.LENGTH_LONG).show();

                // OK when kcalPerMinute is missing (then simply kcalPerMinute = 0)

                else {
                    if (disciplineId == null) {
                        discipline = new Discipline(name.getText().toString());
                        discipline.setKcalPerMinute(Integer.parseInt(kcalPerMinute.getText().toString()));
                        discipline.setFavour(1.f);
                        App.db.disciplines().insert(discipline);
                        finish();
                    }

                    else {
                        discipline.setName(name.getText().toString());
                        discipline.setKcalPerMinute(Integer.parseInt(kcalPerMinute.getText().toString()));
                        // TODO: think of favour changing, while refreshing data
                        // discipline.setFavour(1.f);
                        App.db.disciplines().update(discipline);
                        finish();
                    }

                }
            }

        });
    }

}
