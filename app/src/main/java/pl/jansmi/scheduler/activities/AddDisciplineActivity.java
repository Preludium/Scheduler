package pl.jansmi.scheduler;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import pl.jansmi.scheduler.activities.DisciplinesActivity;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;

public class AddDisciplineActivity extends AppCompatActivity {

    private String disciplineId;
    private EditText name;
    private EditText kcalPerMinute;
    private DisciplinesActivity dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discipline);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kcalPerMinute = findViewById(R.id.add_discipline_content_KcalPerMinute);
        disciplineId = getIntent().getExtras().getString("disciplineId");
        name = findViewById(R.id.add_category_content_name);

        if (disciplineId != null) { // update
            Discipline discipline = App.db.disciplines().getById(disciplineId);
            name.setText(discipline.getName());
            kcalPerMinute.setText(discipline.getKcalPerMinute());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Discipline discipline = new Discipline(name.getText().toString());
                discipline.setFavour((float) 1.0);
                discipline.setKcalPerMinute(Integer.parseInt(kcalPerMinute.getText().toString()));

                if (disciplineId == null) {
                    App.db.disciplines().insert(discipline);
                    finish();
                }
                else {
                    App.db.disciplines().update(discipline);
                    finish();
                }
            }

        });
    }

}
