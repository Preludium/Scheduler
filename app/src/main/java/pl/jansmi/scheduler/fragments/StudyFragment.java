package pl.jansmi.scheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Study;


public class StudyFragment extends Fragment {

    private StudyDayFragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabs;
    private List<StudyDayFragment> studyDayFragmentList;
    private List<List<Study>> selectedStudies;

    public StudyFragment(List<List<Study>> selectedStudies) {
        this.selectedStudies = selectedStudies;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);

        this.studyDayFragmentList = new ArrayList<>();
        for (int i=0; i<7; ++i)
            studyDayFragmentList.add(new StudyDayFragment(selectedStudies.get(i)));

        adapter = new StudyDayFragmentPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.study_fragment_pager);
        viewPager.setAdapter(adapter);

        tabs = view.findViewById(R.id.study_fragment_tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(viewPager);

        return view;
    }


    private class StudyDayFragmentPagerAdapter extends FragmentPagerAdapter {
        StudyDayFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return studyDayFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Monday";
                case 1:
                    return "Tuesday";
                case 2:
                    return "Wednesday";
                case 3:
                    return "Thursday";
                case 4:
                    return "Friday";
                case 5:
                    return "Saturday";
                case 6:
                    return "Sunday";
            }
            return null;
        }
    }

    public int getCurrentDay() {
        return viewPager.getCurrentItem();
    }
}
