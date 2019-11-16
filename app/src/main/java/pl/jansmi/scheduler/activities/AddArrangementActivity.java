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
import java.util.HashMap;
import java.util.List;

import pl.jansmi.scheduler.fragments.MealsFragment;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.fragments.StudyingFragment;
import pl.jansmi.scheduler.fragments.TasksFragment;
import pl.jansmi.scheduler.fragments.TrainingFragment;

public class AddArrangementActivity extends AppCompatActivity {

    private static final int SELECT_MEAL_RC = 1;
    private static final int SELECT_TRAINING_RC = 2;
    private static final int SELECT_TASK_RC = 3;
    private static final int SELECT_STUDYING_RC = 4;

    private String arrangementId; // to (potentially) update
    private HashMap<String, Integer> selectedMeals;

    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_arrangement);

        selectedMeals = new HashMap<>();

        arrangementId = getIntent().getExtras().getString("arrangementId");
        if (arrangementId != null) { // update
            // TODO: set activity title to 'update' and fill activity with initial data
        }

        // init fragments
        MealsFragment mealsFragment = new MealsFragment(selectedMeals);
        // TODO: pass data to the rest
        TrainingFragment trainingFragment = new TrainingFragment();
        TasksFragment tasksFragment = new TasksFragment();
        StudyingFragment studyingFragment = new StudyingFragment();

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
                        return loadFragment(studyingFragment);
                }
                return false;
            }
        });

        // set starting fragment
        navView.setSelectedItemId(R.id.action_meals);
        loadFragment(mealsFragment);

        // init fab
        FloatingActionButton addFab = findViewById(R.id.add_arrangement_activity_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (navView.getSelectedItemId()) {
                    case R.id.navigation_meals:
                        List<String> selectedMealsId = new ArrayList<>();
                        int dayNumber = mealsFragment.getCurrentDay();

                        for (String key : selectedMeals.keySet()) {
                            if (selectedMeals.get(key) == dayNumber)
                                selectedMealsId.add(key);
                        }

                        Intent intent = new Intent(getApplicationContext(), SelectMealsActivity.class);
                        intent.putExtra("meals", (Serializable) selectedMealsId);
                        startActivityForResult(intent, SELECT_MEAL_RC);
                        break;
                    case R.id.navigation_training:
                        startActivityForResult(new Intent(view.getContext(), NewTrainingActivity.class), SELECT_TRAINING_RC);
                        break;
                    case R.id.navigation_tasks:
                        startActivityForResult(new Intent(view.getContext(), NewTaskActivity.class), SELECT_TASK_RC);
                        break;
                    case R.id.navigation_studying:
                        startActivityForResult(new Intent(view.getContext(), NewStudyingActivity.class), SELECT_STUDYING_RC);
                        break;
                }
            }
        });

        // TODO: implement listener (insert or update, depending on arrangementId!)
        FloatingActionButton saveFab;

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
            // TODO: insert selected meal to meals recycler
        }

        else if (requestCode == SELECT_TRAINING_RC && resultCode == RESULT_OK) {
            // TODO: insert selected training to training recycler
        }

        else if (requestCode == SELECT_TASK_RC && resultCode == RESULT_OK) {
            // TODO: insert selected task to task recycler
        }

        else if (requestCode == SELECT_STUDYING_RC && resultCode == RESULT_OK) {
            // TODO: insert selected studying to task recycler
        }

    }
}
