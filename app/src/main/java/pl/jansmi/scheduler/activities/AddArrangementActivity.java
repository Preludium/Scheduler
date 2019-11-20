package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Study;
import pl.jansmi.scheduler.fragments.MealsFragment;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.fragments.StudyFragment;
import pl.jansmi.scheduler.fragments.TasksFragment;
import pl.jansmi.scheduler.fragments.TrainingFragment;

public class AddArrangementActivity extends AppCompatActivity {

    private static final int SELECT_MEAL_RC = 1;
    private static final int SELECT_TRAINING_RC = 2;
    private static final int SELECT_TASK_RC = 3;
    private static final int SELECT_STUDYING_RC = 4;

    private String arrangementId; // to (potentially) update
    private List<List<String>> selectedMeals; // [weekday: 0-7][category] = mealId
    private List<List<Study>> selectedStudies;

    MealsFragment mealsFragment;
    TrainingFragment trainingFragment;
    TasksFragment tasksFragment;
    StudyFragment studyFragment;

    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_arrangement);

        this.selectedMeals = new ArrayList<>(Collections.nCopies(7, new ArrayList<>())); // 7 weekdays
        this.selectedStudies = new ArrayList<>(Collections.nCopies(7, new ArrayList<>())); // 7 weekdays

        this.arrangementId = getIntent().getExtras().getString("arrangementId");
        if (arrangementId != null) { // update
            // TODO: set activity title to 'update' and fill activity with initial data
        }

        // init navView
        navView = findViewById(R.id.add_arrangement_activity_nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_meals:
                        return loadFragment(mealsFragment);
                    case R.id.navigation_training:
                        return loadFragment(trainingFragment);
                    case R.id.navigation_tasks:
                        return loadFragment(tasksFragment);
                    case R.id.navigation_studying:
                        return loadFragment(studyFragment);
                }
                return false;
            }
        });

        // init fab
        FloatingActionButton addFab = findViewById(R.id.add_arrangement_activity_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayNumber;
                Intent intent;
                switch (navView.getSelectedItemId()) {
                    case R.id.navigation_meals:
                        dayNumber = mealsFragment.getCurrentDay();

                        intent = new Intent(getApplicationContext(), SelectMealsActivity.class);
                        intent.putExtra("meals", (Serializable) selectedMeals.get(dayNumber));
                        startActivityForResult(intent, SELECT_MEAL_RC);

                        break;

                    case R.id.navigation_training:
                        startActivityForResult(new Intent(view.getContext(), NewTrainingActivity.class), SELECT_TRAINING_RC);
                        break;

                    case R.id.navigation_tasks:
                        startActivityForResult(new Intent(view.getContext(), NewTaskActivity.class), SELECT_TASK_RC);
                        break;

                    case R.id.navigation_studying:
                        dayNumber = studyFragment.getCurrentDay();

                        intent = new Intent(getApplicationContext(), NewStudyingActivity.class);
                        intent.putExtra("studies", (Serializable) selectedStudies.get(dayNumber));
                        startActivityForResult(intent, SELECT_STUDYING_RC);

                        break;

                }
            }
        });

        // TODO: implement listener (insert or update, depending on arrangementId!)
        FloatingActionButton saveFab;

    }

    @Override
    protected void onStart() {
        super.onStart();

        // init fragments
        this.mealsFragment = new MealsFragment(selectedMeals);
        // TODO: pass data to the rest
        this.trainingFragment = new TrainingFragment();
        this.tasksFragment = new TasksFragment();
        this.studyFragment = new StudyFragment(selectedStudies);

        // set starting fragment
        navView.setSelectedItemId(R.id.action_meals);
        loadFragment(mealsFragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.add_arrangement_activity_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_MEAL_RC && resultCode == RESULT_OK) {
            List<String> mealsId = data.getExtras().getStringArrayList("meals");
            this.selectedMeals.set(mealsFragment.getCurrentDay(), mealsId);
        }

        else if (requestCode == SELECT_TRAINING_RC && resultCode == RESULT_OK) {

        }

        else if (requestCode == SELECT_TASK_RC && resultCode == RESULT_OK) {

        }

        else if (requestCode == SELECT_STUDYING_RC && resultCode == RESULT_OK) {

        }

    }
}
