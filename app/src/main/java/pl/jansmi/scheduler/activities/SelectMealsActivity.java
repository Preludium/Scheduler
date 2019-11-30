package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.fragments.SelectMealsFragment;
import pl.jansmi.scheduler.dbstructure.entities.Category;

public class SelectMealsActivity extends AppCompatActivity {

    private List<Category> categories;
    private List<String> selectedMealsId;

    private List<SelectMealsFragment> selectMealsFragmentList;
    private SelectMealsFragmentPagerAdapter adapter;

    private ViewPager pager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.pager = findViewById(R.id.select_meals_content_pager);
        this.tabs = findViewById(R.id.select_meals_content_tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(pager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedMealsId = new ArrayList<>();
                for (SelectMealsFragment fragment : selectMealsFragmentList) {
                    if (fragment.getSelectedMeal() != null)
                        selectedMealsId.add(fragment.getSelectedMeal().getId());
                }

                Intent intent = new Intent();
                intent.putExtra("meals", (Serializable) selectedMealsId);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.categories = App.db.categories().getAll();
        // TODO: sort categories by order

        this.selectedMealsId = getIntent().getExtras().getStringArrayList("meals");

        this.adapter = new SelectMealsFragmentPagerAdapter(getSupportFragmentManager());
        this.pager.setAdapter(adapter);

    }

    private class SelectMealsFragmentPagerAdapter extends FragmentPagerAdapter {

        SelectMealsFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);

            List<Meal> selectedMeals = new ArrayList<>();
            for (String id : selectedMealsId)
                selectedMeals.add(App.db.meals().getById(id));

            // from selectedMeals get only one, that matches category and create fragment
            selectMealsFragmentList = new ArrayList<>();

            Meal selectedMeal;
            for (Category cat : categories) {
                selectedMeal = null;

                for (Meal meal : selectedMeals) {
                    if (meal.getCategoryId().equals(cat.getId())) {
                        selectedMeal = meal;
                        break;
                    }
                }
                selectMealsFragmentList.add(new SelectMealsFragment(cat, selectedMeal));
            }
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return selectMealsFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return selectMealsFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return categories.get(position).getName();
        }

    }
}
