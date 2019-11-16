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

import android.view.View;

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
    private List<Meal> selectedMeals;
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
                // TODO: pass selected meals to AddArrangementActivity
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.categories = App.db.categories().getAll();
        // TODO: sort categories by order

        this.selectedMeals = new ArrayList<>();
        List<String> selectedMealsId = getIntent().getExtras().getStringArrayList("meals");
        for (String id : selectedMealsId)
            selectedMeals.add(App.db.meals().getById(id));

        this.adapter = new SelectMealsFragmentPagerAdapter(getSupportFragmentManager());
        this.pager.setAdapter(adapter);

    }

    private class SelectMealsFragmentPagerAdapter extends FragmentPagerAdapter {

        public SelectMealsFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);

            // TODO: from selectedMeals get only these, that match category
            selectMealsFragmentList = new ArrayList<>();
            for (Category cat : categories)
                selectMealsFragmentList.add(new SelectMealsFragment(cat, null));
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
