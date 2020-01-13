package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.fragments.MealCategoryFragment;

public class MealsActivity extends AppCompatActivity {

    private MealCategoryFragmentPagerAdapter adapter;
    private TabLayout tabs;
    private ViewPager pager;

    private List<Category> categories;
    private TextView infoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.pager = findViewById(R.id.meals_content_pager);
        this.tabs = findViewById(R.id.meals_content_tabs);
        this.tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        this.infoBox = findViewById(R.id.meals_content_info_text);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categories.size() == 0)
                    Snackbar.make(view, "No meal categories found! Please first add some.",
                            Snackbar.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), AddMealActivity.class);
                    intent.putExtra("mealId", (String) null); // no update
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.categories = App.db.categories().getAll();
        this.categories.sort((cat1, cat2) -> Integer.compare(cat1.getOrder(), cat2.getOrder()));

        this.adapter = new MealCategoryFragmentPagerAdapter(getSupportFragmentManager());
        this.pager.setAdapter(adapter);
        this.tabs.setupWithViewPager(pager);

        if (adapter.getCount() == 0)
            infoBox.setVisibility(View.VISIBLE);
        else
            infoBox.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ingredients:
                ingredients();
                return true;

            case R.id.action_categories:
                categories();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void categories() {
        startActivity(new Intent(getApplicationContext(), CategoriesActivity.class));
    }

    private void ingredients() {
        startActivity(new Intent(getApplicationContext(), IngredientsActivity.class));
    }

    private class MealCategoryFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private List<MealCategoryFragment> mealCategoryFragmentList;

        MealCategoryFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);

            this.mealCategoryFragmentList = new ArrayList<>();
            for (Category cat : categories)
                mealCategoryFragmentList.add(new MealCategoryFragment(cat));
        }

        @NonNull
        @Override
        public Fragment getItem(int position) { return mealCategoryFragmentList.get(position); }

        @Override
        public int getCount() {
            return mealCategoryFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return categories.get(position).getName();
        }

    }

}
