package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.SubmitArrangementActivity;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Practice;
import pl.jansmi.scheduler.dbstructure.entities.Study;
import pl.jansmi.scheduler.dbstructure.entities.Task;
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
    private List<List<Practice>> selectedPractices;
    private List<List<Task>> selectedTasks;

    MealsFragment mealsFragment;
    TrainingFragment trainingFragment;
    TasksFragment tasksFragment;
    StudyFragment studyFragment;

    private int selectedFragment;

    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_arrangement);

        this.selectedMeals = new ArrayList<>(); // 7 weekdays
        this.selectedStudies = new ArrayList<>();
        this.selectedPractices = new ArrayList<>();
        this.selectedTasks = new ArrayList<>();

        for (int i=0; i<7; ++i) {
            this.selectedMeals.add(new ArrayList<>());
            this.selectedStudies.add(new ArrayList<>());
            this.selectedPractices.add(new ArrayList<>());
            this.selectedTasks.add(new ArrayList<>());
        }

        this.arrangementId = getIntent().getExtras().getString("arrangementId");
        if (arrangementId != null) { // update
            // TODO: set activity title to 'update' and fill activity with initial data
        } else {
            this.arrangementId = UUID.randomUUID().toString(); // generate UUID
        }

        // init starting fragment and navView
        selectedFragment = SELECT_MEAL_RC;
        navView = findViewById(R.id.add_arrangement_activity_nav_view);
        navView.setSelectedItemId(R.id.navigation_meals);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_meals:
                        selectedFragment = SELECT_MEAL_RC;
                        return loadFragment(mealsFragment);
                    case R.id.navigation_training:
                        selectedFragment = SELECT_TRAINING_RC;
                        return loadFragment(trainingFragment);
                    case R.id.navigation_tasks:
                        selectedFragment = SELECT_TASK_RC;
                        return loadFragment(tasksFragment);
                    case R.id.navigation_studying:
                        selectedFragment = SELECT_STUDYING_RC;
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
                        intent = new Intent(getApplicationContext(), NewTrainingActivity.class);
                        intent.putExtra("practice", (Practice) null); // no update
                        startActivityForResult(intent, SELECT_TRAINING_RC);
                        break;

                    case R.id.navigation_tasks:
                        intent = new Intent(getApplicationContext(), NewTaskActivity.class);
                        intent.putExtra("task", (Task) null); // no update
                        startActivityForResult(intent, SELECT_TASK_RC);
                        break;

                    case R.id.navigation_studying:
                        intent = new Intent(getApplicationContext(), NewStudyingActivity.class);
                        intent.putExtra("study", (Study) null); // no update
                        startActivityForResult(intent, SELECT_STUDYING_RC);
                        break;

                }
            }
        });

        // TODO: implement listener (insert or update, depending on arrangementId!)
        FloatingActionButton saveFab = findViewById(R.id.add_arrangement_activity_saveFab);
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SubmitArrangementActivity.class);
                intent.putExtra("arrangementId", arrangementId);
                intent.putExtra("meals", (Serializable) selectedMeals);
                intent.putExtra("practices", (Serializable) selectedPractices);
                intent.putExtra("tasks", (Serializable) selectedTasks);
                intent.putExtra("studies", (Serializable) selectedStudies);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // init fragments
        this.mealsFragment = new MealsFragment(selectedMeals);
        this.trainingFragment = new TrainingFragment(selectedPractices);
        this.tasksFragment = new TasksFragment(selectedTasks);
        this.studyFragment = new StudyFragment(selectedStudies);

        // set starting fragment
        switch (selectedFragment) {
            case SELECT_MEAL_RC:
                navView.setSelectedItemId(R.id.navigation_meals);
                loadFragment(mealsFragment);
                break;
            case SELECT_TRAINING_RC:
                navView.setSelectedItemId(R.id.navigation_training);
                loadFragment(trainingFragment);
                break;
            case SELECT_TASK_RC:
                navView.setSelectedItemId(R.id.navigation_tasks);
                loadFragment(tasksFragment);
                break;
            case SELECT_STUDYING_RC:
                navView.setSelectedItemId(R.id.navigation_studying);
                loadFragment(studyFragment);
                break;
        }


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
            Practice practice = (Practice) data.getSerializableExtra("practice");

            int day = trainingFragment.getCurrentDay();
            practice.setDayNumber(day);
            practice.setArrangementId(arrangementId);

            // if fetched study already exists in selectedStudies then update
            for (int i=0; i<selectedPractices.get(day).size(); ++i) {
                if (practice.getId().equals(selectedPractices.get(day).get(i).getId())) {
                    selectedPractices.get(day).set(i, practice);
                    return;
                }
            }
            // else insert new value
            selectedPractices.get(day).add(practice);
        }

        else if (requestCode == SELECT_TASK_RC && resultCode == RESULT_OK) {
            Task task = (Task) data.getSerializableExtra("task");

            int day = tasksFragment.getCurrentDay();
            task.setWeekday(day);
            task.setArrangementId(arrangementId);

            // if fetched study already exists in selectedStudies then update
            for (int i=0; i<selectedTasks.get(day).size(); ++i) {
                if (task.getId().equals(selectedTasks.get(day).get(i).getId())) {
                    selectedTasks.get(day).set(i, task);
                    return;
                }
            }
            // else insert new value
            selectedTasks.get(day).add(task);
        }

        else if (requestCode == SELECT_STUDYING_RC && resultCode == RESULT_OK) {
            Study study = (Study) data.getSerializableExtra("study");

            int day = studyFragment.getCurrentDay();
            study.setDayNumber(day);
            study.setArrangementId(arrangementId);

            // if fetched study already exists in selectedStudies then update
            for (int i=0; i<selectedStudies.get(day).size(); ++i) {
                if (study.getId().equals(selectedStudies.get(day).get(i).getId())) {
                    selectedStudies.get(day).set(i, study);
                    return;
                }
            }
            
            // else insert new value
            selectedStudies.get(day).add(study);
        }

    }
}
