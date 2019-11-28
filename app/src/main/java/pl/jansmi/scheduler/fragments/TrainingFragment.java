package pl.jansmi.scheduler.fragments;

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
import pl.jansmi.scheduler.dbstructure.entities.Practice;

public class TrainingFragment extends Fragment {

    private TrainingDayFragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabs;
    private List<TrainingDayFragment> trainingDayFragmentList;
    private List<List<Practice>> selectedPractices;

    public TrainingFragment(List<List<Practice>> selectedPractices) {
        this.selectedPractices = selectedPractices;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        this.trainingDayFragmentList = new ArrayList<>();
        for (int i = 0; i < 7; ++i)
            trainingDayFragmentList.add(new TrainingDayFragment(selectedPractices.get(i)));

        adapter = new TrainingDayFragmentPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.training_fragment_pager);
        viewPager.setAdapter(adapter);

        tabs = view.findViewById(R.id.training_fragment_tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(viewPager);

        return view;
    }


    private class TrainingDayFragmentPagerAdapter extends FragmentPagerAdapter {
        TrainingDayFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return trainingDayFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
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
