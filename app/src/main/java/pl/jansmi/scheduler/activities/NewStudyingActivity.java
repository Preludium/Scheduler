package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.entities.Subject;
import pl.jansmi.scheduler.fragments.NewStudyFragment;

public class NewStudyingActivity extends AppCompatActivity {

    private List<String> selectedSubjectsId;

    private List<NewStudyFragment> newStudyFragmentList;
    private NewStudyingFragmentPagerAdapter adapter;

    private ViewPager pager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_studying);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.pager = findViewById(R.id.new_studying_content_pager);
        this.tabs = findViewById(R.id.new_studying_content_tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(pager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedSubjectsId = new ArrayList<>();
                for (NewStudyFragment fragment : newStudyFragmentList) {
                    if (fragment.getSelectedSubject() != null)
                        selectedSubjectsId.add(fragment.getSelectedSubject().getId());
                }

                Intent intent = new Intent();
                intent.putExtra("subjects", (Serializable) selectedSubjectsId);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.selectedSubjectsId = getIntent().getExtras().getStringArrayList("subjects");

        this.adapter = new NewStudyingFragmentPagerAdapter(getSupportFragmentManager());
        this.pager.setAdapter(adapter);

    }

    private class NewStudyingFragmentPagerAdapter extends FragmentPagerAdapter {

        NewStudyingFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);

            List<Subject> selectedSubjects = new ArrayList<>();
            for (String id : selectedSubjectsId)
                selectedSubjects.add(App.db.subjects().getById(id));

            // from selectedMeals get only one, that match category and create fragment
            newStudyFragmentList = new ArrayList<>();

            for (Subject subject : selectedSubjects)
                newStudyFragmentList.add(new NewStudyFragment(subject));
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return newStudyFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return newStudyFragmentList.size();
        }
    }
}
